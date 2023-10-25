package homework2.task7;

import java.util.*;

public class Expenses {
    private int id;
    private String paydate;
    private int receiver;
    private double value;

    public Expenses() {
    }

    public Expenses(int id, String paydate, int receiver, double value) {
        this.id = id;
        this.paydate = paydate;
        this.receiver = receiver;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expenses expenses = (Expenses) o;

        if (id != expenses.id) return false;
        if (receiver != expenses.receiver) return false;
        if (Double.compare(expenses.value, value) != 0) return false;
        return Objects.equals(paydate, expenses.paydate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (paydate != null ? paydate.hashCode() : 0);
        result = 31 * result + receiver;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", paydate='" + paydate + '\'' +
                ", receiver=" + receiver +
                ", value=" + value +
                '}';
    }
}
