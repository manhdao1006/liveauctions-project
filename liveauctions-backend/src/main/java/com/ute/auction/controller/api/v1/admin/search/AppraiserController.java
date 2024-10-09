package com.ute.auction.controller.api.v1.admin.search;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.AppraiserDTO;
import com.ute.auction.service.IAppraiserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "appraiser" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/appraiser")
public class AppraiserController {

    private final IAppraiserService appraiserService;

    // Build API sorted asc appraiser by dob
    @GetMapping("/sorted-dob-asc")
    public ResponseEntity<List<AppraiserDTO>> sortedAscByDoB(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AppraiserDTO> appraisers = appraiserService.sortedAscByDoB(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API sorted desc appraiser by dob
    @GetMapping("/sorted-dob-desc")
    public ResponseEntity<List<AppraiserDTO>> sortedDescByDoB(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AppraiserDTO> appraisers = appraiserService.sortedDescByDoB(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API sorted desc appraiser by name
    @GetMapping("/sorted-name-desc")
    public ResponseEntity<List<AppraiserDTO>> sortedDescByName(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AppraiserDTO> appraisers = appraiserService.sortedDescByName(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API sorted asc appraiser by name
    @GetMapping("/sorted-name-asc")
    public ResponseEntity<List<AppraiserDTO>> sortedAscByName(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AppraiserDTO> appraisers = appraiserService.sortedAscByName(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API search appraiser
    @GetMapping("/search")
    public ResponseEntity<List<AppraiserDTO>> searchAppraiser(@RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AppraiserDTO> appraisers = appraiserService.searchAppraiser(keyword, page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API get all appraisers
    @GetMapping("/list")
    public ResponseEntity<List<AppraiserDTO>> getAll(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AppraiserDTO> appraisers = appraiserService.getAll(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API get appraiser by id
    @GetMapping("/id={id}")
    public ResponseEntity<AppraiserDTO> getAppraiserById(@PathVariable("id") int appraiserId) {
        AppraiserDTO appraiserDTO = appraiserService.getAppraiserById(appraiserId);
        return ResponseEntity.ok(appraiserDTO);
    }

    // Build API get appraiser by email
    @GetMapping("/email={email}")
    public ResponseEntity<AppraiserDTO> getAppraiserByEmail(@PathVariable("email") String email) {
        AppraiserDTO appraiserDTO = appraiserService.getAppraiserByEmail(email);
        return ResponseEntity.ok(appraiserDTO);
    }

}
