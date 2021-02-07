package home.yota.task.yota;

import home.yota.task.yota.controller.ClientController;
import home.yota.task.yota.model.Client;
import home.yota.task.yota.service.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

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

        Object resultBody = clientController.readAll().getBody();
        if (resultBody instanceof List<?>) {
            List<?> clients = new ArrayList<>((Collection<?>) resultBody);
            for (Object resultClient : clients){
                if (resultClient instanceof Client) {
                    Assert.assertEquals("password1_new", ((Client) resultClient).getPassword());
                }
            }
        }
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

        ResponseEntity<?> responseEntity = clientController.read("mustbefind");
        HttpStatus httpStatus = responseEntity.getStatusCode();

        Assert.assertEquals(HttpStatus.OK, httpStatus);

        Object resultBody = responseEntity.getBody();
        if (resultBody instanceof List<?>) {
            List<?> clients = new ArrayList<>((Collection<?>) resultBody);
            for (Object client : clients){
                if (client instanceof Client) {
                    Assert.assertEquals("mustbefind", ((Client) client).getUsername());
                }
            }
        }
    }
}
