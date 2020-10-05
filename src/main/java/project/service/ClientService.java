package project.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.dao.ClientDao;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.OrderDTO;
import project.entity.Client;
import project.entity.enums.RoleEnum;
import project.utils.CartListWrapper;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired private ClientDao clientDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private OrderService orderService;
    @Autowired private ModelMapper mapper;

    public void save(ClientDTO clientDTO) {
        Client client = mapper.map(clientDTO, Client.class);
        clientDao.save(client);
    }

    public void update(ClientDTO clientDTO) {
        Client client = mapper.map(clientDTO, Client.class);
        clientDao.update(client);
    }

    private List<ClientDTO> findAll() {
        List<Client> clients = clientDao.findAll();
        return clients
                .stream()
                .map(cl -> mapper.map(cl, ClientDTO.class))
                .collect(Collectors.toList());
    }

    public ClientDTO findByEmail(String email) {
        List<ClientDTO> clients = findAll();
        for (ClientDTO client : clients) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return new ClientDTO();
    }

    private void createUserAndSave(ClientDTO user) {
        user.setRole(RoleEnum.USER);
        user.setActive(true);
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));

        save(user);
    }

    public void updateUserInformation(ClientDTO currentClient, ClientDTO client) {
        if (currentClient == null || client == null) {
            throw new IllegalArgumentException();
        }
        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setBirthDate(client.getBirthDate());
        currentClient.setUserPass(passwordEncoder.encode(client.getUserPass()));

        update(currentClient);
    }

    public void updateAddressInformation(Principal principal, AddressDTO addressDTO) {
        ClientDTO user = findByEmail(principal.getName());
        Set<AddressDTO> addresses = user.getAddressList();
        boolean wasAdded = false;
        for (AddressDTO address : addresses) {
            if (address.getId() == addressDTO.getId()) {
                addresses.remove(address);
                addresses.add(addressDTO);
                wasAdded = true;
                break;
            }
        }

        if (!wasAdded) {
            addresses.add(addressDTO);
        }

        user.setAddressList(addresses);
        update(user);
    }

    public List<OrderDTO> getAllClientOrders(ClientDTO user) {
        List<OrderDTO> currentClientOrders = new ArrayList<>();
        List<OrderDTO> orders = orderService.findAll();
        for (OrderDTO order : orders) {
            long clientId = order.getClient().getId();
            if (clientId == user.getId()) {
                currentClientOrders.add(order);
            }
        }
        return currentClientOrders;
    }

    public OrderDTO collectOrder(CartListWrapper items, Principal principal) {
        OrderDTO order = new OrderDTO();
        order.setItems(items.getList());
        order.setClient(findByEmail(principal.getName()));
        order.setSubtotal(items.getSubtotal());
        return order;
    }

    public boolean checkIfUserExistsAndCreate(ClientDTO client) {
        if (checkIfUsernameExist(client.getEmail())) {
            return true;
        } else {
            createUserAndSave(client);
            return false;
        }
    }

    private boolean checkIfUsernameExist(String email) {
        ClientDTO client = findByEmail(email);
        if (client.getEmail() == null) {
            return false;
        }
        return true;

    }
}
