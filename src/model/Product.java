package model;

import java.io.Serializable;

public class Product implements Serializable {
    private static long serialUID = 12345678;
    private long id;
    private String name;
    private double price;
    private int quantity;
    private SizeClothes sizeClothes;
    private String category;
    private String fabric;
    public static int INDEX = 0;

    public Product() {}

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public Product(String name, double price, int quantity, SizeClothes sizeClothes, String category, String fabric) {
        this.id = INDEX++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sizeClothes = sizeClothes;
        this.category = category;
        this.fabric = fabric;
    }
    public Product(String name, double price, int quantity, String category, String fabric) {
        this.id = INDEX++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.fabric = fabric;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public static long getSerialUID() {
        return serialUID;
    }

    public static void setSerialUID(long serialUID) {
        Product.serialUID = serialUID;
    }

    public SizeClothes getDescribe() {
        return sizeClothes;
    }

    public void setDescribe(SizeClothes describe) {
        this.sizeClothes = sizeClothes;
    }

    @Override
    public String toString() {
        return "Product { " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", price = " + price +
                ", quantity = " + quantity +
                ", sizeClothes = '" + sizeClothes + '\'' +
                ", category = " + category +
                ", fabric = '" + fabric + '\'' +
                '}';
    }
}