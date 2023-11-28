package com.columbus.back.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.request.board.PostCommentRequestDto;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.board.GetBoardResponseDto;
import com.columbus.back.dto.response.board.GetCommentListResponseDto;
import com.columbus.back.dto.response.board.GetFavoriteListResponseDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;
import com.columbus.back.dto.response.board.PostCommentResponseDto;
import com.columbus.back.dto.response.board.PutFavoriteResponseDto;
import com.columbus.back.entity.BoardEntity;
import com.columbus.back.entity.CommentEntity;
import com.columbus.back.entity.FavoriteEntity;
import com.columbus.back.entity.ImageEntity;
import com.columbus.back.entity.ReviewLocationEntity;
import com.columbus.back.repository.BoardRepository;
import com.columbus.back.repository.CommentRepository;
import com.columbus.back.repository.FavoriteRepository;
import com.columbus.back.repository.ImageRepository;
import com.columbus.back.repository.ReviewLocationRepository;
import com.columbus.back.repository.UserRepository;
import com.columbus.back.repository.resultSet.GetBoardResultSet;
import com.columbus.back.repository.resultSet.GetCommentListResultSet;
import com.columbus.back.repository.resultSet.GetFavoriteListResultSet;
import com.columbus.back.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;
    private final ReviewLocationRepository reviewLocationRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer reviewNumber) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();
        ReviewLocationEntity reviewLocationEntity = null;
        
        try {
            
            resultSet = boardRepository.getBoard(reviewNumber);
            if (resultSet == null) return GetBoardResponseDto.notExistBoard();

            imageEntities = imageRepository.findByReviewNumber(reviewNumber);
            reviewLocationEntity = reviewLocationRepository.findByReviewNumber(reviewNumber);

            BoardEntity boardEntity = boardRepository.findByReviewNumber(reviewNumber);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(resultSet, imageEntities, reviewLocationEntity);

    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer reviewNumber) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean existedBoard = boardRepository.existsByReviewNumber(reviewNumber);
            if (!existedBoard) return GetFavoriteListResponseDto.noExistBoard();

            resultSets = favoriteRepository.getFavoriteList(reviewNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);

    }
    
    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer reviewNumber) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean existedBoard = boardRepository.existsByReviewNumber(reviewNumber);
            if (!existedBoard) return GetCommentListResponseDto.noExistBoard();

            resultSets = commentRepository.getCommentList(reviewNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String userId) {

        try {

            boolean existedUserId = userRepository.existsByUserId(userId);
            if(!existedUserId) return PostBoardResponseDto.notExistUser();
            
            BoardEntity boardEntity = new BoardEntity(dto, userId);
            boardRepository.save(boardEntity);

            int reviewNumber = boardEntity.getReviewNumber();

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image: boardImageList) {
                ImageEntity imageEntity = new ImageEntity(reviewNumber, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

            String reviewLocation = dto.getLocation();

            ReviewLocationEntity reviewLocationEntity = new ReviewLocationEntity(reviewNumber, reviewLocation);
            reviewLocationRepository.save(reviewLocationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();

    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer reviewNumber, String userId) {

        try {

            BoardEntity boardEntity = boardRepository.findByReviewNumber(reviewNumber);
            if (boardEntity == null) return PostCommentResponseDto.noExistBoard();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PostCommentResponseDto.noExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, reviewNumber, userId);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();
        
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer reviewNumber, String userId) {
        
        try {

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PutFavoriteResponseDto.noExistUser();

            BoardEntity boardEntity = boardRepository.findByReviewNumber(reviewNumber);
            if (boardEntity == null) return PutFavoriteResponseDto.noExistBoard();

            FavoriteEntity favoriteEntity = favoriteRepository.findByReviewNumberAndUserId(reviewNumber, userId);
            if (favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(userId, reviewNumber);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            }
            else {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();

    }

    
    

}
