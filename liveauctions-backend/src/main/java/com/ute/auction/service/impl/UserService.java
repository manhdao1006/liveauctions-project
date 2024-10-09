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

import com.ute.auction.converter.UserConverter;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.BuyerEntity;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.RoleEntity;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.StaffEntity;
import com.ute.auction.entity.UserEntity;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.BuyerRepository;
import com.ute.auction.repository.CityRepository;
import com.ute.auction.repository.RoleRepository;
import com.ute.auction.repository.SellerRepository;
import com.ute.auction.repository.StaffRepository;
import com.ute.auction.repository.UserRepository;
import com.ute.auction.service.IUserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final RoleRepository roleRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final StaffRepository staffRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.images.path}")
    private String imagePath;

    /*
     * get seller by id
     * 
     * @param id
     * 
     * @return seller
     */
    @Override
    public UserDTO getUserById(int id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        UserDTO userDTO = userConverter.toDTO(userEntity);
        return userDTO;
    }

    /*
     * get seller by email
     * 
     * @param email
     * 
     * @return seller
     */
    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        UserDTO userDTO = userConverter.toDTO(userEntity);
        return userDTO;
    }

    /*
     * edit profile of seller by id
     * 
     * @param id, updatedUser
     * 
     * @return userUpdated
     */
    @Override
    @Transactional
    public UserDTO updateProfile(int id, UserDTO userDTO, MultipartFile avatar) throws IOException {
        UserEntity oldUser = userRepository.findByUserId(id);
        if (oldUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }

        if (userDTO.getCity() != null && userDTO.getCity().getCityId() != null) {
            CityEntity cityEntity = cityRepository.findById(userDTO.getCity().getCityId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "City with id " + userDTO.getCity().getCityId() + " is not found"));
            oldUser.setCity(cityEntity);
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

        UserEntity updatedUser = userConverter.toEntity(userDTO, oldUser);
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
        UserEntity userEntity = (userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)));

        userEntity.setPassword(passwordEncoder.encode(password));
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
    public UserDTO register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        UserEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        RoleEntity roles = roleRepository.findByRoleName("ROLE_BUYER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        UserEntity savedUserEntity = userRepository.save(userEntity);

        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setUser(savedUserEntity);
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
    public UserDTO registerSeller(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        UserEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        RoleEntity roles = roleRepository.findByRoleName("ROLE_SELLER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        UserEntity savedUserEntity = userRepository.save(userEntity);

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setUser(savedUserEntity);
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
    public UserDTO registerStaff(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        UserEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        RoleEntity roles = roleRepository.findByRoleName("ROLE_STAFF")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        UserEntity savedUserEntity = userRepository.save(userEntity);

        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setUser(savedUserEntity);
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
    public UserDTO registerAdmin(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ResourceExistedException("Email is taken!");
        }

        UserEntity userEntity = userConverter.toEntity(userDTO);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        RoleEntity roles = roleRepository.findByRoleName("ROLE_ADMIN")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        UserEntity savedUserEntity = userRepository.save(userEntity);

        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setUser(savedUserEntity);
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
    public List<UserDTO> getAllUsersByRole(int roleId) {
        List<UserEntity> entities = userRepository.findUsersByRole(roleId);

        return entities.stream().map(userConverter::toDTO).toList();
    }

}
