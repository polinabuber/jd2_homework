package homework2.task7.pojo.TransactionPerClass;

import javax.persistence.*;
import java.util.*;

@Entity
public class CardTransactions extends Transactions {
    private String cardNumber;
    private String cardHolderName;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CardTransactions that = (CardTransactions) o;

        if (!Objects.equals(cardNumber, that.cardNumber)) return false;
        return Objects.equals(cardHolderName, that.cardHolderName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardHolderName != null ? cardHolderName.hashCode() : 0);
        return result;
    }
}
