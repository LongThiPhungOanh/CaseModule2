package system;
import service.ProductManager;
import java.util.Scanner;
public class MenuProduct {
    private static final Scanner input = new Scanner(System.in);
    public static void menuProduct(){
        ProductManager productManager = new ProductManager();
        boolean check = true;
        while(check){
            try {
                System.out.println("1. Add Product");
                System.out.println("2. Edit Product");
                System.out.println("3. Delete Product");
                System.out.println("4. Search by name");
                System.out.println("5. Search by price");
                System.out.println("6. total money all product");
                System.out.println("7. show product");
                System.out.println("0. Back");
                System.out.println("you choice: ");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1 -> productManager.add(productManager.returnObject());
                    case 2 -> productManager.edit(productManager.returnObject());
                    case 3 -> productManager.delete();
                    case 4 -> productManager.search();
                    case 5 -> productManager.searchByPrice();
                    case 6 -> productManager.totalMoneyAllProduct();
                    case 7 -> productManager.showProduct();
                    case 0 -> check = false;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
