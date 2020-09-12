package service;

import dao.ClientDao;
import entity.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
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

    public Clients findById(Long id) {
        clientDao.openCurrentSession();
        Clients client = clientDao.findById(id);
        clientDao.closeCurrentSession();
        return client;
    }

    public List<Clients> findAll() {
        clientDao.openCurrentSession();
        List<Clients> clients = clientDao.findAll();
        clientDao.closeCurrentSession();
        return clients;
    }

    public void delete(Clients client) {
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
