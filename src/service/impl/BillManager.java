package service.impl;
import model.Bill;
import service.CRUD;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BillManager implements CRUD<Bill>,Serializable {
    private final static String PATH= "src/file/Bill";

    @Override
    public void delete() {

    }

    @Override
    public void search() {

    }

    @Override
    public void add(Bill bill) {

    }

    @Override
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
    @Override
    public void write(List<Bill> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}