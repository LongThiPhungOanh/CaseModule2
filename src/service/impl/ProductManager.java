package service.impl;

import model.Product;
import model.SizeClothes;
import service.CRUD;

import java.io.*;
import java.util.*;

public class ProductManager implements CRUD<Product>, Serializable{
    private final Scanner input = new Scanner(System.in);
    List<Product> productList;
    List<SizeClothes> sizeClothes;
    private final static String PATH = "src/file/Product";
    public ProductManager(){
        productList = new ArrayList<>();
        sizeClothes = new ArrayList<>();
        sizeClothes = this.returnSize();
        productList.add(new Product("Váy body", 120000,20,"Váy","Vải mịn"));
    }
    public List<SizeClothes> returnSize(){
        List<SizeClothes> size = new ArrayList<>();
        size.add(new SizeClothes("150 - 155cm", "40 - 45", "size S"));
        size.add(new SizeClothes("156 - 160cm", "43 - 46", "size M"));
        size.add(new SizeClothes("161 - 165cm", "46 - 53", "size L"));
        size.add(new SizeClothes("166 - 170cm", "53 - 57", "size XL"));
        return size;
    }
    public Product returnObject(){
        Product product = null;
        try {
            System.out.println("Input Name");
            String name = input.nextLine();
            System.out.println("Input Price");
            double price = Double.parseDouble(input.nextLine());
            System.out.println("Input Quantity");
            int quantity = Integer.parseInt(input.nextLine());
            System.out.println("Input Category");
            String category = input.nextLine();
            System.out.println("Input fabric");
            String fabric = input.nextLine();
            product = new Product(name,price,quantity,category,fabric);
        } catch (Exception e){
            System.out.println(e.getMessage());
        } return product;
    }
    @Override
    public void add(Product product) {
        if (product != null){
            productList.add(product);
            System.out.println("success!");
            write(productList);
            productList = read();
        } else {
            System.out.println("Invalid product!");
        }
    }
    public void edit(Product product) {
        try {
            if (product != null) {
                System.out.println("Input id you to want edit");
                int id = input.nextInt();
                for (Product value : productList) {
                    if (id == value.getId()) {
                        value.setName(product.getName());
                        value.setPrice(product.getPrice());
                        value.setQuantity(product.getQuantity());
                        value.setCategory(product.getCategory());
                        value.setFabric(product.getFabric());
                        System.out.println("success!");
                        break;
                    }
                }
            } else {
                System.out.println("Invalid product!");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void delete() {
        try {
            System.out.println("Id product you to want delete!");
            int delete = input.nextInt();
            for (int i = 0; i < productList.size(); i++){
                if (delete == productList.get(i).getId()){
                    System.out.println("are you sure you want to delete?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1){
                        productList.remove(i);
                        write(productList);
                        productList = read();
                        System.out.println("delete success!");
                        break;
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void search() {
        //tim theo name
        try {
            boolean check = true;
            System.out.println("Input name you want to search: ");
            String search = input.nextLine();
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(search.toLowerCase())) {
                    System.out.println(product);
                    check = false;
                }
            }
            if (check) {
                System.out.println("Not exist product have name contains this word!");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void searchByPrice() {
        boolean check = true;
        System.out.println("Input min price you want search: ");
        double min = Double.parseDouble(input.nextLine());
        System.out.println("Input max price you want search: ");
        double max = Double.parseDouble(input.nextLine());
        if (min > max) {
            System.out.println("Please input again!");
        } else {
            for (Product product : productList) {
                if (product.getPrice() < max && product.getPrice() > min) {
                    System.out.println(product);
                    check = false;
                }
            }
            if (check) {
                System.out.println("Not exist product have price between your input!");
            }
        }
    }
    public void totalMoneyAllProduct(){
        double total =  0;
        for (Product product : productList) {
            total += (product.getPrice() * product.getQuantity());
        }
        System.out.println("All money in stock is: " + total);
    }
    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            Object object = objectInputStream.readObject();
            products = (List<Product>) object;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public void write(List<Product> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

