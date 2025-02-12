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
import com.ute.auction.converter.NguoiMuaConverter;
import com.ute.auction.converter.NguoiDungConverter;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.dto.NguoiMuaResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.entity.NguoiMuaEntity;
import com.ute.auction.entity.NguoiDungEntity;
import com.ute.auction.entity.PhuongXaEntity;
import com.ute.auction.entity.VaiTroEntity;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiMuaRepository;
import com.ute.auction.repository.NguoiDungRepository;
import com.ute.auction.repository.PhuongXaRepository;
import com.ute.auction.repository.VaiTroRepository;
import com.ute.auction.service.INguoiMuaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NguoiMuaService implements INguoiMuaService {

    private final NguoiMuaConverter nguoiMuaConverter;
    private final NguoiMuaRepository nguoiMuaRepository;
    private final NguoiDungConverter nguoiDungConverter;
    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;
    private final PhuongXaRepository phuongXaRepository;
    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    @Override
    public PageResponse<NguoiMuaResponseDTO> getNguoiMuas(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("maNguoiMua").descending());

        Page<NguoiMuaEntity> entities = nguoiMuaRepository.findNguoiMuasByTrangThaiXoa("1", pageable);
        List<NguoiMuaResponseDTO> responseList = new ArrayList<>();
        for (NguoiMuaEntity nguoiMuaEntity : entities) {
            NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);

            NguoiDungEntity nguoiDungEntity = nguoiMuaEntity.getNguoiDung();
            NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);

            responseList.add(new NguoiMuaResponseDTO(nguoiDungDTO, nguoiMuaDTO));
        }

        return PageResponse.<NguoiMuaResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public NguoiMuaResponseDTO getNguoiMuaByMaNguoiMua(long maNguoiDung) {
        NguoiMuaEntity nguoiMuaEntity = nguoiMuaRepository.findOneByMaNguoiMua(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có người mua nào có mã người mua là " + maNguoiDung));
        NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);
        NguoiDungEntity nguoiDungEntity = nguoiMuaEntity.getNguoiDung();
        NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);

        return new NguoiMuaResponseDTO(nguoiDungDTO, nguoiMuaDTO);
    }

    @Transactional
    @Override
    public NguoiMuaResponseDTO addNguoiMua(NguoiDungDTO nguoiDungDTO, NguoiMuaDTO nguoiMuaDTO, MultipartFile avatar)
            throws IOException {
        Map<String, String> avatarInfo = uploadAvatar(avatar);

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));
        nguoiDungEntity.setAvatarId(avatarInfo.get("publicId"));
        nguoiDungEntity.setAvatar(avatarInfo.get("url"));
        VaiTroEntity roles = vaiTroRepository.findByTenVaiTro("ROLE_BUYER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(roles));
        PhuongXaEntity cityEntity = phuongXaRepository.findOneByMaPhuongXa(nguoiDungDTO.getMaPhuongXa())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có phường xã nào với mã phường xã là " + nguoiDungDTO.getMaPhuongXa()));
        nguoiDungEntity.setPhuongXa(cityEntity);
        nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);

        NguoiMuaEntity nguoiMuaEntity = nguoiMuaConverter.toEntity(nguoiMuaDTO);
        nguoiMuaEntity.setNguoiDung(nguoiDungEntity);
        nguoiMuaEntity = nguoiMuaRepository.save(nguoiMuaEntity);

        return new NguoiMuaResponseDTO(nguoiDungConverter.toDTO(nguoiDungEntity),
                nguoiMuaConverter.toDTO(nguoiMuaEntity));
    }

    @Transactional
    @Override
    public NguoiMuaResponseDTO updateNguoiMua(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NguoiMuaDTO nguoiMuaDTO,
            MultipartFile avatar) throws IOException {
        NguoiDungEntity oldNguoiDung = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        NguoiMuaEntity oldNguoiMua = nguoiMuaRepository.findOneByMaNguoiMua(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người mua nào với mã người mua là " + maNguoiDung));
        NguoiDungEntity newNguoiDung = nguoiDungConverter.toEntity(nguoiDungDTO, oldNguoiDung);
        NguoiMuaEntity newNguoiMua = nguoiMuaConverter.toEntity(nguoiMuaDTO, oldNguoiMua);

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
        newNguoiMua = nguoiMuaRepository.save(newNguoiMua);

        return new NguoiMuaResponseDTO(nguoiDungConverter.toDTO(newNguoiDung), nguoiMuaConverter.toDTO(newNguoiMua));
    }

    @Transactional
    @Override
    public void deleteNguoiMua(long maNguoiDung) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        NguoiMuaEntity nguoiMuaEntity = nguoiMuaRepository.findOneByMaNguoiMua(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người mua nào với mã người mua là " + maNguoiDung));
        nguoiDungEntity.setTrangThaiXoa("0");
        nguoiMuaEntity.setTrangThaiXoa("0");
        nguoiDungRepository.save(nguoiDungEntity);
        nguoiMuaRepository.save(nguoiMuaEntity);
    }

    @Transactional
    @Override
    public void banNguoiMua(long maNguoiDung) {
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
                    ObjectUtils.asMap("folder", "nguoi-mua"));

            // get info from cloudinary
            String publicId = (String) result.get("public_id");
            String url = (String) result.get("url");

            avatarInfo.put("publicId", publicId);
            avatarInfo.put("url", url);
        }

        return avatarInfo;
    }

}
