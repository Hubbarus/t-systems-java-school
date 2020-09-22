package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.ClientDTO;
import project.entity.Client;

@Service
@Transactional
public class ClientConverter {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AddressConverter addressConverter;

    public Client convertToEntity(ClientDTO client) {
        Client entity = mapper.map(client, Client.class);
        return entity;
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO dto = mapper.map(client, ClientDTO.class);
        return dto;
    }
}
