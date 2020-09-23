package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import project.dto.AddressDTO;
import project.entity.Address;
import project.service.AddressService;

@Service
public class AddressConverter implements Converter<String, AddressDTO> {
    @Autowired
    private AddressService addressService;

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

    @Override
    public AddressDTO convert(String source) {
        AddressDTO addressDTO = null;
        if (source != null) {
            String[] tokens = source.split(" \\| ");
            long id = Long.parseLong(tokens[0]);
            addressDTO = addressService.findById(id);
        }
        return addressDTO;
    }
}
