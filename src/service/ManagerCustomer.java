package service;
import model.Customer;
import model.SizeClothes;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ManagerCustomer implements Serializable{
    public final static long serialVersionUID = 594344082;
    private final static String PATH= "src/file/Customer";
    private final Scanner input = new Scanner(System.in);
    List<Customer> customerList;
    List<SizeClothes> sizeClothes;
    public List<SizeClothes> getSizeClothes() {
        return sizeClothes;
    }

    public void setSizeClothes(List<SizeClothes> sizeClothes) {
        this.sizeClothes = sizeClothes;
    }

    public ManagerCustomer(){
        customerList = new ArrayList<>();
        sizeClothes = new ArrayList<>();
        sizeClothes = this.addSize();
        customerList = readCustomer();
    }
    public List<SizeClothes> addSize(){
        List<SizeClothes> size = new ArrayList<>();
        size.add(new SizeClothes("150 - 155cm", "40 - 45", "size S"));
        size.add(new SizeClothes("156 - 160cm", "43 - 46", "size M"));
        size.add(new SizeClothes("161 - 165cm", "46 - 53", "size L"));
        size.add(new SizeClothes("166 - 170cm", "53 - 57", "size XL"));
        return size;
    }
    public void showSizeClothes(){
        System.out.println("Customer size");
        for (int i = 0; i < sizeClothes.size(); i++) {
            System.out.println(i + 1 + ". " + sizeClothes.get(i));
        }
    }
    public SizeClothes returnSize() {
        SizeClothes size = null;
        showSizeClothes();
        try {
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1 -> size = sizeClothes.get(0);
                case 2 -> size = sizeClothes.get(1);
                case 3 -> size = sizeClothes.get(2);
                case 4 -> size = sizeClothes.get(3);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } return size;
    }
  public Customer returnCustomer(SizeClothes size) {
      Customer customer = null;
      try {
          System.out.println("Input name Customer");
          String name = input.nextLine();
          System.out.println("Input age Customer");
          int age = Integer.parseInt(input.nextLine());
          System.out.println("Input NumberPhone");
          String numberPhone = input.nextLine();
          customer = new Customer(name, age, size, numberPhone);
      } catch (Exception e){
          System.out.println(e.getMessage());
      } return customer;
  }
    public void add(Customer customer) {
        if (customer != null){
            customerList.add(customer);
            writeCustomer(customerList);
            customerList = readCustomer();
            System.out.println("success!");
        } else {
            System.out.println("Invalid information");
        }
    }
    public void edit(Customer customer){
        boolean check = true;
        try {
            if (customer != null){
                System.out.println("Name you to want edit");
                String name = input.nextLine();
                for (Customer value: customerList) {
                    if (value.getName().equals(name)){
                        value.setName(customer.getName());
                        value.setAge(customer.getAge());
                        value.setSize(customer.getSize());
                        value.setNumberPhone(customer.getNumberPhone());
                        check = false;
                        writeCustomer(customerList);
                        customerList = readCustomer();
                        System.out.println("success!");
                        break;
                    }
                } if (check){
                    System.out.println("Name not found");
                }
            } else {
                System.out.println("Invalid information");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void delete() {
        try {
            System.out.println("Name you to want delete");
            String name = input.nextLine();
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getName().equals(name)) {
                    System.out.println("You sure you want to delete?");
                    System.out.println("1. yes");
                    System.out.println("2. Noo");
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1) {
                        customerList.remove(i);
                        writeCustomer(customerList);
                        customerList = readCustomer();
                        System.out.println("delete success!");
                        break;
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void searchByName(){
        boolean check = true;
        try {
            System.out.println("Input name you want to search: ");
            String search = input.nextLine();
            for (Customer customer : customerList) {
                if (customer.getName().toLowerCase().contains(search.toLowerCase())) {
                    System.out.println(customer);
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
    public void displayCustomer(){
        if (customerList.size() > 0){
            for (Customer value: customerList) {
                System.out.println(value);
            }
        } else {
            System.out.println("no customer");
        }
    }
    public List<Customer> readCustomer() {
        List<Customer> customers = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            Object object = objectInputStream.readObject();
            customers = (List<Customer>) object;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            //System.out.println(e.getMessage());
        }
        return customers;
    }

    public void writeCustomer(List<Customer> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
