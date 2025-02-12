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

    @Override
    public List<NhaThamDinhDTO> getNhaThamDinhs() {
        List<NhaThamDinhEntity> entities = nhaThamDinhRepository.findNhaThamDinhsByTrangThaiXoa("1");
        return entities.stream().map(nhaThamDinhConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public NhaThamDinhDTO getNhaThamDinhByMaNhaThamDinh(long maNhaThamDinh) {
        NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository.findOneByMaNhaThamDinh(maNhaThamDinh)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà thẩm định nào với mã nhà thẩm định là " + maNhaThamDinh));
        NhaThamDinhDTO nhaThamDinhDTO = nhaThamDinhConverter.toDTO(nhaThamDinhEntity);
        return nhaThamDinhDTO;
    }

    @Override
    public NhaThamDinhDTO getNhaThamDinhByEmail(String email) {
        NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository.findOneByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà thẩm định nào với email là " + email));
        NhaThamDinhDTO nhaThamDinhDTO = nhaThamDinhConverter.toDTO(nhaThamDinhEntity);
        return nhaThamDinhDTO;
    }

    @Transactional
    @Override
    public NhaThamDinhDTO addNhaThamDinh(NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar) throws IOException {
        Map<String, String> avatarInfo = uploadAvatar(avatar);

        NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhConverter.toEntity(nhaThamDinhDTO);
        nhaThamDinhEntity.setAvatarId(avatarInfo.get("publicId"));
        nhaThamDinhEntity.setAvatar(avatarInfo.get("url"));
        nhaThamDinhEntity = nhaThamDinhRepository.save(nhaThamDinhEntity);
        return nhaThamDinhConverter.toDTO(nhaThamDinhEntity);
    }

    @Transactional
    @Override
    public NhaThamDinhDTO updateNhaThamDinh(long maNhaThamDinh, NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar)
            throws IOException {
        NhaThamDinhEntity oldNhaThamDinh = nhaThamDinhRepository.findOneByMaNhaThamDinh(maNhaThamDinh)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có nhà thẩm định nào có mã nhà thẩm định là " + maNhaThamDinh));

        NhaThamDinhEntity newNhaThamDinh = nhaThamDinhConverter.toEntity(nhaThamDinhDTO, oldNhaThamDinh);

        if (avatar != null && !avatar.isEmpty()) {
            if (oldNhaThamDinh.getMaNhaThamDinh() != null) {
                if (oldNhaThamDinh.getAvatarId() != null
                        && !oldNhaThamDinh.getAvatarId().isEmpty()) {
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
            NhaThamDinhEntity nhaThamDinhUpdated = nhaThamDinhRepository.save(newNhaThamDinh);
            return nhaThamDinhConverter.toDTO(nhaThamDinhUpdated);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            throw new ResourceExistedException("Email đã tồn tại!");
        }
    }

    @Transactional
    @Override
    public void deleteNhaThamDinh(long maNhaThamDinh) {
        NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository.findOneByMaNhaThamDinh(maNhaThamDinh)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà thẩm định nào với mã nhà thẩm định là " + maNhaThamDinh));
        nhaThamDinhEntity.setTrangThaiXoa("0");
        nhaThamDinhRepository.save(nhaThamDinhEntity);
    }

    @Transactional
    @Override
    public void banNhaThamDinh(long maNhaThamDinh) {
        NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository.findOneByMaNhaThamDinh(maNhaThamDinh)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà thẩm định nào với mã nhà thẩm định là " + maNhaThamDinh));
        nhaThamDinhEntity.setTrangThaiHoatDong("Inactive");
        nhaThamDinhRepository.save(nhaThamDinhEntity);
    }

    @Override
    public List<NhaThamDinhDTO> searchNhaThamDinh(String keyword, int page, int size) {
        List<NhaThamDinhEntity> nhaThamDinhExists = nhaThamDinhRepository.existsNhaThamDinh(keyword);
        if (nhaThamDinhExists.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy nhà thẩm định nào với keyword là " + keyword);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhaThamDinhEntity> entities = nhaThamDinhRepository.searchNhaThamDinh(keyword, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("Không tìm thấy nhà thẩm định nào với page là " + page);
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
