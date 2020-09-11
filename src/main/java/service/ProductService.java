package service;

import dao.ProductDao;
import entity.Products;

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

    public Products findById(int id) {
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
}
