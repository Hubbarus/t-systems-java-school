package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.converter.CartConverter;
import project.dao.CartDao;
import project.dto.CartDTO;
import project.entity.Cart;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartDao cartDao;
    private final CartConverter cartConverter;

    @Autowired
    public CartService(CartDao cartDao, CartConverter cartConverter) {
        this.cartDao = cartDao;
        this.cartConverter = cartConverter;
    }

    public void save(CartDTO cartDTO) {
        Cart cart = cartConverter.convertToEntity(cartDTO);
        cartDao.openCurrentSessionwithTransaction();
        cartDao.save(cart);
        cartDao.closeCurrentSessionwithTransaction();
    }

    public void update(CartDTO cartDTO) {
        Cart cart = cartConverter.convertToEntity(cartDTO);
        cartDao.openCurrentSessionwithTransaction();
        cartDao.update(cart);
        cartDao.closeCurrentSessionwithTransaction();
    }

    @Transactional
    public CartDTO findById(Long id) {
        cartDao.openCurrentSession();
        Cart cart = cartDao.findById(id);
        cartDao.closeCurrentSession();
        return cartConverter.convertToDTO(cart);
    }

    public void delete(CartDTO cartDTO) {
        Cart cart = cartConverter.convertToEntity(cartDTO);
        cartDao.closeCurrentSessionwithTransaction();
        cartDao.delete(cart);
        cartDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        cartDao.openCurrentSessionwithTransaction();
        cartDao.deleteAll();
        cartDao.closeCurrentSessionwithTransaction();
    }

    @Transactional
    public List<CartDTO> findAll() {
        cartDao.openCurrentSession();
        List<Cart> cart = cartDao.findAll();
        cartDao.closeCurrentSession();
        return cart
                .stream()
                .map(cartConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
