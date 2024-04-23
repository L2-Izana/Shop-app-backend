package com.project.shopapp.services.user_management;

import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.user_management.*;
import com.project.shopapp.dtos.user_management.*;

public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;

    String login(String phoneNumber, String password);
}
