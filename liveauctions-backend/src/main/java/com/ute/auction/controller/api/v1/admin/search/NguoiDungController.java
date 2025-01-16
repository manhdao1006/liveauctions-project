package com.ute.auction.controller.api.v1.admin.search;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.service.INguoiDungService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "user" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/users")
public class NguoiDungController {

    private final INguoiDungService userService;

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<NguoiDungDTO>> getAllUsersByRole(@PathVariable("roleId") int roleId) {
        List<NguoiDungDTO> models = userService.getAllUsersByRole(roleId);
        return ResponseEntity.ok(models);
    }

}
