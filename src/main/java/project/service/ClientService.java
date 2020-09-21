package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.ClientConverter;
import project.dao.ClientDao;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.entity.Client;
import project.entity.enums.RoleEnum;
import project.exception.NoSuchClientException;

import java.util.ArrayList;
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

    public ClientDTO findByUserName(String username) {
        List<ClientDTO> clients = findAll();
        for (ClientDTO client : clients) {
            if (client.getUsername().equalsIgnoreCase(username)) {
                return client;
            }
        }
        throw new NoSuchClientException("Client with username " + username + " not found!");
    }

    public void createUserAndSave(ClientDTO user, AddressDTO address) {
        List<AddressDTO> addresses = new ArrayList<>();
        addresses.add(address);
        user.setAddressList(addresses);
        user.setRole(RoleEnum.USER);

        save(user);
    }
}
