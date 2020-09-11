package service;

import dao.ClientDao;
import entity.Clients;

public class ClientService {

    private final ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }

    public void save(Clients client) {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.save(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    public void update(Clients client) {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.update(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    public Clients findById(int id) {
        clientDao.openCurrentSession();
        Clients client = clientDao.findById(id);
        clientDao.closeCurrentSession();
        return client;
    }
}
