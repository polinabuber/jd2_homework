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
    @Column(name = "address")
    private String address;
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;

    public ClientDetails() {
    }

    public ClientDetails(int id, String address, Date registrationDate, Date birthdayDate) {
        this.id = id;
        this.address = address;
        this.registrationDate = registrationDate;
        this.birthdayDate = birthdayDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDetails that = (ClientDetails) o;

        if (id != that.id) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(registrationDate, that.registrationDate))
            return false;
        return Objects.equals(birthdayDate, that.birthdayDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (birthdayDate != null ? birthdayDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientDetails{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", registrationDate=" + registrationDate +
                ", birthdayDate=" + birthdayDate +
                '}';
    }
}
