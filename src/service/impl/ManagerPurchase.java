package service.impl;
import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerPurchase extends ProductManager{
    private final Scanner input = new Scanner(System.in);
    List<Product> productDisPlay;
    List<Product> listOfProductManager = productList;;
    public ManagerPurchase(){
        productDisPlay = new ArrayList<>();
        productDisPlay = returnListDisplay();
    }
    public List<Product> returnListDisplay(){
        List<Product> products = new ArrayList<>();
        for (Product product : listOfProductManager) {
            products.add(new Product(product.getName(), product.getPrice()));
        }
        return products;
    }
    public void display(){
        for (Product value: productList) {
            System.out.println(value);
        }
        try {
            System.out.println("See details input 1!");
            int choice = Integer.parseInt(input.nextLine());
            if (choice == 1){
                SeeDetails();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void SeeDetails(){
        try {
            boolean check = true;
            System.out.println("Name you to want see details");
            String name = input.nextLine();
            for (Product value : listOfProductManager){
                if (value.getName().equals(name)){
                    System.out.println(value);
                    check = false;
                }
            } if (check){
                System.out.println("Name not found");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void purchase(){

    }
}
