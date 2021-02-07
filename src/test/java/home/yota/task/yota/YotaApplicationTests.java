package home.yota.task.yota;

import home.yota.task.yota.controller.ClientController;
import home.yota.task.yota.model.Client;
import home.yota.task.yota.service.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class YotaApplicationTests {

    @Test
    public void testCreateClient() {
        Client client = new Client();
        client.setUsername("user1");
        client.setPassword("password1");

        Assert.assertEquals("user1", client.getUsername());
        Assert.assertEquals("password1", client.getPassword());
    }

    @Test
    public void testPutClientToList() {
        Client client = new Client();
        client.setUsername("user1");
        client.setPassword("password1");

        ClientController clientController = new ClientController(new ClientServiceImpl());
        clientController.putClientToList(client);

        Assert.assertNotEquals("[]", clientController.readAll());
    }

    @Test
    public void testUpdatePassword() {
        Client client = new Client();
        client.setUsername("user1");
        client.setPassword("password1");

        ClientController clientController = new ClientController(new ClientServiceImpl());
        clientController.putClientToList(client);

        Map<String, String> request = new HashMap<>();
        request.put("username", "user1");
        request.put("password", "password1_new");
        request.put("oldpassword", "password1");


        Assert.assertEquals(HttpStatus.OK, clientController.updatePassword(request).getStatusCode());
        ArrayList<Client> clients = (ArrayList<Client>) clientController.readAll().getBody();
        Assert.assertEquals("password1_new", clients.get(0).getPassword());
    }

    @Test
    public void testGetUserByRegExp() {
        Client client1 = new Client();
        client1.setUsername("user1");
        client1.setPassword("password1");

        Client client2 = new Client();
        client2.setUsername("mustbefind");
        client2.setPassword("password2");

        ClientController clientController = new ClientController(new ClientServiceImpl());
        clientController.putClientToList(client1);
        clientController.putClientToList(client2);

        ResponseEntity responseEntity = clientController.read("mustbefind");
        HttpStatus httpStatus = responseEntity.getStatusCode();
        ArrayList<Client> resultClients = (ArrayList<Client>) responseEntity.getBody();

        Assert.assertEquals(HttpStatus.OK, httpStatus);
        for (Client client : resultClients) {
            Assert.assertEquals("mustbefind", client.getUsername());
        }
    }
}
