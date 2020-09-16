package project.converter;

import project.dto.AddressDTO;
import project.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressConverter {

    @Autowired
    private ModelMapper mapper;

    public Address convertToEntity(AddressDTO address) {
        Address entity = mapper.map(address, Address.class);
        return entity;
    }

    public AddressDTO convertToDTO(Address address) {
        AddressDTO dto = mapper.map(address, AddressDTO.class);
        return dto;
    }
}
