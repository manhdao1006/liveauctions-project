package com.ute.auction.service;

import com.ute.auction.dto.NguoiMuaDTO;

public interface INguoiMuaService {

    NguoiMuaDTO getNguoiMuaByMaNguoiMua(long id);

    NguoiMuaDTO getNguoiMuaByEmail(String email);

}
