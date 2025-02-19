package com.ute.auction.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.NguoiDungConverter;
import com.ute.auction.converter.VaiTroConverter;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NguoiDungResponseDTO;
import com.ute.auction.dto.VaiTroDTO;
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
    private final VaiTroConverter vaiTroConverter;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void forgotPassword(String email, String password) {
        NguoiDungEntity nguoiDungEntity = (nguoiDungRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)));

        nguoiDungEntity.setMatKhau(passwordEncoder.encode(password));
        nguoiDungRepository.save(nguoiDungEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO register(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setEmail(nguoiDungDTO.getEmail());
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity vaiTros = vaiTroRepository.findOneByTenVaiTro("ROLE_BUYER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(vaiTros));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(nguoiDungEntity);

        NguoiMuaEntity nguoiMuaEntity = new NguoiMuaEntity();
        nguoiMuaEntity.setNguoiDung(savedUserEntity);
        nguoiMuaRepository.save(nguoiMuaEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO registerSeller(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setEmail(nguoiDungDTO.getEmail());
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity vaiTros = vaiTroRepository.findOneByTenVaiTro("ROLE_SELLER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(vaiTros));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(nguoiDungEntity);

        NguoiBanEntity nguoiBanEntity = new NguoiBanEntity();
        nguoiBanEntity.setNguoiDung(savedUserEntity);
        nguoiBanRepository.save(nguoiBanEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO registerStaff(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setEmail(nguoiDungDTO.getEmail());
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity vaiTros = vaiTroRepository.findOneByTenVaiTro("ROLE_STAFF")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(vaiTros));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(nguoiDungEntity);

        NhanVienEntity nhanVienEntity = new NhanVienEntity();
        nhanVienEntity.setNguoiDung(savedUserEntity);
        nhanVienRepository.save(nhanVienEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Transactional
    @Override
    public NguoiDungDTO registerAdmin(NguoiDungDTO nguoiDungDTO) {
        if (nguoiDungRepository.existsByEmail(nguoiDungDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity nguoiDungEntity = nguoiDungConverter.toEntity(nguoiDungDTO);
        nguoiDungEntity.setEmail(nguoiDungDTO.getEmail());
        nguoiDungEntity.setMatKhau(passwordEncoder.encode(nguoiDungDTO.getMatKhau()));

        VaiTroEntity vaiTros = vaiTroRepository.findOneByTenVaiTro("ROLE_ADMIN")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        nguoiDungEntity.setVaiTros(Collections.singletonList(vaiTros));

        NguoiDungEntity savedUserEntity = nguoiDungRepository.save(nguoiDungEntity);

        NhanVienEntity nhanVienEntity = new NhanVienEntity();
        nhanVienEntity.setNguoiDung(savedUserEntity);
        nhanVienRepository.save(nhanVienEntity);

        return nguoiDungConverter.toDTO(savedUserEntity);
    }

    @Override
    public List<NguoiDungDTO> getNguoiDungsByMaVaiTro(long maVaiTro) {
        List<NguoiDungEntity> entities = nguoiDungRepository.findNguoiDungsByVaiTro(maVaiTro, "1");

        return entities.stream().map(nguoiDungConverter::toDTO).toList();
    }

    @Override
    public NguoiDungResponseDTO getNguoiDungByMaNguoiDung(long maNguoiDung) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng với mã người dùng là " + maNguoiDung));
        NguoiDungDTO nguoiDungDTO = nguoiDungConverter.toDTO(nguoiDungEntity);
        List<VaiTroEntity> vaiTros = nguoiDungEntity.getVaiTros();
        VaiTroDTO vaiTroDTO = vaiTros.isEmpty() ? null : vaiTroConverter.toDTO(vaiTros.get(0));

        return new NguoiDungResponseDTO(nguoiDungDTO, vaiTroDTO);
    }

    @Override
    public NguoiDungDTO getNguoiDungByEmail(String email) {
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng với email là " + email));
        return nguoiDungConverter.toDTO(nguoiDungEntity);
    }

}
