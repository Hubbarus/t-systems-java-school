package project.service;

import lombok.extern.java.Log;
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
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
@Log
public class ClientService {

    @Autowired private ClientDao clientDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private OrderService orderService;
    @Autowired private ModelMapper mapper;

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

        Client userToSave = mapper.map(user, Client.class);
        clientDao.save(userToSave);
    }

    public void updateUserInformation(ClientDTO client) {
        Set<AddressDTO> addressList = findByEmail(client.getEmail()).getAddressList();
        client.setAddressList(addressList);
        client.setUserPass(passwordEncoder.encode(client.getUserPass()));
        clientDao.update(mapper.map(client, Client.class));
    }

    public void updateAddressInformation(Principal principal, AddressDTO addressDTO) {
        ClientDTO user = findByEmail(principal.getName());
        Set<AddressDTO> addresses = user.getAddressList();

        if (addresses.contains(addressDTO)) {
            for (AddressDTO address : addresses) {
                if (address.getId() == addressDTO.getId()) {
                    addresses.remove(address);
                    addresses.add(addressDTO);
                    log.log(Level.INFO, String.format("Address %s information updated", addressDTO.getId()));
                    break;
                }
            }
        } else {
            addresses.add(addressDTO);
            log.log(Level.INFO, String.format("New Address added: %s", addressDTO));
        }

        user.setAddressList(addresses);
        Client userToUpdate = mapper.map(user, Client.class);
        clientDao.update(userToUpdate);
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
        log.log(Level.INFO, String.format("Order collected for user %s", principal.getName()));
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
        log.log(Level.WARNING, String.format("User with username %s already exists", client.getEmail()));
        return true;

    }
}
