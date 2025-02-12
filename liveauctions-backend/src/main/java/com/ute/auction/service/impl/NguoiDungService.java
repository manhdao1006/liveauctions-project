package com.ute.auction.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.NguoiDungConverter;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.NguoiDungEntity;
import com.ute.auction.entity.NguoiMuaEntity;
import com.ute.auction.entity.NhanVienEntity;
import com.ute.auction.entity.VaiTroEntity;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.repository.NguoiDungRepository;
import com.ute.auction.repository.NguoiMuaRepository;
import com.ute.auction.repository.NhanVienRepository;
import com.ute.auction.repository.VaiTroRepository;
import com.ute.auction.service.INguoiDungService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NguoiDungService implements INguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;
    private final NguoiMuaRepository nguoiMuaRepository;
    private final NguoiBanRepository nguoiBanRepository;
    private final NhanVienRepository nhanVienRepository;
    private final NguoiDungConverter nguoiDungConverter;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void forgotPassword(String email, String password) {
        NguoiDungEntity userEntity = (nguoiDungRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)));

        userEntity.setMatKhau(passwordEncoder.encode(password));
        nguoiDungRepository.save(userEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO register(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        userEntity.setEmail(nguoiDungDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity roles = vaiTroRepository.findByTenVaiTro("ROLE_BUYER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(userEntity);

        NguoiMuaEntity buyerEntity = new NguoiMuaEntity();
        buyerEntity.setNguoiDung(savedUserEntity);
        nguoiMuaRepository.save(buyerEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO registerSeller(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        userEntity.setEmail(nguoiDungDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity roles = vaiTroRepository.findByTenVaiTro("ROLE_SELLER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(userEntity);

        NguoiBanEntity sellerEntity = new NguoiBanEntity();
        sellerEntity.setNguoiDung(savedUserEntity);
        nguoiBanRepository.save(sellerEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO registerStaff(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        userEntity.setEmail(nguoiDungDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity roles = vaiTroRepository.findByTenVaiTro("ROLE_STAFF")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(userEntity);

        NhanVienEntity staffEntity = new NhanVienEntity();
        staffEntity.setNguoiDung(savedUserEntity);
        nhanVienRepository.save(staffEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO registerAdmin(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        userEntity.setEmail(nguoiDungDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity roles = vaiTroRepository.findByTenVaiTro("ROLE_ADMIN")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(userEntity);

        NhanVienEntity staffEntity = new NhanVienEntity();
        staffEntity.setNguoiDung(savedUserEntity);
        nhanVienRepository.save(staffEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Override
    public List<NguoiDungDTO> getNguoiDungsByMaVaiTro(long maVaiTro) {
        List<NguoiDungEntity> entities = nguoiDungRepository.findNguoiDungsByVaiTro(maVaiTro);

        return entities.stream().map(nguoiDungConverter::toDTO).toList();
    }

}
