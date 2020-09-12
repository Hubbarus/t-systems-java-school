package service;

import dao.ProductDao;
import entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productService")
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(Products product) {
        productDao.openCurrentSessionwithTransaction();
        productDao.save(product);
        productDao.closeCurrentSessionwithTransaction();
    }

    public void update(Products product) {
        productDao.openCurrentSessionwithTransaction();
        productDao.update(product);
        productDao.closeCurrentSessionwithTransaction();
    }

    public Products findById(Long id) {
        productDao.openCurrentSession();
        Products product = productDao.findById(id);
        productDao.closeCurrentSession();
        return product;
    }

    public void delete(Products product) {
        productDao.closeCurrentSessionwithTransaction();
        productDao.delete(product);
        productDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        productDao.openCurrentSessionwithTransaction();
        productDao.deleteAll();
        productDao.closeCurrentSessionwithTransaction();
    }

    public List<Products> findAll() {
        productDao.openCurrentSession();
        List<Products> products = productDao.findAll();
        productDao.closeCurrentSession();
        return products;
    }
}
