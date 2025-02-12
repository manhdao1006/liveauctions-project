package com.ute.auction.controller.web.buyer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.LichSuDauGiaDTO;
import com.ute.auction.dto.LichSuDauGiaResponseDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.service.ILichSuDauGiaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "lichSuDauGiaNguoiMua" + ApiName.WEB)
@RequestMapping(ApiUrl.API_BUYER + "/lich-su-dau-gia")
public class LichSuDauGiaController {

    private final ILichSuDauGiaService lichSuDauGiaService;

    @GetMapping("/list/{maNguoiDung}")
    public ApiResponse<PageResponse<LichSuDauGiaResponseDTO>> getLichSuDauGiasByMaNguoiMua(
            @PathVariable("maNguoiDung") long maNguoiDung,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<LichSuDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách lịch sử đấu giá của người mua có mã là " + maNguoiDung)
                .result(lichSuDauGiaService.getLichSuDauGiasByMaNguoiMua(maNguoiDung, page, size))
                .build();
    }

    @GetMapping("/list/{maNguoiDung}/{trangThaiDonHang}")
    public ApiResponse<PageResponse<LichSuDauGiaResponseDTO>> getByTrangThaiDonHangNguoiMua(
            @PathVariable("maNguoiDung") long maNguoiDung,
            @PathVariable("trangThaiDonHang") String trangThaiDonHang,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return ApiResponse.<PageResponse<LichSuDauGiaResponseDTO>>builder()
                .code(200)
                .message("Danh sách lịch sử đấu giá của người mua có mã là " + maNguoiDung)
                .result(lichSuDauGiaService.getByTrangThaiDonHangNguoiMua(maNguoiDung, trangThaiDonHang,
                        page, size))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<LichSuDauGiaResponseDTO> addLichSuDauGia(@RequestBody LichSuDauGiaDTO lichSuDauGiaDTO) {
        return ApiResponse.<LichSuDauGiaResponseDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(lichSuDauGiaService.addLichSuDauGia(lichSuDauGiaDTO))
                .build();
    }

    @PutMapping("/edit/{maPhienDauGia}/{maSanPham}/{maNguoiMua}/{maChiPhi}")
    public ApiResponse<LichSuDauGiaResponseDTO> updateLichSuDauGia(
            @PathVariable long maPhienDauGia,
            @PathVariable String maSanPham,
            @PathVariable long maNguoiMua,
            @PathVariable long maChiPhi,
            @RequestBody LichSuDauGiaDTO lichSuDauGiaDTO) {
        return ApiResponse.<LichSuDauGiaResponseDTO>builder()
                .code(200)
                .message("Cập nhật thành công")
                .result(lichSuDauGiaService.updateLichSuDauGia(maPhienDauGia, maSanPham, maNguoiMua,
                        maChiPhi, lichSuDauGiaDTO))
                .build();
    }

}
