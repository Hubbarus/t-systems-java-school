package service;

import dao.ClientDao;
import entity.Client;

public class ClientService {

    private final ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }

    public void save(Client client) {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.save(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    public void update(Client client) {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.update(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    public Client findById(int id) {
        clientDao.openCurrentSession();
        Client client = clientDao.findById(id);
        clientDao.closeCurrentSession();
        return client;
    }
}
