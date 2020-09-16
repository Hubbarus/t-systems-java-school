package project.service;

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
public class ClientService {

    private final ClientDao clientDao;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientService(ClientDao clientDao, ClientConverter clientConverter) {
        this.clientDao = clientDao;
        this.clientConverter = clientConverter;
    }

    public void save(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        clientDao.openCurrentSessionwithTransaction();
        clientDao.save(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    public void update(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        clientDao.openCurrentSessionwithTransaction();
        clientDao.update(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    @Transactional
    public ClientDTO findById(Long id) {
        clientDao.openCurrentSession();
        Client client = clientDao.findById(id);
        clientDao.closeCurrentSession();
        return clientConverter.convertToDTO(client);
    }

    @Transactional
    public List<ClientDTO> findAll() {
        clientDao.openCurrentSession();
        List<Client> client = clientDao.findAll();
        clientDao.closeCurrentSession();
        List<ClientDTO> clientDTOS = client
                .stream()
                .map(cl -> clientConverter.convertToDTO(cl))
                .collect(Collectors.toList());
        return clientDTOS;
    }

    public void delete(ClientDTO clientDTO) {
        Client client = clientConverter.convertToEntity(clientDTO);
        clientDao.openCurrentSessionwithTransaction();
        clientDao.delete(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.deleteAll();
        clientDao.closeCurrentSessionwithTransaction();
    }
}
