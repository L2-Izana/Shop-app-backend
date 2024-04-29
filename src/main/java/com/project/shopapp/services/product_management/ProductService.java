package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.*;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.InvalidParamException;
import com.project.shopapp.models.product_management.Product;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.repositories.product_management.CategoryRepository;
import com.project.shopapp.repositories.product_management.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find category with id: " + productDTO.getCategoryId()));

        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long productId) throws Exception {
        return productRepository.findById(productId).
                orElseThrow(() -> new DataNotFoundException(
                        "Cannot find product with id =" + productId));
    }

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product updateProduct(
            long id,
            ProductDTO productDTO
    )
            throws Exception {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            Category existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() ->
                            new DataNotFoundException(
                                    "Cannot find category with id: " + productDTO.getCategoryId()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategory(existingCategory);
            existingProduct.setDescription(productDTO.getDescription());
            return productRepository.save(existingProduct);
        }
        return null;

    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

//    @Override
//    public ProductImage createProductImage(
//            Long productId,
//            ProductImageDTO productImageDTO) throws Exception {
//        Product existingProduct = productRepository
//                .findById(productImageDTO.getProductId())
//                .orElseThrow(() ->
//                        new DataNotFoundException(
//                                "Cannot find product with id: " + productImageDTO.getProductId()));
//        ProductImage newProductImage = ProductImage.builder()
//                .product(existingProduct)
//                .imageUrl(productImageDTO.getImageUrl())
//                .build();
//        int size = productImageRepository.findByProductId(productId).size();
//        if (size >= 5) {
//            throw new InvalidParamException("Number of images must be <= 5");
//        }
//        return productImageRepository.save(newProductImage);
//    }
}
