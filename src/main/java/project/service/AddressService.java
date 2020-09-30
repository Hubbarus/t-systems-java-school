package project.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.AddressDao;
import project.dto.AddressDTO;
import project.entity.Address;

@Service
@AllArgsConstructor
public class AddressService {

    @Autowired private final AddressDao addressDao;
    @Autowired private final ModelMapper mapper;

    @Transactional
    public void update(AddressDTO addressDTO) {
        Address address = mapper.map(addressDTO, Address.class);
        addressDao.update(address);
    }

    public AddressDTO findById(Long id) {
        Address address = addressDao.findById(id);
        return mapper.map(address, AddressDTO.class);
    }

    public AddressDTO getFormFromAction(AddressDTO addressDTO, String action) {
        if (action == null) {
            return findById(addressDTO.getId());
        } else {
            return new AddressDTO();
        }
    }
}
