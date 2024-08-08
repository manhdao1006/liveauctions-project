package com.ute.auction.controller.seller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.CityDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.exception.DuplicateEmailException;
import com.ute.auction.service.IUserService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seller")
public class UserController {

    private final IUserService userService;

    // Build API get seller by id
    @GetMapping("/profile/id={id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // Build API get seller by email
    @GetMapping("/profile/email={email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    // Build API update profile of seller
    @SuppressWarnings("null")
    @PutMapping("/update-profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Long id, 
                                                @RequestParam(value = "firstName", required = false) String firstName, 
                                                @RequestParam(value = "lastName", required = false) String lastName, 
                                                @Valid @RequestParam(value = "email", required = false) String email, 
                                                @RequestParam(value = "password", required = false) String password, 
                                                @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber, 
                                                @RequestParam(value = "address", required = false) String address, 
                                                @RequestParam(value = "dob", required = false) LocalDate dob, 
                                                @RequestParam(value = "gender", required = false) String gender, 
                                                @RequestParam(value = "avatar", required = false) MultipartFile avatar, 
                                                @RequestParam(value = "cityId", required = false) Long cityId) throws IOException {
        
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setAddress(address);
        userDTO.setDob(dob);
        userDTO.setGender(gender);

        if (cityId != null) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(cityId);
            userDTO.setCity(cityDTO);
        }

        if (avatar != null && !avatar.isEmpty()) {
            if (!avatar.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("The file is not an image format!");
            }
            userDTO.setAvatar(Base64.getEncoder().encodeToString(avatar.getBytes()));
        } else {
            userDTO.setAvatar(null);
        }

        try {
            UserDTO updatedUser = userService.updateProfile(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new DuplicateEmailException("Email already exists!");
        }
    }

    // Build API forgot password
    @PutMapping("forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable("email") String email, @RequestParam String password) {
        userService.forgotPassword(email, password);
        return ResponseEntity.ok("Password changed successfully!");
    }

}
