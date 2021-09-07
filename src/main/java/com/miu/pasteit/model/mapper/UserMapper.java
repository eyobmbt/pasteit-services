package com.miu.pasteit.model.mapper;

import com.miu.pasteit.model.dto.UserModel;
import com.miu.pasteit.model.entity.db.sql.User;
import com.miu.pasteit.model.request.UserCreateRequest;


/**
 * @author Rimon Mostafiz
 */
public class UserMapper {

    public static UserModel mapToUserModel(User entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setStatus(entity.getStatus());
        return model;
    }

    public static UserModel mapUserCreateRequest(User entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setStatus(entity.getStatus());
        return model;
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
}
