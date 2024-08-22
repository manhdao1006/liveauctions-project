package com.ute.auction.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.RegistrationProductConverter;
import com.ute.auction.dto.RegistrationProductDTO;
import com.ute.auction.entity.AuctionFormatEntity;
import com.ute.auction.entity.RegistrationProductEntity;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.SubCategoryEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.AuctionFormatRepository;
import com.ute.auction.repository.RegistrationProductRepository;
import com.ute.auction.repository.SellerRepository;
import com.ute.auction.repository.SubCategoryRepository;
import com.ute.auction.service.IRegistrationProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationProductService implements IRegistrationProductService {

    private final RegistrationProductRepository registrationProductRepository;
    private final SellerRepository sellerRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final AuctionFormatRepository auctionFormatRepository;
    private final RegistrationProductConverter registrationProductConverter;

    private void checkExistedSeller(Long sellerId) {
        boolean sellerExists = sellerRepository.existsBySellerId(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }
    
    /*
     * get all registration products by seller id
     * @param sellerId, page, size
     * @return registration products
     */
    @Override
    public List<RegistrationProductDTO> getRegistrationProductsBySellerId(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RegistrationProductEntity> entities = registrationProductRepository.findRegistrationProductsBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    /*
     * register product
     * @param regisProduct
     * @return regisProduct
     */
    @Override
    @Transactional
    public RegistrationProductDTO registerProduct(RegistrationProductDTO regisProduct) {

        SellerEntity seller = sellerRepository.findOneBySellerId(regisProduct.getSeller().getSellerId());
        Optional<SubCategoryEntity> subCategory = subCategoryRepository.findById(regisProduct.getSubCategory().getId());
        Optional<AuctionFormatEntity> auctionFormat = auctionFormatRepository.findById(regisProduct.getAuctionFormat().getId());

        RegistrationProductEntity registrationProductEntity = registrationProductConverter.toEntity(regisProduct);
        registrationProductEntity.setSeller(seller);
        registrationProductEntity.setSubCategory(subCategory.get());
        registrationProductEntity.setAuctionFormat(auctionFormat.get());

        registrationProductEntity = registrationProductRepository.save(registrationProductEntity);

        return registrationProductConverter.toDTO(registrationProductEntity);
    }

    @Override
    public List<RegistrationProductDTO> sortedAscByStartingPrice(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RegistrationProductEntity> entities = registrationProductRepository.sortedAscByStartingPrice(sellerId, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RegistrationProductDTO> sortedDescByStartingPrice(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RegistrationProductEntity> entities = registrationProductRepository.sortedDescByStartingPrice(sellerId, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RegistrationProductDTO> sortedAscByRegistrationDate(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RegistrationProductEntity> entities = registrationProductRepository.sortedAscByRegistrationDate(sellerId, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RegistrationProductDTO> sortedDescByRegistrationDate(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RegistrationProductEntity> entities = registrationProductRepository.sortedDescByRegistrationDate(sellerId, pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

}
