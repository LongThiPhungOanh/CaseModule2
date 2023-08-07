package system;
import java.util.Scanner;

public class MenuManager {
    public static void menuManager(){
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check){
            try {
                System.out.println("|---------Menu---------|");
                System.out.println("|  1. Product Manager  |");
                System.out.println("|  2. Customer Manager |");
                System.out.println("|  3. Bill Manager     |");
                System.out.println("|  0. Back             |");
                System.out.println("|----------------------|");
                System.out.println("        You choice:     ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> MenuProduct.menuProduct();
                    case 2 -> MenuCustomer.menuCustomer();
                    case 3 -> MenuBill.menuBill();
                    case 0 -> check = false;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
