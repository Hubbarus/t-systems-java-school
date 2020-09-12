package converter;

import dto.ClientDTO;
import entity.Clients;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientConverter {

    @Autowired
    private ModelMapper mapper;

    public Clients convertToEntity(ClientDTO client) {
        Clients entity = mapper.map(client, Clients.class);
        return entity;
    }

    public ClientDTO convertToDTO(Clients client) {
        ClientDTO dto = mapper.map(client, ClientDTO.class);
        return dto;
    }
}
