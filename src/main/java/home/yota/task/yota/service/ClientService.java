package home.yota.task.yota.service;

import home.yota.task.yota.model.Client;
import java.util.List;
import java.util.Map;

public interface ClientService {

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<Client> readAll();

    List<Client> read(String regExp);

    /**
     * Складывает клиента в список для хранения.
     * @param client - клиент для определения в список
     * @return
     */
    boolean putClientToList(Client client);

    boolean updatePassword(Map<String, String> request);

}
