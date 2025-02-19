package com.ute.auction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.AuthResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NguoiDungResponseDTO;
import com.ute.auction.dto.PhuongXaDTO;
import com.ute.auction.dto.QuanHuyenDTO;
import com.ute.auction.security.BlackList;
import com.ute.auction.security.CustomUserDetailsService;
import com.ute.auction.security.JWTGenerator;
import com.ute.auction.service.INguoiDungService;
import com.ute.auction.service.IPhuongXaService;
import com.ute.auction.service.IQuanHuyenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final INguoiDungService nguoiDungService;
    private final JWTGenerator jwtGenerator;
    private final BlackList blackList;
    private final CustomUserDetailsService userDetailsService;
    private final IPhuongXaService phuongXaService;
    private final IQuanHuyenService quanHuyenService;

    @GetMapping("/quan-huyen")
    public ApiResponse<List<QuanHuyenDTO>> getQuanHuyens() {
        return ApiResponse.<List<QuanHuyenDTO>>builder()
                .code(200)
                .message("Danh sách quận huyện")
                .result(quanHuyenService.getQuanHuyens())
                .build();
    }

    @GetMapping("/quan-huyen/maPhuongXa={maPhuongXa}")
    public ApiResponse<QuanHuyenDTO> getQuanHuyenByMaPhuongXa(@PathVariable("maPhuongXa") long maPhuongXa) {
        return ApiResponse.<QuanHuyenDTO>builder()
                .code(200)
                .message("Danh sách quận huyện")
                .result(quanHuyenService.getQuanHuyenByMaPhuongXa(maPhuongXa))
                .build();
    }

    @GetMapping("/phuong-xa/maQuanHuyen={maQuanHuyen}")
    public ApiResponse<List<PhuongXaDTO>> getPhuongXasByMaQuanHuyen(@PathVariable("maQuanHuyen") long maQuanHuyen) {
        return ApiResponse.<List<PhuongXaDTO>>builder()
                .code(200)
                .message("Danh sách phường xã")
                .result(phuongXaService.getPhuongXasByMaQuanHuyen(maQuanHuyen))
                .build();
    }

    @GetMapping("/phuong-xa")
    public ApiResponse<List<PhuongXaDTO>> getPhuongXas() {
        return ApiResponse.<List<PhuongXaDTO>>builder()
                .code(200)
                .message("Danh sách phường xã")
                .result(phuongXaService.getPhuongXas())
                .build();
    }

    @GetMapping("/profile")
    public ApiResponse<NguoiDungDTO> getUserProfile(Authentication authentication) {
        return ApiResponse.<NguoiDungDTO>builder()
                .code(200)
                .message("Người dùng với email là " + authentication.getName())
                .result(nguoiDungService.getNguoiDungByEmail(authentication.getName()))
                .build();
    }

    @GetMapping("/view/{maNguoiDung}")
    public ApiResponse<NguoiDungResponseDTO> getNguoiDungByMaNguoiDung(@PathVariable("maNguoiDung") long maNguoiDung) {
        return ApiResponse.<NguoiDungResponseDTO>builder()
                .code(200)
                .message("Người dùng với mã người dùng là " + maNguoiDung)
                .result(nguoiDungService.getNguoiDungByMaNguoiDung(maNguoiDung))
                .build();
    }

    @PutMapping("forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable("email") String email, @RequestParam String password) {
        nguoiDungService.forgotPassword(email, password);
        return ResponseEntity.ok("Password changed successfully!");
    }

    @PostMapping("/register")
    public ApiResponse<NguoiDungDTO> register(@RequestBody NguoiDungDTO nguoiDungDTO) {
        return ApiResponse.<NguoiDungDTO>builder()
                .code(200)
                .message("Đăng ký thành công")
                .result(nguoiDungService.register(nguoiDungDTO))
                .build();
    }

    @PostMapping("/register-seller")
    public ApiResponse<NguoiDungDTO> registerSeller(@RequestBody NguoiDungDTO nguoiDungDTO) {
        return ApiResponse.<NguoiDungDTO>builder()
                .code(200)
                .message("Đăng ký thành công")
                .result(nguoiDungService.registerSeller(nguoiDungDTO))
                .build();
    }

    @PostMapping("/register-staff")
    public ApiResponse<NguoiDungDTO> registerStaff(@RequestBody NguoiDungDTO nguoiDungDTO) {
        return ApiResponse.<NguoiDungDTO>builder()
                .code(200)
                .message("Đăng ký thành công")
                .result(nguoiDungService.registerStaff(nguoiDungDTO))
                .build();
    }

    @PostMapping("/register-admin")
    public ApiResponse<NguoiDungDTO> registerAdmin(@RequestBody NguoiDungDTO nguoiDungDTO) {
        return ApiResponse.<NguoiDungDTO>builder()
                .code(200)
                .message("Đăng ký thành công")
                .result(nguoiDungService.registerAdmin(nguoiDungDTO))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO> login(@RequestBody NguoiDungDTO nguoiDungDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(nguoiDungDTO.getEmail(), nguoiDungDTO.getMatKhau()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtGenerator.generateToken(userDetails);
        String refreshToken = jwtGenerator.generateRefreshToken(userDetails.getUsername());

        return ApiResponse.<AuthResponseDTO>builder()
                .code(200)
                .message("Đăng nhập thành công")
                .result(new AuthResponseDTO(accessToken, refreshToken))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        if (!jwtGenerator.validateToken(jwtToken)) {
            return ApiResponse.<String>builder()
                    .code(401)
                    .message("Unauthorized: Invalid token.")
                    .build();
        }
        blackList.addToBlackListToken(jwtToken);

        SecurityContextHolder.clearContext();
        return ApiResponse.<String>builder()
                .code(200)
                .message("Đăng xuất thành công")
                .result("You have been logged out.")
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<?> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        if (jwtGenerator.validateRefreshToken(refreshToken)) {
            String username = jwtGenerator.getUsernameFromJWT(refreshToken);

            blackList.addToBlackListToken(refreshToken);
            SecurityContextHolder.clearContext();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtGenerator.generateToken(userDetails);
            String newRefreshToken = jwtGenerator.generateRefreshToken(username);
            return ApiResponse.<AuthResponseDTO>builder()
                    .code(200)
                    .message("Token refreshed successfully")
                    .result(new AuthResponseDTO(newAccessToken, newRefreshToken))
                    .build();
        }

        return ApiResponse.<String>builder()
                .code(401)
                .message("Invalid refresh token")
                .build();
    }

}
