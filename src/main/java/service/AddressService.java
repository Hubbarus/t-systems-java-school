package service;

import dao.AddressDao;
import entity.Address;

import java.util.List;

public class AddressService {

    private final AddressDao addressDao;

    public AddressService() {
        this.addressDao = new AddressDao();
    }

    public void save(Address address) {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.save(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public void update(Address address) {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.update(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public Address findById(int id) {
        addressDao.openCurrentSession();
        Address address = addressDao.findById(id);
        addressDao.closeCurrentSession();
        return address;
    }

    public List<Address> findAll() {
        addressDao.openCurrentSession();
        List<Address> addresses = addressDao.findAll();
        addressDao.closeCurrentSession();
        return addresses;
    }

    public void delete(Address address) {
        addressDao.openCurrentSessionwithTransaction();
        addressDao.delete(address);
        addressDao.closeCurrentSessionwithTransaction();
    }
}
