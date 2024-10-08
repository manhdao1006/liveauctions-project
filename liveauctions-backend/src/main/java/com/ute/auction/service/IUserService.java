package com.ute.auction.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.UserDTO;

public interface IUserService {

    UserDTO getUserById(int id);

    UserDTO getUserByEmail(String email);

    UserDTO updateProfile(int id, UserDTO userDTO, MultipartFile avatar) throws IOException;

    void forgotPassword(String email, String password);

    UserDTO register(UserDTO userDTO);

    UserDTO registerSeller(UserDTO userDTO);

    UserDTO registerStaff(UserDTO userDTO);

    UserDTO registerAdmin(UserDTO userDTO);

}
