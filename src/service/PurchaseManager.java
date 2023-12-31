package service;
import model.Bill;
import model.Customer;
import model.Product;
import java.util.*;
public class PurchaseManager extends ProductManager{
    private final Scanner input = new Scanner(System.in);
    private final BillManager billManager = new BillManager();
    List<Bill> billList;
    Map<Product, String> productMap = new HashMap<>();
    ManagerCustomer managerCustomer;
    public PurchaseManager(){
        managerCustomer = new ManagerCustomer();
        billList = billManager.readBill();
    }
    public void display(){
        try {
            if (productList.size() > 0) {
                System.out.println("<--------------------------------------Product List-------------------------------------->");
                for (Product value : productList) {
                    if(value.getDeleteYn().equals("N") && value.getQuantity() > 0){
                        value.setSizeClothes(null);
                        System.out.println(value);
                    }
                }
                System.out.println("<---------------------------------------------------------------------------------------->");
            } else {
                System.out.println("<-------------------List is empty!!!------------------->");
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void choice() {
        String option = "1";
        try {
        while (!option.equals("0")) {
                display();
                System.out.println("Please enter ID of the product which you want to buy: ");
                int id = Integer.parseInt(input.nextLine());
                if (id < 0 || id > productList.size() - 1) {
                    System.out.println("<--------------------Have no this ID!!!-------------------->");
                    continue;
                }
                System.out.println("Please enter quantity: ");
                String quantity = input.nextLine();
                if (Integer.parseInt(quantity) == 0 || Integer.parseInt(quantity) < 0 || Integer.parseInt(quantity) > productList.get(id).getQuantity()) {
                    System.out.println("<--------------Input information is incorrect!!!------------->");
                    continue;
                }
                managerCustomer.showSizeClothes();
                int size = Integer.parseInt(input.nextLine());
                if (size > 0 && size < 5) {
                    Product product = productList.get(id);
                    product.setSizeClothes(managerCustomer.getSizeClothes().get(size - 1));
                    productMap.put(product, quantity);
                    System.out.println("<--------------------Added to cart!!!<--------------------");
                } else {
                    System.out.println("<--------------------Have no this size!!!-------------------->");
                }
                System.out.println("|-----Menu------|");
                System.out.println("|    1. Buy     |");
                System.out.println("|    0. Back    |");
                System.out.println("|---------------|");
                option = input.nextLine();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void showCart(String account){
        Set<Product> set = productMap.keySet();
        try {
            if (!set.isEmpty()) {
                System.out.println("<-------------------Product List------------------->");
                for (Product key : set) {
                    System.out.println("Product: " + key + " quantity" + productMap.get(key));
                }
                System.out.println("<-------------------------------------------------->");
                System.out.println();
            } else {
                System.out.println("<-------------------Card is empty!!!------------------->");
                System.out.println();
            }
            pay(account);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void pay(String account){
        String option = "1";
        Set<Product> set = productMap.keySet();
        while (!option.equals("0") && !productMap.isEmpty()) {
            try {
                System.out.println("|--------Menu--------|");
                System.out.println("|      1. Pay        |");
                System.out.println("|      0. Back       |");
                System.out.println("|--------------------|");
                System.out.println("      Your option:    ");
                option = input.nextLine();
                if (option.equals("1") ) {
                    boolean check = false;
                    System.out.println("Have you registered as a user yet?");
                    System.out.println("1. yes");
                    System.out.println("2. no");
                    System.out.println("you choice");
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1){
                        System.out.println("Please enter phone number to find information");
                        String phoneNumber = input.nextLine();
                        for (Customer value: managerCustomer.customerList) {
                            if (phoneNumber.equals(value.getNumberPhone())){
                                check = true;
                                System.out.println("you get 20% off");
                                break;
                            }
                        }
                        if (check){
                            double total = 0;
                            for (Product key : set) {
                                total += (key.getPrice() * 80 / 100) * Double.parseDouble(productMap.get(key));
                            }
                            String name = account;
                            int id = billManager.getBills().size();
                            Bill bill = new Bill(id, name, total, productMap);
                            billManager.add(bill);
                            for (Product p : productList) {
                                Set<Product> setP = productMap.keySet();
                                for (Product key : setP) {
                                    if (key.getId() == p.getId()) {
                                        p.setQuantity(p.getQuantity() - Integer.parseInt(productMap.get(key)));
                                        break;
                                    }
                                }
                            }
                            super.write(productList);
                            productMap.clear();
                        } else {
                            System.out.println("Phone number not found!");
                        }
                    } else if (choice == 2){
                        if (productMap.size() > 0) {
                            double total = 0;
                            //Set<Product> set = productMap.keySet();
                            for (Product key : set) {
                                total += key.getPrice() * Double.parseDouble(productMap.get(key));
                            }
                            String name = account;
                            int id = billManager.getBills().size();
                            Bill bill = new Bill(id, name, total, productMap);
                            billManager.add(bill);
                            for (Product p : productList) {
                                Set<Product> setP = productMap.keySet();
                                for (Product key : setP) {
                                    if (key.getId() == p.getId()) {
                                        p.setQuantity(p.getQuantity() - Integer.parseInt(productMap.get(key)));
                                        break;
                                    }
                                }
                            }
                            super.write(productList);
                            productMap.clear();
                    }
                    } else {
                        System.out.println("<-----------Cart is empty or Input information is incorrect!!!------------->");
                    }
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void showBill(String account){
        boolean isEmpty = true;
        billList = billManager.readBill();
        System.out.println("-------------List Bill-------------");
        for (Bill bill : billList) {
            if (bill.getName().equals(account)) {
                System.out.println(bill);
                isEmpty = false;
            }
         }
        if (isEmpty) {
            System.out.println("<---------Have no data!!!---------->");
        }
    }
}
