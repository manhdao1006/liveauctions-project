package com.ute.auction.controller.api.v1.admin.search;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ute.auction.dto.UserDTO;
import com.ute.auction.service.IUserService;

@RequiredArgsConstructor
@RestController(value = "user" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/role/")
    public ResponseEntity<List<UserDTO>> getAllUsersByRole(@PathVariable int roleId) {
        List<UserDTO> models = userService.getAllUsersByRole(roleId);
        return ResponseEntity.ok(models);
    }

}
