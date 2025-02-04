package com.ute.auction.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ute.auction.converter.NhaThamDinhConverter;
import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.entity.NhaThamDinhEntity;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NhaThamDinhRepository;
import com.ute.auction.service.INhaThamDinhService;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NhaThamDinhService implements INhaThamDinhService {

    private final NhaThamDinhRepository nhaThamDinhRepository;
    private final NhaThamDinhConverter nhaThamDinhConverter;
    private final Cloudinary cloudinary;

    /*
     * get all appraisers
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.findAll(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }
        return entities.stream().map(nhaThamDinhConverter::toDTO).collect(Collectors.toList());
    }

    /*
     * get an appraiser by id
     * 
     * @param id
     * 
     * @return appraiser
     */
    @Override
    public NhaThamDinhDTO getAppraiserById(long id) {
        NhaThamDinhEntity appraiserEntity = nhaThamDinhRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " not found"));
        NhaThamDinhDTO appraiserDTO = nhaThamDinhConverter.toDTO(appraiserEntity);
        return appraiserDTO;
    }

    /*
     * get an appraiser by email
     * 
     * @param email
     * 
     * @return appraiser
     */
    @Override
    public NhaThamDinhDTO getAppraiserByEmail(String email) {
        NhaThamDinhEntity appraiserEntity = nhaThamDinhRepository.findOneByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + email + " not found"));
        NhaThamDinhDTO appraiserDTO = nhaThamDinhConverter.toDTO(appraiserEntity);
        return appraiserDTO;
    }

    /*
     * add an appraiser
     * 
     * @param appraiser
     * 
     * @return appraiser
     */
    @Transactional
    @Override
    public NhaThamDinhDTO addAppraiser(NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar) throws IOException {
        Map<String, String> avatarInfo = uploadAvatar(avatar);

        NhaThamDinhEntity appraiserEntity = nhaThamDinhConverter.toEntity(nhaThamDinhDTO);
        appraiserEntity.setAvatarId(avatarInfo.get("publicId"));
        appraiserEntity.setAvatar(avatarInfo.get("url"));
        appraiserEntity = nhaThamDinhRepository.save(appraiserEntity);
        return nhaThamDinhConverter.toDTO(appraiserEntity);
    }

    /*
     * edit an existed appraiser
     * 
     * @param id, updatedAppraiser
     * 
     * @return appraiserUpdated
     */
    @Transactional
    @Override
    public NhaThamDinhDTO updateAppraiser(long maNhaThamDinh, NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar)
            throws IOException {
        NhaThamDinhEntity oldNhaThamDinh = nhaThamDinhRepository.findOneByMaNhaThamDinh(maNhaThamDinh)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có nhà thẩm định nào có mã nhà thẩm định là " + maNhaThamDinh));

        NhaThamDinhEntity newNhaThamDinh = nhaThamDinhConverter.toEntity(nhaThamDinhDTO, oldNhaThamDinh);

        if (avatar != null && !avatar.isEmpty()) {
            if (oldNhaThamDinh.getMaNhaThamDinh() != null) {
                if (oldNhaThamDinh.getAvatarId() != null && !oldNhaThamDinh.getAvatarId().isEmpty()) {
                    cloudinary.uploader().destroy(oldNhaThamDinh.getAvatarId(), ObjectUtils.emptyMap());
                }
            }

            Map<String, String> avatarInfo = uploadAvatar(avatar);
            newNhaThamDinh.setAvatarId(avatarInfo.get("publicId"));
            newNhaThamDinh.setAvatar(avatarInfo.get("url"));
        } else {
            newNhaThamDinh.setAvatarId(oldNhaThamDinh.getAvatarId());
            newNhaThamDinh.setAvatar(oldNhaThamDinh.getAvatar());
        }

        try {
            NhaThamDinhEntity appraiserUpdated = nhaThamDinhRepository.save(newNhaThamDinh);
            return nhaThamDinhConverter.toDTO(appraiserUpdated);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ResourceExistedException("Email already exists!");
        }
    }

    /*
     * delete an existed appraiser
     * 
     * @param id
     */
    @Transactional
    @Override
    public void deleteAppraiser(long id) {
        NhaThamDinhEntity appraiserEntity = nhaThamDinhRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        if (appraiserEntity != null) {
            nhaThamDinhRepository.deleteById(id);
        }
    }

    /*
     * ban an existed appraiser
     * 
     * @param id
     */
    @Transactional
    @Override
    public void banAppraiser(long id) {
        NhaThamDinhEntity appraiserEntity = nhaThamDinhRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appraiser with " + id + " is not found"));
        appraiserEntity.setTrangThaiHoatDong("Inactive");
        nhaThamDinhRepository.save(appraiserEntity);
    }

    /*
     * search appraiser
     * 
     * @param keyword, page, size
     * 
     * @return appraiser
     */
    @Override
    public List<NhaThamDinhDTO> searchAppraiser(String keyword, int page, int size) {
        List<NhaThamDinhEntity> appraiserExists = nhaThamDinhRepository.existsAppraiser(keyword);
        if (appraiserExists.isEmpty()) {
            throw new ResourceNotFoundException("No appraisers with keyword: " +
                    keyword);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.searchAppraiser(keyword, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No appraisers with page: " + page);
        }

        return entities.stream()
                .map(nhaThamDinhConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser asc by name
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedAscByName(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.sortedAscByName(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(nhaThamDinhConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser desc by name
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedDescByName(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.sortedDescByName(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(nhaThamDinhConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser from young to old by dob
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedAscByDoB(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.sortedAscByDoB(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(nhaThamDinhConverter::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * sorted appraiser from old to young by dob
     * 
     * @param page, size
     * 
     * @return appraisers
     */
    @Override
    public List<NhaThamDinhDTO> sortedDescByDoB(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.sortedDescByDoB(pageable);
        if (entities.isEmpty()) {
            if (page > entities.getTotalPages() || page <= 0) {
                throw new ResourceNotFoundException("No appraisers with page: " + page);
            }
        }

        return entities.stream()
                .map(nhaThamDinhConverter::toDTO)
                .collect(Collectors.toList());
    }

    @SuppressWarnings({ "null", "unchecked" })
    private Map<String, String> uploadAvatar(MultipartFile avatar) throws IOException {
        Map<String, String> avatarInfo = new HashMap<>();

        // check valid image
        if (avatar == null || avatar.isEmpty()) {
            avatarInfo.put("publicId", null);
            avatarInfo.put("url", null);
        } else {
            if (!avatar.getContentType().startsWith("image/")) {
                throw new ResourceNotFormatException("Phải là file ảnh!");
            }
            // upload image
            Map<String, Object> result = cloudinary.uploader().upload(avatar.getBytes(),
                    ObjectUtils.asMap("folder", "nha-tham-dinh"));

            // get info from cloudinary
            String publicId = (String) result.get("public_id");
            String url = (String) result.get("url");

            avatarInfo.put("publicId", publicId);
            avatarInfo.put("url", url);
        }

        return avatarInfo;
    }

}
