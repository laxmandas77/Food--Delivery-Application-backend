package com.das.foodesapi.service;

import com.das.foodesapi.io.UserRequest;
import com.das.foodesapi.io.UserResponse;

public interface UserService {

     UserResponse registerUser(UserRequest request);

     String findByUserId();
}
