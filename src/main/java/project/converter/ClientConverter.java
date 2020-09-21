package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.entity.Address;
import project.entity.Client;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientConverter {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AddressConverter addressConverter;

    public Client convertToEntity(ClientDTO client) {
        Client entity = mapper.map(client, Client.class);
        List<AddressDTO> addressDTOS = client.getAddressList();
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : addressDTOS) {
            addresses.add(addressConverter.convertToEntity(addressDTO));
        }
        entity.setAddressList(addresses);
        return entity;
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO dto = mapper.map(client, ClientDTO.class);
        List<Address> addresses = client.getAddressList();
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for (Address address : addresses) {
            addressDTOS.add(addressConverter.convertToDTO(address));
        }
        dto.setAddressList(addressDTOS);
        return dto;
    }
}
