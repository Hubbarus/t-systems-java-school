//package project.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import project.dto.CartDTO;
//import project.entity.Cart;
//
//@Service
//public class CartConverter {
//
//    @Autowired
//    private ModelMapper mapper;
//
//    public Cart convertToEntity(CartDTO cart) {
//        Cart entity = mapper.map(cart, Cart.class);
//        return entity;
//    }
//
//    public CartDTO convertToDTO(Cart cart) {
//        CartDTO dto = mapper.map(cart, CartDTO.class);
//        return dto;
//    }
//}
