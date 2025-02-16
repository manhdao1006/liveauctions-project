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
import com.ute.auction.converter.NguoiDungConverter;
import com.ute.auction.converter.NhanVienConverter;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NhanVienDTO;
import com.ute.auction.dto.NhanVienResponseDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.entity.NguoiDungEntity;
import com.ute.auction.entity.NhanVienEntity;
import com.ute.auction.entity.PhuongXaEntity;
import com.ute.auction.entity.VaiTroEntity;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiDungRepository;
import com.ute.auction.repository.NhanVienRepository;
import com.ute.auction.repository.PhuongXaRepository;
import com.ute.auction.repository.VaiTroRepository;
import com.ute.auction.service.INhanVienService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NhanVienService implements INhanVienService {

    private final NhanVienConverter nhanVienConverter;
    private final NhanVienRepository nhanVienRepository;
    private final NguoiDungConverter nguoiDungConverter;
    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;
    private final PhuongXaRepository phuongXaRepository;
    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    @Override
    public PageResponse<NhanVienResponseDTO> getNhanViens(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("maNhanVien").descending());

        Page<NhanVienEntity> entities = nhanVienRepository.findNhanViensByTrangThaiXoa("1", pageable);
        List<NhanVienResponseDTO> responseList = new ArrayList<>();
        for (NhanVienEntity nhanVienEntity : entities) {
            NhanVienDTO nhanVienDTO = nhanVienConverter.toDTO(nhanVienEntity);

            NguoiDungEntity nguoiDungEntity = nhanVienEntity.getNguoiDung();
            NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);

            responseList.add(new NhanVienResponseDTO(nguoiDungDTO, nhanVienDTO));
        }

        return PageResponse.<NhanVienResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public NhanVienResponseDTO getNhanVienByMaNhanVien(long maNguoiDung) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findOneByMaNhanVien(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có nhân viên nào có mã nhân viên là " + maNguoiDung));
        NhanVienDTO nhanVienDTO = nhanVienConverter.toDTO(nhanVienEntity);
        NguoiDungEntity nguoiDungEntity = nhanVienEntity.getNguoiDung();
        NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);

        return new NhanVienResponseDTO(nguoiDungDTO, nhanVienDTO);
    }

    @Transactional
    @Override
    public NhanVienResponseDTO addNhanVien(NguoiDungDTO nguoiDungDTO, NhanVienDTO nhanVienDTO, MultipartFile avatar)
            throws IOException {
        Map<String, String> avatarInfo = uploadAvatar(avatar);

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));
        nguoiDungEntity.setAvatarId(avatarInfo.get("publicId"));
        nguoiDungEntity.setAvatar(avatarInfo.get("url"));
        VaiTroEntity vaiTros = vaiTroRepository.findOneByTenVaiTro("ROLE_STAFF")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(vaiTros));
        PhuongXaEntity cityEntity = phuongXaRepository.findOneByMaPhuongXa(nguoiDungDTO.getMaPhuongXa())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có phường xã nào với mã phường xã là " + nguoiDungDTO.getMaPhuongXa()));
        nguoiDungEntity.setPhuongXa(cityEntity);
        nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);

        NhanVienEntity nhanVienEntity = nhanVienConverter.toEntity(nhanVienDTO);
        nhanVienEntity.setNguoiDung(nguoiDungEntity);
        nhanVienEntity = nhanVienRepository.save(nhanVienEntity);

        return new NhanVienResponseDTO(nguoiDungConverter.toDTO(nguoiDungEntity),
                nhanVienConverter.toDTO(nhanVienEntity));
    }

    @Transactional
    @Override
    public NhanVienResponseDTO updateNhanVien(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NhanVienDTO nhanVienDTO,
            MultipartFile avatar) throws IOException {
        NguoiDungEntity oldNguoiDung = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        NhanVienEntity oldNhanVien = nhanVienRepository.findOneByMaNhanVien(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhân viên nào với mã nhân viên là " + maNguoiDung));
        NguoiDungEntity newNguoiDung = nguoiDungConverter.toEntity(nguoiDungDTO, oldNguoiDung);
        NhanVienEntity newNhanVien = nhanVienConverter.toEntity(nhanVienDTO, oldNhanVien);

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
        newNhanVien = nhanVienRepository.save(newNhanVien);

        return new NhanVienResponseDTO(nguoiDungConverter.toDTO(newNguoiDung), nhanVienConverter.toDTO(newNhanVien));
    }

    @Transactional
    @Override
    public void deleteNhanVien(long maNguoiDung) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        NhanVienEntity nhanVienEntity = nhanVienRepository.findOneByMaNhanVien(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhân viên nào với mã nhân viên là " + maNguoiDung));
        nguoiDungEntity.setTrangThaiXoa("0");
        nhanVienEntity.setTrangThaiXoa("0");
        nguoiDungRepository.save(nguoiDungEntity);
        nhanVienRepository.save(nhanVienEntity);
    }

    @Transactional
    @Override
    public void banNhanVien(long maNguoiDung) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng nào với mã người dùng là " + maNguoiDung));
        nguoiDungEntity.setTrangThaiHoatDong("Không hoạt động");
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
                    ObjectUtils.asMap("folder", "nhan-vien"));

            // get info from cloudinary
            String publicId = (String) result.get("public_id");
            String url = (String) result.get("url");

            avatarInfo.put("publicId", publicId);
            avatarInfo.put("url", url);
        }

        return avatarInfo;
    }

}
