
package SIG.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LineTableView extends AbstractTableModel{
private ArrayList<InvoiceLine> lines;
private String[] col = {"no","item","price","count","total price"};

    public LineTableView(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }


 @Override
    public String getColumnName(int column) {
        return col[column] ; 
    }

    @Override
    public int getRowCount() {
      return lines.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine inv = lines.get(rowIndex);
        switch (columnIndex){
            case 0: return inv.getInvoice().getNum();
            case 1: return inv.getItem();
            case 2: return inv.getPrice();
            case 3: return inv.getCount();
            case 4: return inv.getTotal_line();
            default:return "";
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}
