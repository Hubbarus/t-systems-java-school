package service;

import dao.ProductDao;
import entity.Product;

public class ProductService {
    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public void save(Product product) {
        productDao.openCurrentSessionwithTransaction();
        productDao.save(product);
        productDao.closeCurrentSessionwithTransaction();
    }

    public void update(Product product) {
        productDao.openCurrentSessionwithTransaction();
        productDao.update(product);
        productDao.closeCurrentSessionwithTransaction();
    }

    public Product findById(int id) {
        productDao.openCurrentSession();
        Product product = productDao.findById(id);
        productDao.closeCurrentSession();
        return product;
    }
}
