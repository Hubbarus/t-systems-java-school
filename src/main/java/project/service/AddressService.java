package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void save(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
        addressDao.save(address);
    }

    @Transactional
    public void update(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
        addressDao.update(address);
    }

    public AddressDTO findById(Long id) {
        Address address = addressDao.findById(id);
        return addressConverter.convertToDTO(address);
    }

    public List<AddressDTO> findAll() {
        List<Address> address = addressDao.findAll();
        List<AddressDTO> addressDTOS = address
                .stream()
                .map(ad -> addressConverter.convertToDTO(ad))
                .collect(Collectors.toList());
        return addressDTOS;
    }

    @Transactional
    public void delete(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
        addressDao.delete(address);
    }

    @Transactional
    public void deleteAll() {
        addressDao.deleteAll();
    }
}
