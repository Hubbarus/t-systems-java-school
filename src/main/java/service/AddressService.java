package service;

import dao.AddressDao;
import entity.Addresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void save(Addresses address) {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.save(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public void update(Addresses address) {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.update(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public Addresses findById(Long id) {
        addressDao.openCurrentSession();
        Addresses address = addressDao.findById(id);
        addressDao.closeCurrentSession();
        return address;
    }

    public List<Addresses> findAll() {
        addressDao.openCurrentSession();
        List<Addresses> addresses = addressDao.findAll();
        addressDao.closeCurrentSession();
        return addresses;
    }

    public void delete(Addresses address) {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.delete(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.deleteAll();
        addressDao.closeCurrentSessionwithTransaction();
    }
}
