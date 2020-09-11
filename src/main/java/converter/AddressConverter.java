package converter;

import dto.AddressDTO;
import entity.Addresses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressConverter {

    @Autowired
    private ModelMapper mapper;

    public Addresses convertToEntity(AddressDTO address) {
        Addresses entity = mapper.map(address, Addresses.class);
        return entity;
    }

    public AddressDTO convertToDTO(Addresses address) {
        AddressDTO dto = mapper.map(address, AddressDTO.class);
        return dto;
    }
}
