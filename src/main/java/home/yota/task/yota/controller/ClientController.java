package home.yota.task.yota.controller;

import home.yota.task.yota.model.Client;
import home.yota.task.yota.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/users")
    public ResponseEntity<?> readAll() {
        final List<Client> clients = clientService.readAll();

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>("[]", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{regExp}")
    public ResponseEntity<?> read(@PathVariable String regExp) {
        final List<Client> clients = clientService.read(regExp);

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>("[]", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<?> putClientToList(@RequestBody Client client) {
        final String updateResult = clientService.putClientToList(client);

        return !updateResult.isEmpty() || updateResult.equals("")
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(updateResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> request) {
        final String updateResult = clientService.updatePassword(request);

        return !updateResult.isEmpty() || updateResult.equals("")
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>("{\"error\":\"Не удалось обновить пароль\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
