package com.ute.auction.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.converter.UserConverter;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.RoleEntity;
import com.ute.auction.entity.UserEntity;
import com.ute.auction.exception.ResourceExistedException;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.CityRepository;
import com.ute.auction.repository.RoleRepository;
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
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
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
    public UserDTO updateProfile(int id, UserDTO updatedUser, MultipartFile avatar) throws IOException {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }
        userEntity.setFirstName(updatedUser.getFirstName());
        userEntity.setLastName(updatedUser.getLastName());
        userEntity.setEmail(updatedUser.getEmail());
        userEntity.setPassword(updatedUser.getPassword());
        userEntity.setPhoneNumber(updatedUser.getPhoneNumber());
        userEntity.setAddress(updatedUser.getAddress());
        userEntity.setDob(updatedUser.getDob());
        userEntity.setGender(updatedUser.getGender());

        if (updatedUser.getCity() != null && updatedUser.getCity().getCityId() != null) {
            CityEntity cityEntity = cityRepository.findById(updatedUser.getCity().getCityId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "City with id " + updatedUser.getCity().getCityId() + " is not found"));
            userEntity.setCity(cityEntity);
        }

        if (avatar != null && !avatar.isEmpty()) {
            // delete old avatar
            Path path = Paths.get(imagePath, updatedUser.getAvatar());
            Files.deleteIfExists(path);

            validateImage(avatar);
            String fileName = saveImageToFolder(avatar);
            updatedUser.setAvatar(fileName);
        }

        UserEntity userUpdated = userRepository.save(userEntity);
        return userConverter.toDTO(userUpdated);
    }

    /*
     * forgot password
     * 
     * @param email, password
     */
    @Override
    @Transactional
    public void forgotPassword(String email, String password) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        userEntity.setPassword(password);
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
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        return userConverter.toDTO(userRepository.save(userEntity));
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
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        return userConverter.toDTO(userRepository.save(userEntity));
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
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        return userConverter.toDTO(userRepository.save(userEntity));
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
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        userEntity.setRoles(Collections.singletonList(roles));

        return userConverter.toDTO(userRepository.save(userEntity));
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

}
