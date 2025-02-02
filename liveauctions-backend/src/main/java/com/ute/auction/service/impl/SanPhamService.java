package com.ute.auction.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.entity.AnhSanPhamEntity;
import com.ute.auction.entity.DanhMucConEntity;
import com.ute.auction.entity.LoaiDauGiaEntity;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.NhaKhoEntity;
import com.ute.auction.entity.NhaThamDinhEntity;
import com.ute.auction.entity.SanPhamEntity;
import com.ute.auction.exception.ResourceNotFormatException;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.AnhSanPhamRepository;
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
    private final AnhSanPhamRepository anhSanPhamRepository;
    private final SanPhamConverter sanPhamConverter;
    private final Cloudinary cloudinary;

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
    public SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO, List<MultipartFile> anhSanPhamList) throws IOException {
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
        sanPhamEntity = sanPhamRepository.save(sanPhamEntity);

        // Upload ảnh lên Cloudinary và lưu vào DB
        if (anhSanPhamList != null && !anhSanPhamList.isEmpty()) {
            for (MultipartFile file : anhSanPhamList) {
                Map<String, String> anhSanPhamInfo = uploadAnhSanPham(file);
                AnhSanPhamEntity anhSanPham = new AnhSanPhamEntity();
                anhSanPham.setTenAnhId(anhSanPhamInfo.get("publicId"));
                anhSanPham.setTenAnh(anhSanPhamInfo.get("url"));
                anhSanPham.setSanPham(sanPhamEntity);
                anhSanPhamRepository.save(anhSanPham);
            }
        }
        return sanPhamConverter.toDTO(sanPhamEntity);
    }

    @Transactional
    @Override
    public SanPhamDTO updateSanPham(String maSanPham, SanPhamDTO sanPhamDTO, List<MultipartFile> anhSanPhamList)
            throws IOException {
        SanPhamEntity sanPhamEntity = sanPhamRepository.findOneByMaSanPham(maSanPham)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                + maSanPham));

        if (sanPhamDTO.getMaNguoiBan() != null) {
            NguoiBanEntity nguoiBanEntity = nguoiBanRepository.findOneByMaNguoiBan(sanPhamDTO.getMaNguoiBan())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy người bán nào với mã người bán là: "
                                    + sanPhamDTO.getMaNguoiBan()));
            sanPhamEntity.setNguoiBan(nguoiBanEntity);
        }
        if (sanPhamDTO.getMaDanhMucCon() != null) {

            DanhMucConEntity danhMucConEntity = danhMucConRepository
                    .findOneByMaDanhMucCon(sanPhamDTO.getMaDanhMucCon())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy danh mục con nào với mã danh mục con là: "
                                    + sanPhamDTO.getMaDanhMucCon()));
            sanPhamEntity.setDanhMucCon(danhMucConEntity);
        }
        if (sanPhamDTO.getMaLoaiDauGia() != null) {
            LoaiDauGiaEntity loaiDauGiaEntity = loaiDauGiaRepository
                    .findOneByMaLoaiDauGia(sanPhamDTO.getMaLoaiDauGia())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy loại đấu giá nào với mã loại đấu giá là: "
                                    + sanPhamDTO.getMaLoaiDauGia()));
            sanPhamEntity.setLoaiDauGia(loaiDauGiaEntity);
        }
        if (sanPhamDTO.getMaNhaKho() != null) {
            NhaKhoEntity nhaKhoEntity = nhaKhoRepository.findOneByMaNhaKho(sanPhamDTO.getMaNhaKho())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy nhà kho nào với mã nhà kho là: "
                                    + sanPhamDTO.getMaNhaKho()));
            sanPhamEntity.setNhaKho(nhaKhoEntity);
        }
        if (sanPhamDTO.getMaNhaThamDinh() != null) {
            NhaThamDinhEntity nhaThamDinhEntity = nhaThamDinhRepository
                    .findOneByMaNhaThamDinh(sanPhamDTO.getMaNhaThamDinh())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy nhà thẩm định nào với mã nhà thẩm định là: "
                                    + sanPhamDTO.getMaNhaThamDinh()));
            sanPhamEntity.setNhaThamDinh(nhaThamDinhEntity);
        }

        if (anhSanPhamList != null && !anhSanPhamList.isEmpty()) {
            // Xóa tất cả ảnh cũ từ Cloudinary
            List<AnhSanPhamEntity> anhSanPhamListOld = anhSanPhamRepository.findOneBySanPham(sanPhamEntity);
            for (AnhSanPhamEntity anhSanPham : anhSanPhamListOld) {
                if (anhSanPham.getTenAnhId() != null) {
                    // Xóa ảnh cũ từ Cloudinary
                    cloudinary.uploader().destroy(anhSanPham.getTenAnhId(), ObjectUtils.emptyMap());
                }
            }

            // Thêm ảnh mới
            for (MultipartFile file : anhSanPhamList) {
                Map<String, String> anhSanPhamInfo = uploadAnhSanPham(file);
                AnhSanPhamEntity anhSanPham = new AnhSanPhamEntity();
                anhSanPham.setTenAnhId(anhSanPhamInfo.get("publicId"));
                anhSanPham.setTenAnh(anhSanPhamInfo.get("url"));
                anhSanPham.setSanPham(sanPhamEntity);
                anhSanPhamRepository.save(anhSanPham);
            }
        }

        SanPhamEntity sanPhamUpdated = sanPhamConverter.toEntity(sanPhamDTO, sanPhamEntity);
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

    @SuppressWarnings({ "null", "unchecked" })
    private Map<String, String> uploadAnhSanPham(MultipartFile anhSanPham) throws IOException {
        Map<String, String> anhSanPhamInfo = new HashMap<>();

        // check valid image
        if (anhSanPham == null || anhSanPham.isEmpty()) {
            anhSanPhamInfo.put("publicId", null);
            anhSanPhamInfo.put("url", null);
        } else {
            if (!anhSanPham.getContentType().startsWith("image/")) {
                throw new ResourceNotFormatException("Phải là file ảnh!");
            }
            // upload image
            Map<String, Object> result = cloudinary.uploader().upload(anhSanPham.getBytes(),
                    ObjectUtils.asMap("folder", "san-pham"));

            // get info from cloudinary
            String publicId = (String) result.get("public_id");
            String url = (String) result.get("url");

            anhSanPhamInfo.put("publicId", publicId);
            anhSanPhamInfo.put("url", url);
        }

        return anhSanPhamInfo;
    }

}
