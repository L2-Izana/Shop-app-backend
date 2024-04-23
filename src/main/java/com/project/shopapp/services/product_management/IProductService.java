package com.project.shopapp.services.product_management;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.project.shopapp.dtos.product_management.*;
import com.project.shopapp.models.product_management.*;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;

    Product getProductById(long id) throws Exception;

    Page<Product> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO) throws Exception;

    void deleteProduct(long id);

    boolean existsByName(String name);

    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;
}
