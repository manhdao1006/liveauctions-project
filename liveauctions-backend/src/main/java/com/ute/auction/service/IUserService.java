package com.ute.auction.service;

import com.ute.auction.dto.UserDTO;

public interface IUserService {

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    UserDTO updateProfile(Long id, UserDTO updatedUser);

    void forgotPassword(String email, String password);
    
}
