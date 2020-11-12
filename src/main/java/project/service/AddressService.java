package project.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.AddressDao;
import project.dto.AddressDTO;
import project.entity.Address;
import project.exception.NoSuchAddressException;

import java.util.logging.Level;

/**
 * Service class for {@link AddressDTO} objects.
 */
@Service
@AllArgsConstructor
@Log
public class AddressService {

    @Autowired private final AddressDao addressDao;
    @Autowired private final ModelMapper mapper;


    /**
     * Finds {@link Address} entity in database and converts it to DTO
     * @param id of entity in database
     * @return {@link AddressDTO} object
     */
    public AddressDTO findById(Long id) {
        Address address = addressDao.findById(id);
        if (address == null) {
            log.log(Level.INFO, String.format("No address with %s id", id));
            throw new NoSuchAddressException("No address with " + id + " id");
        }
        return mapper.map(address, AddressDTO.class);
    }
}
