package homework2.task7.pojo.Client;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Embeddable
public class ClientDetailsInfo implements Serializable {
    @Column(name = "address")
    private String address;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "birthday_date")
    private Date birthdayDate;

    public ClientDetailsInfo() {
    }

    public ClientDetailsInfo(String address, Date registrationDate, Date birthdayDate) {
        this.address = address;
        this.registrationDate = registrationDate;
        this.birthdayDate = birthdayDate;
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

        ClientDetailsInfo that = (ClientDetailsInfo) o;

        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(registrationDate, that.registrationDate))
            return false;
        return Objects.equals(birthdayDate, that.birthdayDate);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (birthdayDate != null ? birthdayDate.hashCode() : 0);
        return result;
    }
}

