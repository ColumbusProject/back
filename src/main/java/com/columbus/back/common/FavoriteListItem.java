package com.columbus.back.common;

import java.util.ArrayList;
import java.util.List;

import com.columbus.back.entity.UserEntity;
import com.columbus.back.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteListItem {
    private String userId;
    private String nickname;
    private String profileImage;

    public FavoriteListItem(GetFavoriteListResultSet resultSet) {
        this.userId = resultSet.getUserId();
        this.nickname = resultSet.getNickname();
        this.profileImage =resultSet.getProfileImage(); 
    }

    public static List<FavoriteListItem> copyList(List<GetFavoriteListResultSet> resultSets) {
        List<FavoriteListItem> list = new ArrayList<>();
        for (GetFavoriteListResultSet resultSet: resultSets) {
            FavoriteListItem favoriteListItem = new FavoriteListItem(resultSet);
            list.add(favoriteListItem);
        }
        return list;
    }
}
