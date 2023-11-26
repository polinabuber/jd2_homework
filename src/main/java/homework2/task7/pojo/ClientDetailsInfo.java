package homework2.task7.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Embeddable
public class ClientDetailsInfo implements Serializable {
    private String address;
    private Date registrationDate;
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
}

