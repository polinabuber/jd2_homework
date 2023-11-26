package homework2.task7.pojo;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name="client_details")
public class ClientDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Embedded
    private ClientDetailsInfo clientDetailsInfo;

    public ClientDetails() {
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
        result = 31 * result + (clientDetailsInfo != null ? clientDetailsInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientDetails{" +
                "id=" + id +
                ", clientDetailsInfo=" + clientDetailsInfo +
                '}';
    }
}

