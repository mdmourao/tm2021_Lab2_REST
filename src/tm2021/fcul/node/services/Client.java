package tm2021.fcul.node.services;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import tm2021.fcul.api.Node;
import tm2021.fcul.api.service.RestNode;

public class Client implements Runnable {

    String idClient;
    int amount;

    public Client(String clientId, int amount) {
        this.idClient = clientId;
        this.amount = amount;
    }

    @Override
    public void run() {
        ClientConfig config = new ClientConfig();
        jakarta.ws.rs.client.Client client = ClientBuilder.newClient(config);
        String url = "http://" + idClient + ":8081" + "/rest";
        WebTarget target = client.target(url).path(RestNode.PATH);

        Node n = new Node(idClient, 0);

        Response r = target.path(idClient).queryParam("amount", amount).request()
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(n, MediaType.APPLICATION_JSON));
        if (r.getStatus() == Response.Status.OK.getStatusCode() && r.hasEntity())
            System.out.println("Success, updated amount with id: " + r.readEntity(String.class));
        else
            System.out.println("Error, HTTP error status: " + r.getStatus());
    }
}
