package service;

import dao.ProductDao;
import entity.Products;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
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
