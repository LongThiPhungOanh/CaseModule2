package service.impl;
import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class PurchaseManager extends ProductManager{
    private final Scanner input = new Scanner(System.in);
    ManagerCustomer managerCustomer;
    public PurchaseManager(){
        managerCustomer = new ManagerCustomer();
    }
    public void display(){
        try {
            for (Product value: productList) {
                System.out.println(value.toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void choice() {
        display();
        int choiceSize;
        try {
            System.out.println("Input your clothing size");
            managerCustomer.showSizeClothes();
            choiceSize = Integer.parseInt(input.nextLine());
            System.out.println("What products do you buy?");
            int product = Integer.parseInt(input.nextLine());
            if (product == 1) {
                System.out.println("Input product id you want to buy");
                int id = Integer.parseInt(input.nextLine());
                if (id >= 0 && id < productList.size()) {
                    payNow(id, choiceSize);
                }
            } if (product > 1) {
                cart(choiceSize,product);
            }if (product < 1){
                System.out.println("product id is not correct ");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public boolean confirm() {
        boolean check = false;
        try {
            System.out.println("1. yes");
            System.out.println("2. no");
            int choice = Integer.parseInt(input.nextLine());
            if (choice == 1){
                check = true;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } return check;
    }
    public Product payNow(int id,int size) {
        Product product = null;
        try {
            System.out.println("the total amount you have to pay is: " + productList.get(id).getPrice());
            System.out.println("Are you sure you want to pay?");
            if (confirm()){
                System.out.println("the total amount you have to pay is: " + (productList.get(id).getPrice() * 80 / 100));
                product = new Product(productList.get(id).getName(), productList.get(id).getPrice(),
                        productList.get(id).getQuantity(), managerCustomer.getSizeClothes().get(size - 1),
                        productList.get(id).getCategory(), productList.get(id).getFabric());
                System.out.println(product);
                productList.get(id).setQuantity(productList.get(id).getQuantity() - 1);
                System.out.println("tks");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        } return product;
    }
    public List<Product> cart(int size, int length){
        List<Product> productsList = new ArrayList<>();
        double total = 0;
        try {
            for (int i = 0; i < length; i++) {
                display();
                System.out.println("Input product id you want to buy");
                int id = Integer.parseInt(input.nextLine());
                productsList.add(new Product(productList.get(id).getName(), productList.get(id).getPrice(),
                        productList.get(id).getQuantity(), managerCustomer.getSizeClothes().get(size - 1),
                        productList.get(id).getCategory(), productList.get(id).getFabric()));
                total += productList.get(id).getPrice();
                productList.get(id).setQuantity(productList.get(id).getQuantity() - 1);
                System.out.println("successfully added to cart");
                System.out.println("Name : " + productList.get(id).getName() + ',' + productList.get(id).getPrice());
                System.out.println("the sum of all money is: " + total);
            }
            System.out.println("the total amount you have to pay is: " +total);
            System.out.println("Are you sure you want to pay?");
            if (confirm()){
                System.out.println("tks");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } return productsList;
    }
}
