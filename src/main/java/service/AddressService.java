package service;

import dao.AddressDao;
import entity.Addresses;

import java.util.List;

public class AddressService {

    private final AddressDao addressDao;

    public AddressService() {
        this.addressDao = new AddressDao();
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

    public Addresses findById(int id) {
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
}
