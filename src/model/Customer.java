package model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private int age;
    private SizeClothes size;
    private String numberPhone;

    public Customer(String name, int age, SizeClothes size, String numberPhone) {
        this.name = name;
        this.age = age;
        this.size = size;
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SizeClothes getSize() {
        return size;
    }

    public void setSize(SizeClothes size) {
        this.size = size;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", size=" + size +
                ", numberPhone='" + numberPhone + '\'' +
                '}';
    }
}
