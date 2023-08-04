package service.impl;
import model.Product;
import model.SizeClothes;

import java.io.*;
import java.util.*;
public class ProductManager implements Serializable{
    //private static long serialVersionUID = 12345678;
    private final Scanner input = new Scanner(System.in);
    List<Product> productList;
    private final static String PATH = "src/file/Product";
    public ProductManager(){
        productList = new ArrayList<>();
        productList = read();
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
                boolean check = true;
                System.out.println("Input id you to want edit");
                int id = Integer.parseInt(input.nextLine());
                for (Product value : productList) {
                    if (id == value.getId()) {
                        value.setName(product.getName());
                        value.setPrice(product.getPrice());
                        value.setQuantity(product.getQuantity());
                        value.setCategory(product.getCategory());
                        value.setFabric(product.getFabric());
                        System.out.println("success!");
                        check = false;
                        break;
                    }
                } if (check){
                    System.out.println("Id not found!");
                }
            } else {
                System.out.println("Invalid product!");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void delete() {
        try {
            System.out.println("Id product you to want delete!");
            int delete = Integer.parseInt(input.nextLine());
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
    public void showProduct(){
        if (productList.size() > 0){
            for (Product value: productList ) {
                System.out.println(value);
            }
        } else {
            System.out.println("no product");
        }
    }
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
    public void write(List<Product> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

