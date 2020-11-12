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
import project.exception.AppJsonParseException;
import project.exception.AppQueueException;
import project.exception.NoSuchClientException;
import project.producer.Producer;
import project.utils.CartListWrapper;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/**
 * Service class for {@link ClientDTO} objects.
 */
@Service
@Log
public class ClientService {

    @Autowired private ClientDao clientDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private OrderService orderService;
    @Autowired private ModelMapper mapper;
    @Autowired private ItemService itemService;
    @Autowired private Producer producer;

    /**
     * Finds {@link Client} entity in database and converts it to DTO
     * @param email of client
     * @return {@link ClientDTO} object
     */
    public ClientDTO findByEmail(String email) {
        List<Client> list = clientDao.findByEmail(email);
        return list.isEmpty() ? new ClientDTO() : mapper.map(list.get(0), ClientDTO.class);
    }

    /**
     * Generates user info and saves it to database
     * @param user to save
     */
    private void createUserAndSave(ClientDTO user) {
        user.setRole(RoleEnum.USER);
        user.setActive(true);
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));

        Client userToSave = mapper.map(user, Client.class);
        clientDao.save(userToSave);
    }

    /**
     * Updates client information in database
     * @param client to update
     */
    public void updateUserInformation(ClientDTO client) {
        Set<AddressDTO> addressList = findByEmail(client.getEmail()).getAddressList();
        client.setAddressList(addressList);
        client.setUserPass(passwordEncoder.encode(client.getUserPass()));
        clientDao.update(mapper.map(client, Client.class));
    }

    /**
     * Updates client information in database
     * @param principal client in session
     * @param addressDTO to update
     */
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

    /**
     * Get all user orders from database
     * @param user which orders to find
     * @return collection of user orders
     */
    public List<OrderDTO> getAllClientOrders(ClientDTO user) {
        return orderService.getAllClientOrders(mapper.map(user, Client.class));
    }

    /**
     * Collects order for user
     * @param items to collect
     * @param principal user that collects order
     * @return {@link OrderDTO} object with filled fields
     */
    public OrderDTO collectOrder(CartListWrapper items, Principal principal) {
        OrderDTO order = new OrderDTO();
        order.setItems(items.getList());
        order.setClient(findByEmail(principal.getName()));
        order.setSubtotal(items.getSubtotal());
        log.log(Level.INFO, String.format("Order collected for user %s", principal.getName()));
        return order;
    }

    /**
     * Checks if username already exists in database and creates new user if not
     * @param client object with e-mail filled
     * @return true if already exists
     */
    public boolean checkIfUserExistsAndCreate(ClientDTO client) {
        if (checkIfUsernameExist(client.getEmail())) {
            return true;
        } else {
            createUserAndSave(client);
            return false;
        }
    }

    /**
     * Checks if e-mail exists in database
     * @param email to check
     * @return true if exists
     */
    private boolean checkIfUsernameExist(String email) {
        ClientDTO client = findByEmail(email);
        if (client.getEmail() == null) {
            return false;
        }
        log.log(Level.WARNING, String.format("User with username %s already exists", client.getEmail()));
        return true;

    }

    /**
     * Proceeds order confirmation and sends information to stand app
     * @param principal user that placed order
     * @param order that placed
     * @throws AppJsonParseException in case of {@link Producer} errors
     * @throws AppQueueException in case of {@link org.apache.activemq.ActiveMQConnectionFactory} errors
     */
    public void doPayment(Principal principal, OrderDTO order) throws AppJsonParseException, AppQueueException {
        order.setClient(findByEmail(principal.getName()));
        orderService.createOrderAndSave(order);
        producer.sendMessage(new CartListWrapper(itemService.getTopTenItems()));
    }

    /**
     * Finds a list of top 10 clients for all time in database
     * @return List if entries with clients and their orders quantity
     * @throws NoSuchClientException in case no client id in database
     */
    public List<Map.Entry<ClientDTO, Integer>> getTopTenClients() throws NoSuchClientException {
        List<Object[]> topTenClients = clientDao.getTopTenClients();

        Map<ClientDTO, Integer> resultMap = new LinkedHashMap<>();

        for (Object[] obj : topTenClients) {
            ClientDTO client = findById((Long) obj[0]);
            Long quan = (Long) obj[1];
            resultMap.put(client, quan.intValue());
        }

        return new ArrayList<>(resultMap.entrySet());
    }

    /**
     * Finds {@link Client} entity in database and converts it to DTO
     * @param id of entity in database
     * @return {@link ClientDTO} object
     */
    private ClientDTO findById(Long id) throws NoSuchClientException {
        Client client = clientDao.findById(id);
        if (client == null) {
            log.log(Level.WARNING, String.format("No client with %s found", id));
            throw new NoSuchClientException(String.format("No client with %s found", id));
        }
        return mapper.map(client, ClientDTO.class);
    }
}
