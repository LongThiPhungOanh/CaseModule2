package service.impl;
import model.Bill;
import model.Product;
import java.io.*;
import java.util.*;

public class PurchaseManager extends ProductManager{
    private final Scanner input = new Scanner(System.in);
    private final static String PATH= "src/file/Bill";

    private BillManager billManager = new BillManager();
    List<Bill> billList;

    Map<Product, String> productMap = new HashMap<Product, String>();
    ManagerCustomer managerCustomer;
    public PurchaseManager(){
        managerCustomer = new ManagerCustomer();
        billList = billManager.readBill();
    }
    public void display(){
        if(productList.size() > 0){
            System.out.println("<--------------------------------------Product List-------------------------------------->");
            for (Product value: productList) {
                System.out.println(value.toString());
            }
            System.out.println("<---------------------------------------------------------------------------------------->");
        }else {
            System.out.println("<-------------------List is empty!!!------------------->");
            System.out.println();
        }
    }
    public void choice() {
        String option = "1";
        while (!option.equals("0")){
            display();
            System.out.println("Please enter ID of the product which you want to buy: ");
            int id = Integer.parseInt(input.nextLine());
            if(id < 0 || id > productList.size()-1){
                System.out.println("<--------------------Have no this ID!!!-------------------->");
                continue;
            }
            System.out.println("Please enter quantity: ");
            String quantity = input.nextLine();
            managerCustomer.showSizeClothes();
            int size =  Integer.parseInt(input.nextLine());
            if (size > 0 && size < 5){
                Product product = productList.get(id);
                product.setSizeClothes(managerCustomer.getSizeClothes().get(size-1));
                productMap.put(product, quantity);
                System.out.println("<--------------------Added to cart!!!<--------------------");
            }else {
                System.out.println("<--------------------Have no this size!!!-------------------->");
            }
            System.out.println("|-----Menu------|");
            System.out.println("|    1. Buy     |");
            System.out.println("|    0. Back    |");
            System.out.println("|---------------|");
            option = input.nextLine();
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


    public double totalMoney(List<Product> products){
        double total = 0;
        if (products.size() > 0) {
            for (Product value : products) {
                total += value.getPrice();
            }
        }else {
            System.out.println("no product");
        } return total;
    }

    public String[] returnCustomerInformation(){
        String[] strings = new String[2];
        try {
            System.out.println("input name");
            String name = input.nextLine();
            strings[0] = name;
            System.out.println("input phone number");
            String numberPhone = input.nextLine();
            strings[1] = numberPhone;
        } catch (Exception e){
            System.out.println(e.getMessage());
        } return strings;
    }

    public void writeBill(List<Bill> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCart(String account){
        Set<Product> set = productMap.keySet();
        if(!set.isEmpty()){
            System.out.println("<-------------------Product List------------------->");
            for (Product key : set) {
                System.out.println("Product: " + key + " quantity" + productMap.get(key));
            }
            System.out.println("<-------------------------------------------------->");
            System.out.println();
        }else {
            System.out.println("<-------------------Card is empty!!!------------------->");
            System.out.println();
        }
        pay(account);
    }

    public void pay(String account){
        String option = "1";
        while (!option.equals("0")){
            System.out.println("|--------Menu--------|");
            System.out.println("|        1. Pay      |");
            System.out.println("|        2. My Bill  |");
            System.out.println("|        0. Back     |");
            System.out.println("|--------------------|");
            System.out.println("Your option: ");
            option = input.nextLine();
            if (option.equals("1")){
                if(productMap.size() > 0){
                    double total = 0;
                    Set<Product> set = productMap.keySet();
                    for (Product key : set) {
                        total += key.getPrice()* Double.parseDouble(productMap.get(key));
                    }
                    String name = account;
                    int id = billList.size();
                    Bill bill = new Bill(id, name, total, productMap);
                    billManager.add(bill);
                    for(Product p: productList){
                        Set<Product> setP = productMap.keySet();
                        for (Product key : setP) {
                            if(key.getId() == p.getId()){
                                p.setQuantity(p.getQuantity() - Integer.parseInt(productMap.get(key)));
                                break;
                            }
                        }
                    }
                    super.write(productList);
                    productMap.clear();
                }else {
                    System.out.println("<---------------Cart is empty!!!---------------->");
                }
            }else if(option.equals("2")){
                List<Bill> myBill = billManager.getBills();
                boolean isEmpty = true;
                System.out.println("-------------List Bill-------------");
                for (Bill bill: myBill){
                    if (bill.getName().equals(account)){
                        System.out.println(bill);
                        isEmpty = false;
                    }
                }
                if(isEmpty){
                    System.out.println("<---------Have no data!!!---------->");
                }
                    System.out.println("<---------------------------------->");
            }
        }
    }
}
