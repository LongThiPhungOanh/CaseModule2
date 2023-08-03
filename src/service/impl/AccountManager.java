package service.impl;
import model.Account;
import java.io.*;
import java.util.*;
public class AccountManager implements Serializable{
    private final static String PATH= "src/file/Account";
    private final Scanner input = new Scanner(System.in);
    List<Account> account;
    public AccountManager(){
        account = new ArrayList<>();
        account = read();
    }
    public String[] returnAccount(){
        String[] returnAccount = new String[2];
        try {
            System.out.println("UserName");
            String account = input.nextLine();
            System.out.println("Password");
            String password = input.nextLine();
            returnAccount[0] = account;
            returnAccount[1] = password;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } return returnAccount;
    }

    //Sig Up account.
    public void add(String[] str) {
        boolean check = false;
        try {
            for (Account value : account) {
                if (value.getAccount().equals(str[0]) && value.getPassword().equals(str[1])) {
                    System.out.println("Account already exists!");
                    check = true;
                    break;
                }
            }
            if (!check) {
                account.add(new Account(str[0], str[1]));
                write(account);
                account = read();
                System.out.println("Sign Up Success");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public int login(String[] str) {
        int check = -1;
        for (Account value : account) {
            if (value.getAccount().equals(str[0]) && value.getPassword().equals(str[1])){
                if (value.getRole().equals("Admin")){
                    check =  1;
                    break;
                }else {
                    check = 0;
                }
            }
        }
        return check;
    }
    public void displayTest(int check){
        if (check == -1){
            System.out.println("k co tai khoan nha ");
        } if (check == 0){
            System.out.println("ng dung");
        } if (check == 1){
            System.out.println("admin");
        }
    }

    public void delete(String[] str) {
        try {
        for (int i = 0; i < account.size(); i++) {
            if (account.get(i).getAccount().equals(str[0]) && account.get(i).getPassword().equals(str[1])){
                if (account.get(i).getRole().equals("Admin")){
                    System.out.println("account cannot be deleted!");
                    break;
                } if (account.get(i).getRole().equals("User")){
                    System.out.println("Are you sure you want to delete?");
                    System.out.println("1. yes");
                    System.out.println("2. no");
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1){
                        account.remove(i);
                        write(account);
                        account = read();
                        System.out.println("delete success!");
                        break;
                    }
                }
            }
        }
    } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<Account> read() {
        List<Account> accounts;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            Object object = objectInputStream.readObject();
            accounts = (List<Account>) object;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            account.add(new Account("Admin", "123", "Admin"));
            account.add(new Account("Oanh", "123"));
            write(account);
            accounts = account;
            System.out.println(e.getMessage());
        }
        return accounts;
    }
    public void write(List<Account> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
