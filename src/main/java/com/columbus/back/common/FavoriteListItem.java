package com.columbus.back.common;

import java.util.ArrayList;
import java.util.List;

import com.columbus.back.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteListItem {
    private String userId;
    private String nickname;
    private String profileImage;

    public FavoriteListItem(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();

    }

    public static List<FavoriteListItem> getList(List<UserEntity> userEntities) {
        List<FavoriteListItem> list = new ArrayList<>();
        for (UserEntity userEntity: userEntities) {
            FavoriteListItem favoriteListItem = new FavoriteListItem(userEntity);
            list.add(favoriteListItem);
        }
        return list;
    }
}
