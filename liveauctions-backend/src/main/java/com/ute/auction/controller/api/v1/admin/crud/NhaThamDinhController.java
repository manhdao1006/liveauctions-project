package com.ute.auction.controller.api.v1.admin.crud;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RestController(value = "appraiser" + ApiName.CRUD_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/appraiser")
public class NhaThamDinhController {

    private final INhaThamDinhService appraiserService;

    // Build API add an appraiser
    @PostMapping("/add")
    public ApiResponse<NhaThamDinhDTO> addAppraiser(@ModelAttribute NhaThamDinhDTO nhaThamDinhDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhaThamDinhDTO>builder()
                .code(200)
                .message("Save successfully!")
                .result(appraiserService.addAppraiser(nhaThamDinhDTO, file))
                .build();
    }

    // Build API edit an existed appraiser
    @PutMapping("/edit/{maNhaThamDinh}")
    public ApiResponse<NhaThamDinhDTO> updateAppraiser(@PathVariable("maNhaThamDinh") long maNhaThamDinh,
            @ModelAttribute NhaThamDinhDTO nhaThamDinhDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return ApiResponse.<NhaThamDinhDTO>builder()
                .code(200)
                .message("Update successfully!")
                .result(appraiserService.updateAppraiser(maNhaThamDinh, nhaThamDinhDTO, file))
                .build();
    }

    // Build API delete an existed appraiser
    @DeleteMapping("/delete/{maNhaThamDinh}")
    public ResponseEntity<String> deleteAppraiser(@PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        appraiserService.deleteAppraiser(maNhaThamDinh);
        return ResponseEntity.ok("Deleting appraiser is successfully");
    }

    // Build API ban an appraiser
    @PutMapping("ban/{maNhaThamDinh}")
    public ResponseEntity<String> banAppraiser(@PathVariable("maNhaThamDinh") long maNhaThamDinh) {
        appraiserService.banAppraiser(maNhaThamDinh);
        return ResponseEntity.ok("Banning appraiser is successfully!");
    }

}
