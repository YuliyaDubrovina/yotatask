package home.yota.task.yota.service;

import home.yota.task.yota.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    // Хранилище клиентов
    private static final List<Client> CLIENTS_LIST = new ArrayList<>();


    @Override
    public void create(Client client) {
        CLIENTS_LIST.add(client);
    }

    @Override
    public List<Client> readAll() {
//        return new ArrayList<>(CLIENT_REPOSITORY.values());
        return CLIENTS_LIST;
    }

    @Override
    public Client read(int id) {
//        return CLIENT_REPOSITORY.get(id);
        return new Client();
    }

    @Override
    public boolean update(Client client) {
        if (client.getPassword().equals("") || client.getPassword() == null) {
            return false;
        }
        CLIENTS_LIST.add(client);
        return true;
    }

    @Override
    public boolean delete(int id) {
//        return CLIENT_REPOSITORY.remove(id) != null;
        return true;
    }
}
