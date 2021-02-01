package home.yota.task.yota.service;

import home.yota.task.yota.model.Client;
import java.util.List;

public interface ClientService {
    /**
     * Создает нового клиента
     * @param client - клиент для создания
     */
    void create(Client client);

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<Client> readAll();

    Client read(int id);

    boolean update(Client client);

    boolean delete(int id);

}
