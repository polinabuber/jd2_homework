package homework2.task7.pojo.Expenses;

import homework2.task7.pojo.Client.*;
import homework2.task7.pojo.Receiver.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;
@Entity
@Table(name="expenses")

public class Expenses implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "paydate")
    @Temporal(TemporalType.DATE)
    private Date paydate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    private Receiver receiver;
    @Column(name = "value")
    private double value;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


    public Expenses() {
    }

    public Expenses(int id, Date paydate, Receiver receiver, double value) {
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

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
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
        if (Double.compare(expenses.value, value) != 0) return false;
        if (!Objects.equals(paydate, expenses.paydate)) return false;
        return Objects.equals(receiver, expenses.receiver);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (paydate != null ? paydate.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", paydate=" + paydate +
                ", receiver=" + receiver +
                ", value=" + value +
                '}';
    }
}
