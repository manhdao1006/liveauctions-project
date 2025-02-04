package com.ute.auction.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ute.auction.converter.NguoiDungConverter;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.NguoiDungEntity;
import com.ute.auction.entity.NguoiMuaEntity;
import com.ute.auction.entity.NhanVienEntity;
import com.ute.auction.entity.PhuongXaEntity;
import com.ute.auction.entity.VaiTroEntity;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.repository.NguoiDungRepository;
import com.ute.auction.repository.NguoiMuaRepository;
import com.ute.auction.repository.NhanVienRepository;
import com.ute.auction.repository.PhuongXaRepository;
import com.ute.auction.repository.VaiTroRepository;
import com.ute.auction.service.INguoiDungService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NguoiDungService implements INguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;
    private final PhuongXaRepository phuongXaRepository;
    private final VaiTroRepository vaiTroRepository;
    private final NguoiMuaRepository nguoiMuaRepository;
    private final NguoiBanRepository nguoiBanRepository;
    private final NhanVienRepository nhanVienRepository;
    private final NguoiDungConverter nguoiDungConverter;
    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    /*
     * edit profile of seller by id
     * 
     * @param id, newNguoiDung
     * 
     * @return userUpdated
     */
    @Transactional
    @Override
    public NguoiDungDTO updateProfile(long maNguoiDung, NguoiDungDTO nguoiDungDTO, MultipartFile avatar)
            throws IOException {
        NguoiDungEntity oldNguoiDung = nguoiDungRepository.findOneByMaNguoiDung(maNguoiDung)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có người dùng nào có mã người dùng là " + maNguoiDung));

        NguoiDungEntity newNguoiDung = nguoiDungConverter.toEntity(nguoiDungDTO, oldNguoiDung);

        if (nguoiDungDTO.getMaPhuongXa() != null) {
            PhuongXaEntity cityEntity = phuongXaRepository.findOneByMaPhuongXa(nguoiDungDTO.getMaPhuongXa())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không có phường xã nào với mã phường xã là " + nguoiDungDTO.getMaPhuongXa()));
            oldNguoiDung.setPhuongXa(cityEntity);
        }

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

        return nguoiDungConverter.toDTO(nguoiDungRepository.save(newNguoiDung));
    }

    /*
     * forgot password
     * 
     * @param email, password
     */
    @Transactional
    @Override
    public void forgotPassword(String email, String password) {
        NguoiDungEntity userEntity = (nguoiDungRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)));

        userEntity.setMatKhau(passwordEncoder.encode(password));
        nguoiDungRepository.save(userEntity);
    }

    /*
     * register buyer
     * 
     * @param user
     * 
     * @return buyer
     */
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

    /*
     * register seller
     * 
     * @param user
     * 
     * @return seller
     */
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

    /*
     * register staff
     * 
     * @param user
     * 
     * @return staff
     */
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

    /*
     * register admin
     * 
     * @param user
     * 
     * @return admin
     */
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
    public List<NguoiDungDTO> getAllUsersByRole(long roleId) {
        List<NguoiDungEntity> entities = nguoiDungRepository.findNguoiDungsByVaiTro(roleId);

        return entities.stream().map(nguoiDungConverter::toDTO).toList();
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
                    ObjectUtils.asMap("folder", "nguoi-dung"));

            // get info from cloudinary
            String publicId = (String) result.get("public_id");
            String url = (String) result.get("url");

            avatarInfo.put("publicId", publicId);
            avatarInfo.put("url", url);
        }

        return avatarInfo;
    }

}
