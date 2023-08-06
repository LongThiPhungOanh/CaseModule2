package service.impl;
import model.Bill;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BillManager implements Serializable {
    private final static String PATH= "src/file/Bill";
    List<Bill> bills = readBill();
    public List<Bill> readBill() {
        List<Bill> billList = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            Object object = objectInputStream.readObject();
            billList = (List<Bill>) object;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
           // System.out.println(e.getMessage());
        }
        return billList;
    }
    public List<Bill> getBills(){
        return bills;
    }

    public void listBill(){
        if (bills.size() > 0){
            for (Bill value: bills ) {
                System.out.println(value);
            }
        } else {
            System.out.println("no bill");
        }
    }

    public void add(Bill bill){
        if(bill != null){
            bills.add(bill);
            write(bills);
            System.out.println("<--------------Added bill success!!!-------------->");
        }else {
            System.out.println("<--------------Failed!!!-------------->");
        }
    }

    public void write(List<Bill> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}