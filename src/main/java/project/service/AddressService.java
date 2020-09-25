package project.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AddressService {

    @Autowired
    private final AddressDao addressDao;
    @Autowired
    private final AddressConverter addressConverter;

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
        return address
                .stream()
                .map(addressConverter::convertToDTO)
                .collect(Collectors.toList());
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

    public AddressDTO getFormFromAction(AddressDTO addressDTO, String action) {
        if (action == null) {
            return findById(addressDTO.getId());
        } else return new AddressDTO();
    }
}
