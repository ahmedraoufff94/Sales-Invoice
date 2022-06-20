
package SIG.Model;

import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader {
    private int num;
    private String customer;
    private String date;
    private int total_price;
    private ArrayList<InvoiceLine>lines;

    public InvoiceHeader(int num, String customer, String date) {
        this.num = num;
        this.customer = customer;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<InvoiceLine> getLines() {
        if(lines==null){
        lines=new ArrayList<>(); }
        
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public int getTotal_price() {
       int total=0;
        for (InvoiceLine line : lines) {
         total=total+ line.getTotal_line();
        }
        return total;
    }
    
   
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    
    
    
    
}
