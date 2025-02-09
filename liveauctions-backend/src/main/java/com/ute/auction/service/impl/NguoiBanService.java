package com.ute.auction.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ute.auction.converter.NguoiBanConverter;
import com.ute.auction.converter.NguoiDungConverter;
import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.NguoiBanResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.entity.NguoiDungEntity;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.PhuongXaEntity;
import com.ute.auction.entity.VaiTroEntity;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.repository.NguoiDungRepository;
import com.ute.auction.repository.PhuongXaRepository;
import com.ute.auction.repository.VaiTroRepository;
import com.ute.auction.service.INguoiBanService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NguoiBanService implements INguoiBanService {

    private final NguoiBanConverter nguoiBanConverter;
    private final NguoiBanRepository nguoiBanRepository;
    private final NguoiDungConverter nguoiDungConverter;
    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;
    private final PhuongXaRepository phuongXaRepository;
    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    @Override
    public PageResponse<NguoiBanResponseDTO> getNguoiBans(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("maNguoiBan").descending());

        Page<NguoiBanEntity> entities = nguoiBanRepository.findNguoiBansByTrangThaiXoa("1", pageable);
        List<NguoiBanResponseDTO> responseList = new ArrayList<>();
        for (NguoiBanEntity nguoiBanEntity : entities) {
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            NguoiDungEntity nguoiDungEntity = nguoiBanEntity.getNguoiDung();
            NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);

            responseList.add(new NguoiBanResponseDTO(nguoiDungDTO, nguoiBanDTO));
        }

        return PageResponse.<NguoiBanResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public NguoiBanResponseDTO getNguoiBanByMaNguoiBan(long maNguoiDung) {
        NguoiBanEntity nguoiBanEntity = nguoiBanRepository.findOneByMaNguoiBan(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có người bán nào có mã người bán là " + maNguoiDung));
        NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);
        NguoiDungEntity nguoiDungEntity = nguoiBanEntity.getNguoiDung();
        NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);

        return new NguoiBanResponseDTO(nguoiDungDTO, nguoiBanDTO);
    }

    @Transactional
    @Override
    public NguoiBanResponseDTO addNguoiBan(NguoiDungDTO nguoiDungDTO, NguoiBanDTO nguoiBanDTO, MultipartFile avatar)
            throws IOException {
        Map<String, String> avatarInfo = uploadAvatar(avatar);

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));
        nguoiDungEntity.setAvatarId(avatarInfo.get("publicId"));
        nguoiDungEntity.setAvatar(avatarInfo.get("url"));
        VaiTroEntity roles = vaiTroRepository.findByTenVaiTro("ROLE_SELLER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(roles));
        PhuongXaEntity cityEntity = phuongXaRepository.findOneByMaPhuongXa(nguoiDungDTO.getMaPhuongXa())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có phường xã nào với mã phường xã là " + nguoiDungDTO.getMaPhuongXa()));
        nguoiDungEntity.setPhuongXa(cityEntity);
        nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);

        NguoiBanEntity nguoiBanEntity = nguoiBanConverter.toEntity(nguoiBanDTO);
        nguoiBanEntity.setNguoiDung(nguoiDungEntity);
        nguoiBanEntity = nguoiBanRepository.save(nguoiBanEntity);

        return new NguoiBanResponseDTO(nguoiDungConverter.toDTO(nguoiDungEntity),
                nguoiBanConverter.toDTO(nguoiBanEntity));
    }

    @Transactional
    @Override
    public NguoiBanResponseDTO updateNguoiBan(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NguoiBanDTO nguoiBanDTO,
            MultipartFile avatar) throws IOException {
        NguoiDungEntity oldNguoiDung = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        NguoiBanEntity oldNguoiBan = nguoiBanRepository.findOneByMaNguoiBan(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người bán nào với mã người bán là " + maNguoiDung));
        NguoiDungEntity newNguoiDung = nguoiDungConverter.toEntity(nguoiDungDTO, oldNguoiDung);
        NguoiBanEntity newNguoiBan = nguoiBanConverter.toEntity(nguoiBanDTO, oldNguoiBan);

        if (avatar != null && !avatar.isEmpty()) {
            if (oldNguoiDung.getMaNguoiDung() != null) {
                if (oldNguoiDung.getAvatarId() != null && !oldNguoiDung.getAvatarId().isEmpty()) {
                    cloudinary.uploader().destroy(oldNguoiDung.getAvatarId(), ObjectUtils.emptyMap());
                }
            }

            Map<String, String> avatarInfo = uploadAvatar(avatar);
            newNguoiDung.setAvatarId(avatarInfo.get("publicId"));
            newNguoiDung.setAvatar(avatarInfo.get("url"));
        } else {
            newNguoiDung.setAvatarId(oldNguoiDung.getAvatarId());
            newNguoiDung.setAvatar(oldNguoiDung.getAvatar());
        }

        if (nguoiDungDTO.getMatKhau() != null && !nguoiDungDTO.getMatKhau().isEmpty()) {
            newNguoiDung.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));
        }

        if (nguoiDungDTO.getMaPhuongXa() != null) {
            PhuongXaEntity cityEntity = phuongXaRepository.findOneByMaPhuongXa(nguoiDungDTO.getMaPhuongXa())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không có phường xã nào với mã phường xã là " + nguoiDungDTO.getMaPhuongXa()));
            newNguoiDung.setPhuongXa(cityEntity);
        }

        newNguoiDung = nguoiDungRepository.save(newNguoiDung);
        newNguoiBan = nguoiBanRepository.save(newNguoiBan);

        return new NguoiBanResponseDTO(nguoiDungConverter.toDTO(newNguoiDung), nguoiBanConverter.toDTO(newNguoiBan));
    }

    @Transactional
    @Override
    public void deleteNguoiBan(long maNguoiDung) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        NguoiBanEntity nguoiBanEntity = nguoiBanRepository.findOneByMaNguoiBan(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người bán nào với mã người bán là " + maNguoiDung));
        nguoiDungEntity.setTrangThaiXoa("0");
        nguoiBanEntity.setTrangThaiXoa("0");
        nguoiDungRepository.save(nguoiDungEntity);
        nguoiBanRepository.save(nguoiBanEntity);
    }

    @Transactional
    @Override
    public void banNguoiBan(long maNguoiDung) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        nguoiDungEntity.setTrangThaiHoatDong("Inactive");
        nguoiDungRepository.save(nguoiDungEntity);
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
                    ObjectUtils.asMap("folder", "nguoi-ban"));

            // get info from cloudinary
            String publicId = (String) result.get("public_id");
            String url = (String) result.get("url");

            avatarInfo.put("publicId", publicId);
            avatarInfo.put("url", url);
        }

        return avatarInfo;
    }

}
