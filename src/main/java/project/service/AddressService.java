package project.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.AddressDao;
import project.dto.AddressDTO;
import project.entity.Address;
import project.exception.NoSuchAddressException;
import project.exception.NotSupportedActionException;

@Service
@AllArgsConstructor
public class AddressService {

    @Autowired private final AddressDao addressDao;
    @Autowired private final ModelMapper mapper;

    public AddressDTO findById(Long id) {
        Address address = addressDao.findById(id);
        if (address == null) {
            throw new NoSuchAddressException("No address with " + id + " id");
        }
        return mapper.map(address, AddressDTO.class);
    }

    public AddressDTO getFormFromAction(AddressDTO addressDTO, String action) {
        if (action.equals("edit")) {
            return findById(addressDTO.getId());
        } else if (action.equals("add")) {
            return new AddressDTO();
        } else {
            throw new NotSupportedActionException(action + "is not supported.");
        }
    }
}
