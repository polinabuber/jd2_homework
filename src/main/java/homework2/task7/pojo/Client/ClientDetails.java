package homework2.task7.pojo.Client;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name="client_details")
public class ClientDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Embedded
    private ClientDetailsInfo clientDetailsInfo;

    public ClientDetails() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientDetails(int id, ClientDetailsInfo clientDetailsInfo) {
        this.id = id;
        this.clientDetailsInfo = clientDetailsInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientDetailsInfo getClientDetailsInfo() {
        return clientDetailsInfo;
    }

    public void setClientDetailsInfo(ClientDetailsInfo clientDetailsInfo) {
        this.clientDetailsInfo = clientDetailsInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDetails that = (ClientDetails) o;

        if (id != that.id) return false;
        return Objects.equals(clientDetailsInfo, that.clientDetailsInfo);
    }

    @Override
    public int hashCode() {

        int result = id;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (clientDetailsInfo != null ? clientDetailsInfo.hashCode() : 0);
        return result;
    }
}

