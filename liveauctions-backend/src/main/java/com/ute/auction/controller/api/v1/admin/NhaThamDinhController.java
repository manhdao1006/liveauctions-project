package com.ute.auction.controller.api.v1.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.ApiResponse;
import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.service.INhaThamDinhService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "nhaThamDinh" + ApiName.API)
@RequestMapping(ApiUrl.API_ADMIN + "/nha-tham-dinh")
public class NhaThamDinhController {

    private final INhaThamDinhService nhaThamDinhService;

    @GetMapping("/search")
    public ApiResponse<List<NhaThamDinhDTO>> searchNhaThamDinh(@RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<List<NhaThamDinhDTO>>builder()
                .code(200)
                .message("Danh sách nhà thẩm định")
                .result(nhaThamDinhService.searchNhaThamDinh(keyword, page, size))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<List<NhaThamDinhDTO>> getNhaThamDinhs() {
        return ApiResponse.<List<NhaThamDinhDTO>>builder()
                .code(200)
                .message("Danh sách nhà thẩm định")
                .result(nhaThamDinhService.getNhaThamDinhs())
                .build();
    }

    @GetMapping("/maNhaThamDinh={maNhaThamDinh}")
    public ApiResponse<NhaThamDinhDTO> getNhaThamDinhByMaNhaThamDinh(
            @PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        return ApiResponse.<NhaThamDinhDTO>builder()
                .code(200)
                .message("Nhà thẩm định với mã nhà thẩm định là " + maNhaThamDinh)
                .result(nhaThamDinhService.getNhaThamDinhByMaNhaThamDinh(maNhaThamDinh))
                .build();
    }

    @GetMapping("/email={email}")
    public ApiResponse<NhaThamDinhDTO> getNhaThamDinhByEmail(@PathVariable("email") String email) {
        return ApiResponse.<NhaThamDinhDTO>builder()
                .code(200)
                .message("Nhà thẩm định với email là " + email)
                .result(nhaThamDinhService.getNhaThamDinhByEmail(email))
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<NhaThamDinhDTO> addNhaThamDinh(@ModelAttribute NhaThamDinhDTO nhaThamDinhDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhaThamDinhDTO>builder()
                .code(200)
                .message("Thêm mới thành công")
                .result(nhaThamDinhService.addNhaThamDinh(nhaThamDinhDTO, file))
                .build();
    }

    @PutMapping("/edit/{maNhaThamDinh}")
    public ApiResponse<NhaThamDinhDTO> updateNhaThamDinh(@PathVariable("maNhaThamDinh") long maNhaThamDinh,
            @ModelAttribute NhaThamDinhDTO nhaThamDinhDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhaThamDinhDTO>builder()
                .code(200)
                .message("Cập nhật thành công!")
                .result(nhaThamDinhService.updateNhaThamDinh(maNhaThamDinh, nhaThamDinhDTO, file))
                .build();
    }

    @PutMapping("/delete/{maNhaThamDinh}")
    public ApiResponse<String> deleteNhaThamDinh(@PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        nhaThamDinhService.deleteNhaThamDinh(maNhaThamDinh);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa thành công!")
                .build();
    }

    @PutMapping("ban/{maNhaThamDinh}")
    public ApiResponse<String> banNhaThamDinh(@PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        nhaThamDinhService.banNhaThamDinh(maNhaThamDinh);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cấm thành công!")
                .build();
    }

}
