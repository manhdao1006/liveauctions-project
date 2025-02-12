package com.ute.auction.controller.api.v1.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<NhaThamDinhDTO>> searchNhaThamDinh(@RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> nhaThamDinhs = nhaThamDinhService.searchNhaThamDinh(keyword, page, size);
        return ResponseEntity.ok(nhaThamDinhs);
    }

    @GetMapping("/list")
    public ResponseEntity<List<NhaThamDinhDTO>> getNhaThamDinhs() {
        List<NhaThamDinhDTO> nhaThamDinhs = nhaThamDinhService.getNhaThamDinhs();
        return ResponseEntity.ok(nhaThamDinhs);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<NhaThamDinhDTO> getNhaThamDinhByMaNhaThamDinh(@PathVariable("id") int maNhaThamDinh) {
        NhaThamDinhDTO nhaThamDinhDTO = nhaThamDinhService.getNhaThamDinhByMaNhaThamDinh(maNhaThamDinh);
        return ResponseEntity.ok(nhaThamDinhDTO);
    }

    @GetMapping("/email={email}")
    public ResponseEntity<NhaThamDinhDTO> getNhaThamDinhByEmail(@PathVariable("email") String email) {
        NhaThamDinhDTO nhaThamDinhDTO = nhaThamDinhService.getNhaThamDinhByEmail(email);
        return ResponseEntity.ok(nhaThamDinhDTO);
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
    public ResponseEntity<String> deleteNhaThamDinh(@PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        nhaThamDinhService.deleteNhaThamDinh(maNhaThamDinh);
        return ResponseEntity.ok("Xóa thành công");
    }

    @PutMapping("ban/{maNhaThamDinh}")
    public ResponseEntity<String> banNhaThamDinh(@PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        nhaThamDinhService.banNhaThamDinh(maNhaThamDinh);
        return ResponseEntity.ok("Cấm thành công!");
    }

}
