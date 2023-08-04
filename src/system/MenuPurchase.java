package system;
import service.impl.PurchaseManager;
import java.util.Scanner;
public class MenuPurchase {
    public static void menuPurchase(){
        Scanner scanner = new Scanner(System.in);
        PurchaseManager purchaseManager = new PurchaseManager();
        boolean check = true;
        while (check) {
            System.out.println("1. display all product");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1 -> purchaseManager.display();
                case 0 -> check = false;
            }
        }
    }
}
