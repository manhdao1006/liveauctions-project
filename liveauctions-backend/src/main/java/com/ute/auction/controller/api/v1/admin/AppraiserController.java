package com.ute.auction.controller.api.v1.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.AppraiserDTO;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.service.IAppraiserService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/appraiser")
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

    // Build API add an appraiser
    @SuppressWarnings("null")
    @PostMapping("/add")
    public ResponseEntity<?> addAppraiser(@Valid @RequestParam(value = "name", required = false) String name,
            @Valid @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "gender", required = false) String gender,
            @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "dob", required = false) LocalDate dob,
            @RequestParam(value = "description", required = false) String description) throws IOException {
        AppraiserDTO newAppraiser = new AppraiserDTO();
        newAppraiser.setAppraiserName(name);
        newAppraiser.setEmail(email);
        newAppraiser.setGender(gender);
        newAppraiser.setPhoneNumber(phoneNumber);
        newAppraiser.setAddress(address);
        newAppraiser.setType(type);
        newAppraiser.setStatus(status);
        newAppraiser.setDob(dob);
        newAppraiser.setDescription(description);

        if (avatar != null && !avatar.isEmpty()) {
            if (!avatar.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("The file is not an image format!");
            }
            newAppraiser.setAvatar(Base64.getEncoder().encodeToString(avatar.getBytes()));
        } else {
            newAppraiser.setAvatar(null);
        }

        AppraiserDTO savedAppraiser = appraiserService.addAppraiser(newAppraiser);

        return new ResponseEntity<>(savedAppraiser, HttpStatus.OK);
    }

    // Build API edit an existed appraiser
    @SuppressWarnings("null")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateAppraiser(@PathVariable("id") int id,
            @Valid @RequestParam(value = "name", required = false) String name,
            @Valid @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "gender", required = false) String gender,
            @Valid @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "dob", required = false) LocalDate dob,
            @RequestParam(value = "description", required = false) String description) throws IOException {
        AppraiserDTO appraiserDTO = new AppraiserDTO();
        appraiserDTO.setAppraiserName(name);
        appraiserDTO.setEmail(email);
        appraiserDTO.setGender(gender);
        appraiserDTO.setPhoneNumber(phoneNumber);
        appraiserDTO.setAddress(address);
        appraiserDTO.setType(type);
        appraiserDTO.setStatus(status);
        appraiserDTO.setDob(dob);
        appraiserDTO.setDescription(description);

        if (avatar != null && !avatar.isEmpty()) {
            if (!avatar.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("The file is not an image format!");
            }
            appraiserDTO.setAvatar(Base64.getEncoder().encodeToString(avatar.getBytes()));
        } else {
            appraiserDTO.setAvatar(null);
        }

        try {
            AppraiserDTO updatedAppraiser = appraiserService.updateAppraiser(id, appraiserDTO);
            return new ResponseEntity<>(updatedAppraiser, HttpStatus.OK);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ResourceExistedException("Email already exists!");
        }
    }

    // Build API delete an existed appraiser
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppraiser(@PathVariable("id") int appraiserId) {
        appraiserService.deleteAppraiser(appraiserId);
        return ResponseEntity.ok("Deleting appraiser is successfully");
    }

    // Build API ban an appraiser
    @PutMapping("ban/{id}")
    public ResponseEntity<String> banAppraiser(@PathVariable("id") int id) {
        appraiserService.banAppraiser(id);
        return ResponseEntity.ok("Banning appraiser is successfully!");
    }

}
