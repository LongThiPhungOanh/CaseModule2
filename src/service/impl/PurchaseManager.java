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
            System.out.println("How many products to buy?");
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
                if (pay(productList.get(id).getPrice())){
                    write(productList);
                    productList = read();
                }
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
                System.out.println("Product :" + productList.get(id).getName() + " , "  + " Price: " + productList.get(id).getPrice());
                System.out.println("the sum of all money is: " + total);
            }
            System.out.println("the total amount you have to pay is: " + total);
                if (pay(total)){
                    write(productList);
                    productList = read();
                }

        } catch (Exception e){
            System.out.println(e.getMessage());
        } return productsList;
    }
    public boolean pay(double total){
        boolean checkQuantity = false;
        try {
            System.out.println("1. cash ");
            System.out.println("2. card payment");
            int choice = Integer.parseInt(input.nextLine());
            if (choice == 1){
                System.out.println("thank you here is your bill");
                checkQuantity = true;
            } if (choice == 2){
                System.out.println("input name");
                String name = input.nextLine();
                System.out.println("input phone number");
                String numberPhone = input.nextLine();
                boolean check = true;
                for (int i = 0; i < managerCustomer.customerList.size(); i++) {
                    if (managerCustomer.customerList.get(i).getName().equals(name) && managerCustomer.customerList.get(i).getNumberPhone().equals(numberPhone)){
                        managerCustomer.customerList.get(i).setMoney(managerCustomer.customerList.get(i).getMoney() - total);
                        managerCustomer.writeCustomer(managerCustomer.customerList);
                        managerCustomer.readCustomer();
                        check = false;
                        checkQuantity = true;
                    }
                } if (check){
                    System.out.println("name or phone number not found");
                }
                System.out.println("thank you here is your bill");
            } else {
                System.out.println("incorrect information");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        } return checkQuantity;
    }
}
