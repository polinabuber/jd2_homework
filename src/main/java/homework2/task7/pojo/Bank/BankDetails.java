package homework2.task7.pojo.Bank;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("bank_details")
public class BankDetails extends Bank {
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BankDetails that = (BankDetails) o;

        return Objects.equals(bankName, that.bankName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        return result;
    }
}

