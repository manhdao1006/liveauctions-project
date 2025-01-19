package com.ute.auction.controller.web.seller.crud;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.service.INguoiDungService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "user" + ApiName.CRUD_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class NguoiDungController {

    private final INguoiDungService userService;

    // Build API update profile of seller
    @SuppressWarnings("null")
    @PutMapping("/update-profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") long id,
            @RequestParam(value = "fullName", required = false) String fullName,
            @Valid @RequestParam(value = "email", required = false) String email,
            @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "dob", required = false) LocalDate dob,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "cityId", required = false) Long cityId) throws IOException {

        NguoiDungDTO userDTO = new NguoiDungDTO();
        userDTO.setHoVaTen(fullName);
        userDTO.setEmail(email);
        userDTO.setSoDienThoai(phoneNumber);
        userDTO.setDiaChi(address);
        userDTO.setNgaySinh(dob);
        userDTO.setGioiTinh(gender);

        if (cityId != null) {
            userDTO.setMaPhuongXa(cityId);
        }

        try {
            NguoiDungDTO updatedUser = userService.updateProfile(id, userDTO, avatar);
            return ResponseEntity.ok(updatedUser);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ResourceExistedException("Email already exists!");
        }
    }

}
