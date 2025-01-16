package com.ute.auction.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ute.auction.entity.VaiTroEntity;
import com.ute.auction.entity.NguoiDungEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiDungRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final NguoiDungRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        NguoiDungEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));
        return new User(user.getEmail(), user.getMatKhau(), mapRolesToAuthorities(user.getVaiTros()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<VaiTroEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTenVaiTro())).collect(Collectors.toList());
    }

}
