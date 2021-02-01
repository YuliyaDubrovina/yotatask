package home.yota.task.yota.service;

import home.yota.task.yota.model.Client;
import java.util.List;
import java.util.Map;

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

    List<Client> read(String regExp);

    boolean update(Client client);

    boolean updatePassword(Map<String, String> request);

}
