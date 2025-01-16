package com.ute.auction.controller.api.v1.admin.crud;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.service.INhaThamDinhService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "appraiser" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/appraiser")
public class NhaThamDinhController {

    private final INhaThamDinhService appraiserService;

    // Build API add an appraiser
    @SuppressWarnings("null")
    @PostMapping("/add")
    public ResponseEntity<?> addAppraiser(@Valid @RequestParam(value = "name", required = false) String name,
            @Valid @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "gender", required = false) String gender,
            @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "dob", required = false) LocalDate dob,
            @RequestParam(value = "description", required = false) String description) throws IOException {
        NhaThamDinhDTO newAppraiser = new NhaThamDinhDTO();
        newAppraiser.setHoVaTen(name);
        newAppraiser.setEmail(email);
        newAppraiser.setGioiTinh(gender);
        newAppraiser.setSoDienThoai(phoneNumber);
        newAppraiser.setDiaChi(address);
        newAppraiser.setLoai(type);
        newAppraiser.setTrangThaiHoatDong(status);
        newAppraiser.setNgaySinh(dob);
        newAppraiser.setMoTa(description);

        if (avatar != null && !avatar.isEmpty()) {
            if (!avatar.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("The file is not an image format!");
            }
            newAppraiser.setAvatar(Base64.getEncoder().encodeToString(avatar.getBytes()));
        } else {
            newAppraiser.setAvatar(null);
        }

        NhaThamDinhDTO savedAppraiser = appraiserService.addAppraiser(newAppraiser);

        return new ResponseEntity<>(savedAppraiser, HttpStatus.OK);
    }

    // Build API edit an existed appraiser
    @SuppressWarnings("null")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateAppraiser(@PathVariable("id") int id,
            @Valid @RequestParam(value = "name", required = false) String name,
            @Valid @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "gender", required = false) String gender,
            @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "dob", required = false) LocalDate dob,
            @RequestParam(value = "description", required = false) String description) throws IOException {
        NhaThamDinhDTO appraiserDTO = new NhaThamDinhDTO();
        appraiserDTO.setHoVaTen(name);
        appraiserDTO.setEmail(email);
        appraiserDTO.setGioiTinh(gender);
        appraiserDTO.setSoDienThoai(phoneNumber);
        appraiserDTO.setDiaChi(address);
        appraiserDTO.setLoai(type);
        appraiserDTO.setTrangThaiHoatDong(status);
        appraiserDTO.setNgaySinh(dob);
        appraiserDTO.setMoTa(description);

        if (avatar != null && !avatar.isEmpty()) {
            if (!avatar.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("The file is not an image format!");
            }
            appraiserDTO.setAvatar(Base64.getEncoder().encodeToString(avatar.getBytes()));
        } else {
            appraiserDTO.setAvatar(null);
        }

        try {
            NhaThamDinhDTO updatedAppraiser = appraiserService.updateAppraiser(id, appraiserDTO);
            return new ResponseEntity<>(updatedAppraiser, HttpStatus.OK);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ResourceExistedException("Email already exists!");
        }
    }

    // Build API delete an existed appraiser
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppraiser(@PathVariable("id") int appraiserId) {
        appraiserService.deleteAppraiser(appraiserId);
        return ResponseEntity.ok("Deleting appraiser is successfully");
    }

    // Build API ban an appraiser
    @PutMapping("ban/{id}")
    public ResponseEntity<String> banAppraiser(@PathVariable("id") int id) {
        appraiserService.banAppraiser(id);
        return ResponseEntity.ok("Banning appraiser is successfully!");
    }

}
