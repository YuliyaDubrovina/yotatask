package home.yota.task.yota.service;

import home.yota.task.yota.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements ClientService{

    // Хранилище клиентов
    private static final List<Client> CLIENTS_LIST = new ArrayList<>();


    @Override
    public List<Client> readAll() {
        return CLIENTS_LIST;
    }

    @Override
    public List<Client> read(String regExp) {
        List<Client> resultClientList = new ArrayList<>();
        for (Client client : CLIENTS_LIST) {
            String username = client.getUsername();
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(username);
            while (matcher.find()){
                resultClientList.add(client);
            }
        }
        return resultClientList;
    }

    @Override
    public String putClientToList(Client client) {
        if (client.getPassword().equals("") || client.getPassword() == null) {
            return "\"error\":\"Пароль не задан или пустой\"";
        }

        for (Client existClient : CLIENTS_LIST) {
            if (existClient.getUsername().equals(client.getUsername())) return "\"error\":\"Такой клиент уже существует.\"";
        }

        CLIENTS_LIST.add(client);
        return "";
    }

    @Override
    public String updatePassword(Map<String, String> request) {
        String username = request.get("username");
        String oldpassword = request.get("oldpassword");
        String password = request.get("password");

        for (Client client : CLIENTS_LIST) {
            if (client.getUsername().equals(username)) {
                if (client.getPassword().equals(oldpassword)) {
                    client.setPassword(password);
                    return "";
                }
                else return "\"error\":\"Заданные oldpasword не совпадает с паролем в системе.\"";
            }
        }

        return "\"error\":\"Такой клиента не существует.\"";
    }

}
