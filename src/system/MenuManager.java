package system;

import java.util.Scanner;

public class MenuManager {
    public static void menuManager(){
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check){
            System.out.println("1. Product Manager");
            System.out.println("2. Customer Manager");
            System.out.println("0. Exit");
            System.out.println("You choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> MenuProduct.menuProduct();
                case 2 -> MenuCustomer.menuCustomer();
                case 0 -> check = false;
            }
        }
    }
}
