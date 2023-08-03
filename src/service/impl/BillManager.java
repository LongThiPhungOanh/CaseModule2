package service.impl;
import model.Bill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BillManager implements Serializable {
    private final static String PATH= "src/file/Bill";
    public void delete() {

    }


    public void search() {

    }



    public void add(Bill bill) {

    }

       public List<Bill> read() {
        List<Bill> bills = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            Object object = objectInputStream.readObject();
            bills = (List<Bill>) object;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
        }
        return bills;
    }
     public void write(List<Bill> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}