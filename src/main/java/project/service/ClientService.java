package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.ClientConverter;
import project.dao.ClientDao;
import project.dto.ClientDTO;
import project.entity.Client;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private final ClientDao clientDao;
    @Autowired
    private final ClientConverter clientConverter;

    @Transactional
    public void save(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        clientDao.save(client);
    }

    @Transactional
    public void update(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        clientDao.update(client);
    }

    public ClientDTO findById(Long id) {
        Client client = clientDao.findById(id);
        return clientConverter.convertToDTO(client);
    }

    public List<ClientDTO> findAll() {
        List<Client> client = clientDao.findAll();
        return client
                .stream()
                .map(clientConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        clientDao.delete(client);
    }

    @Transactional
    public void deleteAll() {
        clientDao.deleteAll();
    }
}
