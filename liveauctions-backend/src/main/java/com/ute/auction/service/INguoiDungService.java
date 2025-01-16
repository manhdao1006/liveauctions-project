package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NguoiDungDTO;

public interface INguoiDungService {

    NguoiDungDTO updateProfile(long id, NguoiDungDTO userDTO, MultipartFile avatar) throws IOException;

    void forgotPassword(String email, String password);

    NguoiDungDTO register(NguoiDungDTO userDTO);

    NguoiDungDTO registerSeller(NguoiDungDTO userDTO);

    NguoiDungDTO registerStaff(NguoiDungDTO userDTO);

    NguoiDungDTO registerAdmin(NguoiDungDTO userDTO);

    List<NguoiDungDTO> getAllUsersByRole(long roleId);

}
