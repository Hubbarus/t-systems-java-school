package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dto.ClientDTO;
import project.entity.Client;

@Service
public class ClientConverter {

    @Autowired
    private ModelMapper mapper;

    public Client convertToEntity(ClientDTO client) {
        Client entity = mapper.map(client, Client.class);
        return entity;
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO dto = mapper.map(client, ClientDTO.class);
        return dto;
    }
}
