package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.ClientConverter;
import project.dao.ClientDao;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.entity.Client;
import project.entity.enums.RoleEnum;
import project.exception.NoSuchClientException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired private final ClientDao clientDao;
    @Autowired private final ClientConverter clientConverter;
    @Autowired private final PasswordEncoder passwordEncoder;

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

    public ClientDTO findByEmail(String email) {
        List<ClientDTO> clients = findAll();
        for (ClientDTO client : clients) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        throw new NoSuchClientException("Client with email " + email + " not found!");
    }

    public void createUserAndSave(ClientDTO user, AddressDTO address) {
        Set<AddressDTO> addresses = new HashSet<>();
        addresses.add(address);
        user.setAddressList(addresses);
        user.setRole(RoleEnum.USER);
        user.setActive(true);
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));

        save(user);
    }

    public void encodePassword(ClientDTO client) {
        client.setUserPass(passwordEncoder.encode(client.getUserPass()));
    }

    public void updateUserInformation(ClientDTO currentClient, ClientDTO client) {
        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setBirthDate(client.getBirthDate());
        currentClient.setEmail(client.getEmail());
        currentClient.setUserPass(passwordEncoder.encode(client.getUserPass()));

        update(currentClient);
    }
}
