package homework2.task7.pojo.Product;

import javax.persistence.*;

@Entity
public class Investment extends Product {
    private double returnRate;

    public double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(double returnRate) {
        this.returnRate = returnRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Investment that = (Investment) o;

        return Double.compare(that.returnRate, returnRate) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(returnRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
