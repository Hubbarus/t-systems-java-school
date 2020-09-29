package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.AddressConverter;
import project.dao.AddressDao;
import project.dto.AddressDTO;
import project.entity.Address;

@Service
@AllArgsConstructor
public class AddressService {

    @Autowired
    private final AddressDao addressDao;
    @Autowired
    private final AddressConverter addressConverter;

    @Transactional
    public void update(AddressDTO addressDTO) {
        Address address = addressConverter.convertToEntity(addressDTO);
        addressDao.update(address);
    }

    public AddressDTO findById(Long id) {
        Address address = addressDao.findById(id);
        return addressConverter.convertToDTO(address);
    }

    public AddressDTO getFormFromAction(AddressDTO addressDTO, String action) {
        if (action == null) {
            return findById(addressDTO.getId());
        } else return new AddressDTO();
    }
}
