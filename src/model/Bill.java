package model;

import java.io.Serializable;

public class Bill implements Serializable {
    private static int INDEX = 1;
    private int codeBill;
    private String customerName;
    private String DateOfPayment;
    private double totalMoney;

    public Bill(String customerName, String dateOfPayment, double totalMoney) {
        this.codeBill = INDEX++;
        this.customerName = customerName;
        DateOfPayment = dateOfPayment;
        this.totalMoney = totalMoney;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
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

    @Override
    public String toString() {
        return "Bill{" +
                "INDEX=" + INDEX +
                ", codeBill='" + codeBill + '\'' +
                ", customerName='" + customerName + '\'' +
                ", DateOfPayment='" + DateOfPayment + '\'' +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
