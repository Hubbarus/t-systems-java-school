package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import project.dto.AddressDTO;
import project.service.AddressService;

@Service
public class AddressConverter implements Converter<String, AddressDTO> {

    @Autowired private AddressService addressService;

    @Override
        public AddressDTO convert(String source) {
            AddressDTO addressDTO = new AddressDTO();
            if (source != null) {
                String[] tokens = source.split(" \\| ");
                long id = Long.parseLong(tokens[0]);
                addressDTO = addressService.findById(id);
            }
            return addressDTO;
        }
    }
