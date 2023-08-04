package service.impl;
import model.Bill;
import model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BillManager extends PurchaseManager implements Serializable {
    private final static String PATH= "src/file/Bill";
    List<Bill> billList;
    public BillManager(){
        billList = new ArrayList<>();
        billList = readBill();
    }

    public void delete() {
    }


    public void search() {

    }



    public void addBill(Product product) {
        
    }

       public List<Bill> readBill() {
        List<Bill> bills = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            Object object = objectInputStream.readObject();
            bills = (List<Bill>) object;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
        }
        return bills;
    }
     public void writeBill(List<Bill> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}