package com.miu.pasteit.model.mapper;

import com.miu.pasteit.model.dto.UserModel;
import com.miu.pasteit.model.entity.db.sql.User;
import com.miu.pasteit.model.request.UserCreateRequest;
import com.miu.pasteit.model.request.UserUpdateRequest;


/**
 * @author Rimon Mostafiz
 */
public class UserMapper {

    public static UserModel mapToUserModel(User entity) {
        return UserModel.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .status(entity.getStatus())
                .build();
    }

    public static UserModel mapperForInternal(User entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        return model;
    }

    public static User mapUserCreateRequest(UserCreateRequest userCreateRequest, String createdBy,
                                            String hashedPassword) {
        User user = new User();
        user.setUsername(userCreateRequest.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(userCreateRequest.getEmail());
        user.setFirstName(userCreateRequest.getFirstName());
        user.setLastName(userCreateRequest.getLastName());
        user.setStatus(userCreateRequest.getStatus());

        user.setCreatedBy(createdBy);
        return user;
    }

    public static User mapUserUpdateRequest(User user, UserUpdateRequest userCreateRequest, String editedBy) {

        user.setEmail(userCreateRequest.getEmail());
        user.setFirstName(userCreateRequest.getFirstName());
        user.setLastName(userCreateRequest.getLastName());

        user.setEditedBy(editedBy);
        return user;
    }
}
