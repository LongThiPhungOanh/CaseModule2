package model;

import java.io.Serializable;
public class SizeClothes implements Serializable {
   private String height;
   private String weight;
   private String size;

    public SizeClothes(String height, String weight, String size) {
        this.height = height;
        this.weight = weight;
        this.size = size;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SizeClothes{" +
                "height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
