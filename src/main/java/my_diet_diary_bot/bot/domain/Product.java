package my_diet_diary_bot.bot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "rawWeight")
    private double rawWeight;
    @Column(name = "readyWeight")
    private double readyWeight;
    @Column(name = "oneConsumeWeight")
    private double oneConsumeWeight;
    @Column(name = "resultWeight")
    private double resultWeight;
    @Column(name = "currentEdit")
    private boolean currentEdit;

    public Product() {
    }

    public Product(String name, double rawWeight, double readyWeight, double oneConsumeWeight, double resultWeight, boolean currentEdit) {
        this.name = name;
        this.rawWeight = rawWeight;
        this.readyWeight = readyWeight;
        this.oneConsumeWeight = oneConsumeWeight;
        this.resultWeight = resultWeight;
        this.currentEdit = currentEdit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRawWeight() {
        return rawWeight;
    }

    public void setRawWeight(double rawWeight) {
        this.rawWeight = rawWeight;
    }

    public double getReadyWeight() {
        return readyWeight;
    }

    public void setReadyWeight(double readyWeight) {
        this.readyWeight = readyWeight;
    }

    public double getOneConsumeWeight() {
        return oneConsumeWeight;
    }

    public void setOneConsumeWeight(double oneConsumeWeight) {
        this.oneConsumeWeight = oneConsumeWeight;
    }

    public double getResultWeight() {
        return resultWeight;
    }

    public void setResultWeight(double resultWeight) {
        this.resultWeight = resultWeight;
    }

    public boolean isCurrentEdit() {
        return currentEdit;
    }

    public void setCurrentEdit(boolean currentEdit) {
        this.currentEdit = currentEdit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.rawWeight, rawWeight) == 0 &&
                Double.compare(product.readyWeight, readyWeight) == 0 &&
                Double.compare(product.oneConsumeWeight, oneConsumeWeight) == 0 &&
                Double.compare(product.resultWeight, resultWeight) == 0 &&
                currentEdit == product.currentEdit &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rawWeight, readyWeight, oneConsumeWeight, resultWeight, currentEdit);
    }
}
