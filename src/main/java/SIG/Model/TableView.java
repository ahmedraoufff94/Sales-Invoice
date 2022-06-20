
package SIG.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableView extends AbstractTableModel {
    private ArrayList<InvoiceHeader> invoicess;

    private String []table_Columns = {"Inv. no","Date","Name","Total"};
    
    public TableView(ArrayList<InvoiceHeader> invoicess) {
        this.invoicess = invoicess;
    }

    @Override
    public String getColumnName(int column) {
        return table_Columns[column] ; 
    }
    
  
    
    @Override
    public int getRowCount() {
       return invoicess.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invv = invoicess.get(rowIndex);
        switch (columnIndex){
            case 0: return invv.getNum();
            case 1: return invv.getDate();
            case 2: return invv.getCustomer();
            case 3: return invv.getTotal_price();
            default:return "";
        }
        
    }
}
