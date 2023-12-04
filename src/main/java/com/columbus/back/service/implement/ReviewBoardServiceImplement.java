package com.columbus.back.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.columbus.back.dto.request.board.review.PostReviewBoardRequestDto;
import com.columbus.back.dto.request.board.review.PostReviewCommentRequestDto;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewBoardResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewCommentListResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewFavoriteListResponseDto;
import com.columbus.back.dto.response.board.review.PostReviewBoardResponseDto;
import com.columbus.back.dto.response.board.review.PostReviewCommentResponseDto;
import com.columbus.back.dto.response.board.review.PutReviewFavoriteResponseDto;
import com.columbus.back.entity.review.ReviewCommentEntity;
import com.columbus.back.entity.review.ReviewFavoriteEntity;
import com.columbus.back.entity.review.ReviewImageEntity;
import com.columbus.back.entity.review.ReviewBoardEntity;
import com.columbus.back.entity.review.ReviewLocationEntity;
import com.columbus.back.repository.UserRepository;
import com.columbus.back.repository.review.ReviewCommentRepository;
import com.columbus.back.repository.review.ReviewFavoriteRepository;
import com.columbus.back.repository.review.ReviewImageRepository;
import com.columbus.back.repository.review.ReviewBoardRepository;
import com.columbus.back.repository.review.ReviewLocationRepository;
import com.columbus.back.repository.review.resultSet.GetReviewBoardResultSet;
import com.columbus.back.repository.review.resultSet.GetReviewCommentListResultSet;
import com.columbus.back.repository.review.resultSet.GetReviewFavoriteListResultSet;
import com.columbus.back.service.ReviewBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewBoardServiceImplement implements ReviewBoardService {

    private final UserRepository userRepository;
    private final ReviewBoardRepository reviewBoardRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewFavoriteRepository reviewFavoriteRepository;
    private final ReviewLocationRepository reviewLocationRepository;

    @Override
    public ResponseEntity<? super GetReviewBoardResponseDto> getReviewBoard(Integer reviewNumber) {

        GetReviewBoardResultSet resultSet = null;
        List<ReviewImageEntity> reviewImageEntities = new ArrayList<>();
        ReviewLocationEntity reviewLocationEntity = null;
        
        try {
            
            resultSet = reviewBoardRepository.getReviewBoard(reviewNumber);
            if (resultSet == null) return GetReviewBoardResponseDto.notExistReviewBoard();

            reviewImageEntities = reviewImageRepository.findByReviewNumber(reviewNumber);
            reviewLocationEntity = reviewLocationRepository.findByReviewNumber(reviewNumber);

            ReviewBoardEntity reviewBoardEntity = reviewBoardRepository.findByReviewNumber(reviewNumber);
            reviewBoardEntity.increaseViewCount();
            reviewBoardRepository.save(reviewBoardEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewBoardResponseDto.success(resultSet, reviewImageEntities, reviewLocationEntity);

    }

    @Override
    public ResponseEntity<? super GetReviewFavoriteListResponseDto> getReviewFavoriteList(Integer reviewNumber) {

        List<GetReviewFavoriteListResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean existedReviewBoard = reviewBoardRepository.existsByReviewNumber(reviewNumber);
            if (!existedReviewBoard) return GetReviewFavoriteListResponseDto.noExistReviewBoard();

            resultSets = reviewFavoriteRepository.getReviewFavoriteList(reviewNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewFavoriteListResponseDto.success(resultSets);

    }
    
    @Override
    public ResponseEntity<? super GetReviewCommentListResponseDto> getReviewCommentList(Integer reviewNumber) {

        List<GetReviewCommentListResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean existedReviewBoard = reviewBoardRepository.existsByReviewNumber(reviewNumber);
            if (!existedReviewBoard) return GetReviewCommentListResponseDto.noExistReviewBoard();

            resultSets = reviewCommentRepository.getReviewCommentList(reviewNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetReviewCommentListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super PostReviewBoardResponseDto> postReviewBoard(PostReviewBoardRequestDto dto, String userId) {

        try {

            boolean existedUserId = userRepository.existsByUserId(userId);
            if(!existedUserId) return PostReviewBoardResponseDto.notExistUser();
            
            ReviewBoardEntity reviewBoardEntity = new ReviewBoardEntity(dto, userId);
            reviewBoardRepository.save(reviewBoardEntity);

            int reviewNumber = reviewBoardEntity.getReviewNumber();

            List<String> reviewBoardImageList = dto.getBoardImageList();
            List<ReviewImageEntity> reviewImageEntities = new ArrayList<>();

            for (String image: reviewBoardImageList) {
                ReviewImageEntity reviewImageEntity = new ReviewImageEntity(reviewNumber, image);
                reviewImageEntities.add(reviewImageEntity);
            }
            reviewImageRepository.saveAll(reviewImageEntities);

            String reviewLocation = dto.getLocation();

            ReviewLocationEntity reviewLocationEntity = new ReviewLocationEntity(reviewNumber, reviewLocation);
            reviewLocationRepository.save(reviewLocationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostReviewBoardResponseDto.success();

    }

    @Override
    public ResponseEntity<? super PostReviewCommentResponseDto> postReviewComment(PostReviewCommentRequestDto dto, Integer reviewNumber, String userId) {

        try {

            ReviewBoardEntity reviewBoardEntity = reviewBoardRepository.findByReviewNumber(reviewNumber);
            if (reviewBoardEntity == null) return PostReviewCommentResponseDto.noExistReviewBoard();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PostReviewCommentResponseDto.noExistUser();

            ReviewCommentEntity reviewCommentEntity = new ReviewCommentEntity(dto, reviewNumber, userId);
            reviewCommentRepository.save(reviewCommentEntity);

            reviewBoardEntity.increaseCommentCount();
            reviewBoardRepository.save(reviewBoardEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostReviewCommentResponseDto.success();
        
    }

    @Override
    public ResponseEntity<? super PutReviewFavoriteResponseDto> putReviewFavorite(Integer reviewNumber, String userId) {
        
        try {

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PutReviewFavoriteResponseDto.noExistUser();

            ReviewBoardEntity reviewBoardEntity = reviewBoardRepository.findByReviewNumber(reviewNumber);
            if (reviewBoardEntity == null) return PutReviewFavoriteResponseDto.noExistReviewBoard();

            ReviewFavoriteEntity reviewFavoriteEntity = reviewFavoriteRepository.findByReviewNumberAndUserId(reviewNumber, userId);
            if (reviewFavoriteEntity == null) {
                reviewFavoriteEntity = new ReviewFavoriteEntity(userId, reviewNumber);
                reviewFavoriteRepository.save(reviewFavoriteEntity);
                reviewBoardEntity.increaseFavoriteCount();
            }
            else {
                reviewFavoriteRepository.delete(reviewFavoriteEntity);
                reviewBoardEntity.decreaseFavoriteCount();
            }

            reviewBoardRepository.save(reviewBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutReviewFavoriteResponseDto.success();

    }

    
    

}
