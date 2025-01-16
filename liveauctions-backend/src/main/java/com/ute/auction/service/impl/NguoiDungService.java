package com.ute.auction.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private final NguoiDungRepository userRepository;
    private final PhuongXaRepository cityRepository;
    private final VaiTroRepository roleRepository;
    private final NguoiMuaRepository buyerRepository;
    private final NguoiBanRepository sellerRepository;
    private final NhanVienRepository staffRepository;
    private final NguoiDungConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.images.path}")
    private String imagePath;

    /*
     * edit profile of seller by id
     * 
     * @param id, updatedUser
     * 
     * @return userUpdated
     */
    @Override
    @Transactional
    public NguoiDungDTO updateProfile(long id, NguoiDungDTO userDTO, MultipartFile avatar) throws IOException {
        NguoiDungEntity oldUser = userRepository.findByUserId(id);
        if (oldUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }

        if (userDTO.getMaPhuongXa() != null) {
            PhuongXaEntity cityEntity = cityRepository.findOneByMaPhuongXa(userDTO.getMaPhuongXa())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "City with id " + userDTO.getMaPhuongXa() + " is not found"));
            oldUser.setPhuongXa(cityEntity);
        }

        if (avatar != null && !avatar.isEmpty()) {
            if (userDTO.getAvatar() != null && !userDTO.getAvatar().isEmpty()) {
                // delete old avatar
                System.out.println(userDTO.getAvatar());
                Path path = Paths.get(imagePath, userDTO.getAvatar());
                Files.deleteIfExists(path);
            }

            validateImage(avatar);
            String fileName = saveImageToFolder(avatar);
            oldUser.setAvatar(fileName);
        }

        NguoiDungEntity updatedUser = userConverter.toEntity(userDTO, oldUser);
        return userConverter.toDTO(userRepository.save(updatedUser));
    }

    /*
     * forgot password
     * 
     * @param email, password
     */
    @Override
    @Transactional
    public void forgotPassword(String email, String password) {
        NguoiDungEntity userEntity = (userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)));

        userEntity.setMatKhau(passwordEncoder.encode(password));
        userRepository.save(userEntity);
    }

    /*
     * register buyer
     * 
     * @param user
     * 
     * @return buyer
     */
    @Override
    @Transactional
    public NguoiDungDTO register(NguoiDungDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(userDTO.getMatKhau()));

        VaiTroEntity roles = roleRepository.findByTenVaiTro("ROLE_BUYER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = userRepository.save(userEntity);

        NguoiMuaEntity buyerEntity = new NguoiMuaEntity();
        buyerEntity.setNguoiDung(savedUserEntity);
        buyerRepository.save(buyerEntity);

        return userConverter.toDTO(savedUserEntity);
    }

    /*
     * register seller
     * 
     * @param user
     * 
     * @return seller
     */
    @Override
    public NguoiDungDTO registerSeller(NguoiDungDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(userDTO.getMatKhau()));

        VaiTroEntity roles = roleRepository.findByTenVaiTro("ROLE_SELLER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = userRepository.save(userEntity);

        NguoiBanEntity sellerEntity = new NguoiBanEntity();
        sellerEntity.setNguoiDung(savedUserEntity);
        sellerRepository.save(sellerEntity);

        return userConverter.toDTO(savedUserEntity);
    }

    /*
     * register staff
     * 
     * @param user
     * 
     * @return staff
     */
    @Override
    public NguoiDungDTO registerStaff(NguoiDungDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(userDTO.getMatKhau()));

        VaiTroEntity roles = roleRepository.findByTenVaiTro("ROLE_STAFF")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = userRepository.save(userEntity);

        NhanVienEntity staffEntity = new NhanVienEntity();
        staffEntity.setNguoiDung(savedUserEntity);
        staffRepository.save(staffEntity);

        return userConverter.toDTO(savedUserEntity);
    }

    /*
     * register admin
     * 
     * @param user
     * 
     * @return admin
     */
    @Override
    public NguoiDungDTO registerAdmin(NguoiDungDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        NguoiDungEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setMatKhau(passwordEncoder.encode(userDTO.getMatKhau()));

        VaiTroEntity roles = roleRepository.findByTenVaiTro("ROLE_ADMIN")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setVaiTros(Collections.singletonList(roles));

        NguoiDungEntity savedUserEntity = userRepository.save(userEntity);

        NhanVienEntity staffEntity = new NhanVienEntity();
        staffEntity.setNguoiDung(savedUserEntity);
        staffRepository.save(staffEntity);

        return userConverter.toDTO(savedUserEntity);
    }

    // save image to folder
    private String saveImageToFolder(MultipartFile imageFIle) throws IOException {
        try {
            String fileName = System.currentTimeMillis() + "_" + imageFIle.getOriginalFilename();
            Path path = Paths.get(imagePath, fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, imageFIle.getBytes());

            return fileName;
        } catch (IOException e) {
            throw new ResourceNotFormatException("Error saving file: " + e.getMessage());
        }
    }

    // validate image
    @SuppressWarnings("null")
    private void validateImage(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        if (!contentType.startsWith("image/")) {
            throw new ResourceNotFormatException("Uploaded file is not an image");
        }
    }

    @Override
    public List<NguoiDungDTO> getAllUsersByRole(long roleId) {
        List<NguoiDungEntity> entities = userRepository.findNguoiDungsByVaiTro(roleId);

        return entities.stream().map(userConverter::toDTO).toList();
    }

}
