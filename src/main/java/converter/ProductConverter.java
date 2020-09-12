package converter;

import dto.ProductDTO;
import entity.Products;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConverter {

    @Autowired
    private ModelMapper mapper;

    public Products convertToEntity(ProductDTO product) {
        Products entity = mapper.map(product, Products.class);
        return entity;
    }

    public ProductDTO convertToDTO(Products product) {
        ProductDTO dto = mapper.map(product, ProductDTO.class);
        return dto;
    }
}
