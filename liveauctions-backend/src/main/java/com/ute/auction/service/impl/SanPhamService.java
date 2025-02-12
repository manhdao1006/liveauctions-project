package com.ute.auction.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ute.auction.converter.DanhMucConConverter;
import com.ute.auction.converter.LoaiDauGiaConverter;
import com.ute.auction.converter.NguoiBanConverter;
import com.ute.auction.converter.NhaKhoConverter;
import com.ute.auction.converter.NhaThamDinhConverter;
import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.dto.DanhMucConDTO;
import com.ute.auction.dto.LoaiDauGiaDTO;
import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.NhaKhoDTO;
import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.dto.SanPhamResponseDTO;
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
    private final NguoiBanConverter nguoiBanConverter;
    private final DanhMucConConverter danhMucConConverter;
    private final LoaiDauGiaConverter loaiDauGiaConverter;
    private final NhaKhoConverter nhaKhoConverter;
    private final NhaThamDinhConverter nhaThamDinhConverter;
    private final Cloudinary cloudinary;

    @Override
    public PageResponse<SanPhamResponseDTO> getSanPhamsByMaNguoiBan(long maNguoiBan, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<SanPhamEntity> entities = sanPhamRepository.findSanPhamsByMaNguoiBan(maNguoiBan, "1", pageable);
        List<SanPhamResponseDTO> responseList = new ArrayList<>();
        for (SanPhamEntity sanPhamEntity : entities) {
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            NguoiBanEntity nguoiBanEntity = sanPhamEntity.getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            DanhMucConEntity danhMucConEntity = sanPhamEntity.getDanhMucCon();
            DanhMucConDTO danhMucConDTO = danhMucConConverter.toDTO(danhMucConEntity);

            LoaiDauGiaEntity loaiDauGiaEntity = sanPhamEntity.getLoaiDauGia();
            LoaiDauGiaDTO loaiDauGiaDTO = loaiDauGiaConverter.toDTO(loaiDauGiaEntity);

            NhaKhoEntity nhaKhoEntity = sanPhamEntity.getNhaKho();
            NhaKhoDTO nhaKhoDTO = nhaKhoConverter.toDTO(nhaKhoEntity);

            NhaThamDinhEntity nhaThamDinhEntity = sanPhamEntity.getNhaThamDinh();
            NhaThamDinhDTO nhaThamDinhDTO = nhaThamDinhConverter.toDTO(nhaThamDinhEntity);

            responseList.add(new SanPhamResponseDTO(sanPhamDTO, nguoiBanDTO, danhMucConDTO, loaiDauGiaDTO, nhaKhoDTO,
                    nhaThamDinhDTO));
        }

        return PageResponse.<SanPhamResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public List<SanPhamResponseDTO> getSanPhams() {
        List<SanPhamEntity> entities = sanPhamRepository.findSanPhamsByTrangThaiXoa("1");
        List<SanPhamResponseDTO> responseList = new ArrayList<>();
        for (SanPhamEntity sanPhamEntity : entities) {
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            NguoiBanEntity nguoiBanEntity = sanPhamEntity.getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            DanhMucConEntity danhMucConEntity = sanPhamEntity.getDanhMucCon();
            DanhMucConDTO danhMucConDTO = danhMucConConverter.toDTO(danhMucConEntity);

            LoaiDauGiaEntity loaiDauGiaEntity = sanPhamEntity.getLoaiDauGia();
            LoaiDauGiaDTO loaiDauGiaDTO = loaiDauGiaConverter.toDTO(loaiDauGiaEntity);

            NhaKhoEntity nhaKhoEntity = sanPhamEntity.getNhaKho();
            NhaKhoDTO nhaKhoDTO = nhaKhoConverter.toDTO(nhaKhoEntity);

            NhaThamDinhEntity nhaThamDinhEntity = sanPhamEntity.getNhaThamDinh();
            NhaThamDinhDTO nhaThamDinhDTO = nhaThamDinhConverter.toDTO(nhaThamDinhEntity);

            responseList.add(new SanPhamResponseDTO(sanPhamDTO, nguoiBanDTO, danhMucConDTO, loaiDauGiaDTO, nhaKhoDTO,
                    nhaThamDinhDTO));
        }
        return responseList;
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
