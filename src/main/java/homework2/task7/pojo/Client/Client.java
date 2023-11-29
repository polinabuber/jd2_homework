package homework2.task7.pojo.Client;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name="clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Embedded
    private ClientInfo clientInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        return Objects.equals(clientInfo, client.clientInfo);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (clientInfo != null ? clientInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientInfo=" + clientInfo +
                '}';
    }
}

