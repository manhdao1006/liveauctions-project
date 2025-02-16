package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NguoiDungResponseDTO;

public interface INguoiDungService {

    void forgotPassword(String email, String password);

    NguoiDungDTO register(NguoiDungDTO userDTO);

    NguoiDungDTO registerSeller(NguoiDungDTO userDTO);

    NguoiDungDTO registerStaff(NguoiDungDTO userDTO);

    NguoiDungDTO registerAdmin(NguoiDungDTO userDTO);

    List<NguoiDungDTO> getNguoiDungsByMaVaiTro(long maVaiTro);

    NguoiDungResponseDTO getNguoiDungByMaNguoiDung(long maNguoiDung);

    NguoiDungDTO getNguoiDungByEmail(String name);

}
