package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.converter.AddressConverter;
import project.dao.AddressDao;
import project.dto.AddressDTO;
import project.entity.Address;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressDao addressDao;
    private final AddressConverter addressConverter;

    @Autowired
    public AddressService(AddressDao addressDao, AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
        this.addressDao = addressDao;
    }

    public void save(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
        addressDao.openCurrentSessionwithTransaction();
        addressDao.save(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public void update(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
        addressDao.openCurrentSessionwithTransaction();
        addressDao.update(address);
        addressDao.closeCurrentSessionwithTransaction();
    }

    public AddressDTO findById(Long id) {
        addressDao.openCurrentSession();
        Address address = addressDao.findById(id);
        addressDao.closeCurrentSession();
        return addressConverter.convertToDTO(address);
    }

    public List<AddressDTO> findAll() {
        addressDao.openCurrentSession();
        List<Address> address = addressDao.findAll();
        addressDao.closeCurrentSession();
        List<AddressDTO> addressDTOS = address
                .stream()
                .map(ad -> addressConverter.convertToDTO(ad))
                .collect(Collectors.toList());
        return addressDTOS;
    }

    public void delete(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
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
