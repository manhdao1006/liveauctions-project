package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.entity.DanhMucConEntity;
import com.ute.auction.entity.LoaiDauGiaEntity;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.NhaKhoEntity;
import com.ute.auction.entity.NhaThamDinhEntity;
import com.ute.auction.entity.SanPhamEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.DanhMucConRepository;
import com.ute.auction.repository.LoaiDauGiaRepository;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.repository.NhaKhoRepository;
import com.ute.auction.repository.NhaThamDinhRepository;
import com.ute.auction.repository.SanPhamRepository;
import com.ute.auction.service.ISanPhamService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SanPhamService implements ISanPhamService {

    private final SanPhamRepository sanPhamRepository;
    private final NguoiBanRepository nguoiBanRepository;
    private final DanhMucConRepository danhMucConRepository;
    private final LoaiDauGiaRepository loaiDauGiaRepository;
    private final NhaKhoRepository nhaKhoRepository;
    private final NhaThamDinhRepository nhaThamDinhRepository;
    private final SanPhamConverter sanPhamConverter;

    /*
     * get all products by seller id
     * 
     * @param sellerId, page, size
     * 
     * @return products
     */
    @Override
    public List<SanPhamDTO> getProductsBySellerId(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamEntity> entities = sanPhamRepository.findProductsBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(sanPhamConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDTO> sortedAscByStartingPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamEntity> entities = sanPhamRepository.sortedAscByStartingPrice(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(sanPhamConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDTO> sortedDescByStartingPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamEntity> entities = sanPhamRepository.sortedAscByStartingPrice(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(sanPhamConverter::toDTO).collect(Collectors.toList());
    }

    private void checkExistedSeller(long sellerId) {
        boolean sellerExists = nguoiBanRepository.existsByMaNguoiBan(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

    @Override
    public List<SanPhamDTO> getSanPhams() {
        List<SanPhamEntity> entities = sanPhamRepository.findSanPhamsByTrangThaiXoa("1");
        return entities.stream().map(sanPhamConverter::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO) {
        NguoiBanEntity nguoiBanEntity = nguoiBanRepository.findOneByMaNguoiBan(sanPhamDTO.getMaNguoiBan())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người bán nào với mã người bán là: "
                                + sanPhamDTO.getMaNguoiBan()));
        DanhMucConEntity danhMucConEntity = danhMucConRepository.findOneByMaDanhMucCon(sanPhamDTO.getMaDanhMucCon())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục con nào với mã danh mục con là: "
                                + sanPhamDTO.getMaDanhMucCon()));
        LoaiDauGiaEntity loaiDauGiaEntity = loaiDauGiaRepository.findOneByMaLoaiDauGia(sanPhamDTO.getMaLoaiDauGia())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy loại đấu giá nào với mã loại đấu giá là: "
                                + sanPhamDTO.getMaLoaiDauGia()));
        NhaKhoEntity nhaKhoEntity = nhaKhoRepository.findOneByMaNhaKho(sanPhamDTO.getMaNhaKho())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà kho nào với mã nhà kho là: "
                                + sanPhamDTO.getMaNhaKho()));
        NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository
                .findOneByMaNhaThamDinh(sanPhamDTO.getMaNhaThamDinh()).orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhà thẩm định nào với mã nhà thẩm định là: "
                                + sanPhamDTO.getMaNhaThamDinh()));
        SanPhamEntity sanPhamEntity = sanPhamConverter.toEntity(sanPhamDTO);
        sanPhamEntity.setNguoiBan(nguoiBanEntity);
        sanPhamEntity.setDanhMucCon(danhMucConEntity);
        sanPhamEntity.setLoaiDauGia(loaiDauGiaEntity);
        sanPhamEntity.setNhaKho(nhaKhoEntity);
        sanPhamEntity.setNhaThamDinh(nhaThamDinhEntity);
        return sanPhamConverter.toDTO(sanPhamRepository.save(sanPhamEntity));
    }

    @Transactional
    @Override
    public SanPhamDTO updateSanPham(String maSanPham, SanPhamDTO updatedSanPham) {
        SanPhamEntity sanPhamEntity = sanPhamRepository.findOneByMaSanPham(maSanPham)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                + maSanPham));

        if (updatedSanPham.getMaNguoiBan() != null) {
            NguoiBanEntity nguoiBanEntity = nguoiBanRepository.findOneByMaNguoiBan(updatedSanPham.getMaNguoiBan())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy người bán nào với mã người bán là: "
                                    + updatedSanPham.getMaNguoiBan()));
            sanPhamEntity.setNguoiBan(nguoiBanEntity);
        }
        if (updatedSanPham.getMaDanhMucCon() != null) {

            DanhMucConEntity danhMucConEntity = danhMucConRepository
                    .findOneByMaDanhMucCon(updatedSanPham.getMaDanhMucCon())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy danh mục con nào với mã danh mục con là: "
                                    + updatedSanPham.getMaDanhMucCon()));
            sanPhamEntity.setDanhMucCon(danhMucConEntity);
        }
        if (updatedSanPham.getMaLoaiDauGia() != null) {
            LoaiDauGiaEntity loaiDauGiaEntity = loaiDauGiaRepository
                    .findOneByMaLoaiDauGia(updatedSanPham.getMaLoaiDauGia())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy loại đấu giá nào với mã loại đấu giá là: "
                                    + updatedSanPham.getMaLoaiDauGia()));
            sanPhamEntity.setLoaiDauGia(loaiDauGiaEntity);
        }
        if (updatedSanPham.getMaNhaKho() != null) {
            NhaKhoEntity nhaKhoEntity = nhaKhoRepository.findOneByMaNhaKho(updatedSanPham.getMaNhaKho())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy nhà kho nào với mã nhà kho là: "
                                    + updatedSanPham.getMaNhaKho()));
            sanPhamEntity.setNhaKho(nhaKhoEntity);
        }
        if (updatedSanPham.getMaNhaThamDinh() != null) {
            NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository
                    .findOneByMaNhaThamDinh(updatedSanPham.getMaNhaThamDinh())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy nhà thẩm định nào với mã nhà thẩm định là: "
                                    + updatedSanPham.getMaNhaThamDinh()));
            sanPhamEntity.setNhaThamDinh(nhaThamDinhEntity);
        }

        SanPhamEntity sanPhamUpdated = sanPhamConverter.toEntity(updatedSanPham, sanPhamEntity);
        return sanPhamConverter.toDTO(sanPhamRepository.save(sanPhamUpdated));
    }

    @Transactional
    @Override
    public void deleteSanPham(String maSanPham) {
        SanPhamEntity sanPhamEntity = sanPhamRepository.findOneByMaSanPham(maSanPham)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                + maSanPham));
        sanPhamEntity.setTrangThaiXoa("0");
        sanPhamRepository.save(sanPhamEntity);
    }

}
