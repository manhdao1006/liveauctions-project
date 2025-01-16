package com.ute.auction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.dto.AuthResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.security.JWTGenerator;
import com.ute.auction.service.INguoiDungService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final INguoiDungService userService;
    private final JWTGenerator jwtGenerator;

    // Build API forgot password
    @PutMapping("forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable("email") String email, @RequestParam String password) {
        userService.forgotPassword(email, password);
        return ResponseEntity.ok("Password changed successfully!");
    }

    // Build API register for BUYER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody NguoiDungDTO userDTO) {
        userService.register(userDTO);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    // Build API register for SELLER
    @PostMapping("/register-seller")
    public ResponseEntity<String> registerSeller(@RequestBody NguoiDungDTO userDTO) {
        userService.registerSeller(userDTO);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    // Build API register for STAFF
    @PostMapping("/register-staff")
    public ResponseEntity<String> registerStaff(@RequestBody NguoiDungDTO userDTO) {
        userService.registerStaff(userDTO);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    // Build API register for ADMIN
    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody NguoiDungDTO userDTO) {
        userService.registerAdmin(userDTO);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    // Build API login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody NguoiDungDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getMatKhau()));
        // sau khi đăng nhập thành công thì sẽ lưu vào để đánh dấu là đã đăng nhập
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
