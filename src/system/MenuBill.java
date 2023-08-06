package system;

import service.impl.BillManager;

import java.util.Scanner;

public class MenuBill {
    public static void menuBill(){
        Scanner scanner = new Scanner(System.in);
        BillManager billManager = new BillManager();
        boolean check = true;
        while (check){
            System.out.println("|---------Menu---------|");
            System.out.println("|  1. List Bill        |");
            System.out.println("|  0. Exit             |");
            System.out.println("|----------------------|");
            System.out.println("You choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> billManager.listBill();
                case 0 -> check = false;
            }
        }
    }
}
