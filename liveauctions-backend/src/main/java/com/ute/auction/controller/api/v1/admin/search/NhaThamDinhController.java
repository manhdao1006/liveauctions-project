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
import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.service.INhaThamDinhService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "appraiser" + ApiName.SEARCH_ADMIN)
@RequestMapping(ApiUrl.API_ADMIN + "/appraiser")
public class NhaThamDinhController {

    private final INhaThamDinhService appraiserService;

    // Build API sorted asc appraiser by dob
    @GetMapping("/sorted-dob-asc")
    public ResponseEntity<List<NhaThamDinhDTO>> sortedAscByDoB(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> appraisers = appraiserService.sortedAscByDoB(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API sorted desc appraiser by dob
    @GetMapping("/sorted-dob-desc")
    public ResponseEntity<List<NhaThamDinhDTO>> sortedDescByDoB(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> appraisers = appraiserService.sortedDescByDoB(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API sorted desc appraiser by name
    @GetMapping("/sorted-name-desc")
    public ResponseEntity<List<NhaThamDinhDTO>> sortedDescByName(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> appraisers = appraiserService.sortedDescByName(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API sorted asc appraiser by name
    @GetMapping("/sorted-name-asc")
    public ResponseEntity<List<NhaThamDinhDTO>> sortedAscByName(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> appraisers = appraiserService.sortedAscByName(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API search appraiser
    @GetMapping("/search")
    public ResponseEntity<List<NhaThamDinhDTO>> searchAppraiser(@RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> appraisers = appraiserService.searchAppraiser(keyword, page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API get all appraisers
    @GetMapping("/list")
    public ResponseEntity<List<NhaThamDinhDTO>> getAll(@RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<NhaThamDinhDTO> appraisers = appraiserService.getAll(page, size);
        return ResponseEntity.ok(appraisers);
    }

    // Build API get appraiser by id
    @GetMapping("/id={id}")
    public ResponseEntity<NhaThamDinhDTO> getAppraiserById(@PathVariable("id") int appraiserId) {
        NhaThamDinhDTO appraiserDTO = appraiserService.getAppraiserById(appraiserId);
        return ResponseEntity.ok(appraiserDTO);
    }

    // Build API get appraiser by email
    @GetMapping("/email={email}")
    public ResponseEntity<NhaThamDinhDTO> getAppraiserByEmail(@PathVariable("email") String email) {
        NhaThamDinhDTO appraiserDTO = appraiserService.getAppraiserByEmail(email);
        return ResponseEntity.ok(appraiserDTO);
    }

}
