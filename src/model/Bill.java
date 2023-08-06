package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Bill implements Serializable {
    private int id;
    private String name;
    private double total;
    private String createDate = LocalDateTime.now().toString();
    private Map<Product, String> mapProduct = new HashMap<>();

    public Bill(int id, String name, double total, Map<Product, String> mapProduct) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.mapProduct = mapProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Map<Product, String> getMapProduct() {
        return mapProduct;
    }

    public void setMapProduct(Map<Product, String> mapProduct) {
        this.mapProduct = mapProduct;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", createDate=" + createDate.substring(0, 19) +
                ", mapProduct=" + mapProduct +
                '}';
    }
}
