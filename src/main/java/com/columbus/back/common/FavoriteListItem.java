package com.columbus.back.common;

import java.util.ArrayList;
import java.util.List;

import com.columbus.back.repository.review.resultSet.GetReviewFavoriteListResultSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteListItem {
    private String userId;
    private String nickname;
    private String profileImage;

    public FavoriteListItem(GetReviewFavoriteListResultSet resultSet) {
        this.userId = resultSet.getUserId();
        this.nickname = resultSet.getNickname();
        this.profileImage =resultSet.getProfileImage(); 
    }

    public static List<FavoriteListItem> copyList(List<GetReviewFavoriteListResultSet> resultSets) {
        List<FavoriteListItem> list = new ArrayList<>();
        for (GetReviewFavoriteListResultSet resultSet: resultSets) {
            FavoriteListItem favoriteListItem = new FavoriteListItem(resultSet);
            list.add(favoriteListItem);
        }
        return list;
    }
}
