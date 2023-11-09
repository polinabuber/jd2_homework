package homework2.task7.pojo;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "receivers")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public Receiver() {
    }

    public Receiver(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receiver receiver = (Receiver) o;

        if (id != receiver.id) return false;
        return Objects.equals(name, receiver.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
