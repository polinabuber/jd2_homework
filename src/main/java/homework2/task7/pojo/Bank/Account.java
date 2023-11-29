package homework2.task7.pojo.Bank;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("account")
public class Account extends Bank {
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Account account = (Account) o;

        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }
}
