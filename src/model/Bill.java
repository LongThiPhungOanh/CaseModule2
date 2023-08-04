package model;
import javax.xml.crypto.Data;
import java.io.Serializable;

public class Bill implements Serializable {
    private static int INDEX = 1;
    private Customer customer;
    private int codeBill;
    private Data customerName;
    private String DateOfPayment;
    private double totalMoney;
    private int review = 0;
    public Bill(Customer customer, Data customerName, String dateOfPayment, double totalMoney, int review) {
        this.codeBill = INDEX++;
        this.customer = customer;
        this.customerName = customerName;
        DateOfPayment = dateOfPayment;
        this.totalMoney = totalMoney;
        this.review = review;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getINDEX() {
        return INDEX;
    }

    public int getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(int codeBill) {
        this.codeBill = codeBill;
    }

    public Data getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Data customerName) {
        this.customerName = customerName;
    }

    public String getDateOfPayment() {
        return DateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        DateOfPayment = dateOfPayment;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Bill(Customer customer, int codeBill, Data customerName, String dateOfPayment, double totalMoney, int review) {
        this.customer = customer;
        this.codeBill = codeBill;
        this.customerName = customerName;
        DateOfPayment = dateOfPayment;
        this.totalMoney = totalMoney;
        this.review = review;
    }
}
