
package SIG.Controller;

import SIG.Model.InvoiceHeader;
import SIG.Model.InvoiceLine;
import SIG.Model.LineTableView;
import SIG.Model.TableView;
import SIG.View.InvDialog;
import SIG.View.LineDialog;
import SIG.View.SalesInvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ActionHandler implements ActionListener, ListSelectionListener {
    
    private InvDialog invDialog;
    private LineDialog lineDialog;
    private SalesInvoiceFrame frame;
    public ActionHandler(SalesInvoiceFrame frame){
    this.frame=frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("Action Handling called!");
        switch (e.getActionCommand()) {
            case "New Invoice":
                System.out.println("New invoice");
                newInv();
                break;
                
            case "Delete Invoice":
                System.out.println("Delete invoice");
                delInv();
                break;
                
            case "Add Item":
                System.out.println("Add Item");
                addItem();
                break;
                
            case "Delete Item":
                System.out.println("Delete Item");
                deleteItem();
                break;
                
              
            case "Load file":
                System.out.println("Load file");
                loadFile();
                break;
                
            case "Save file":
                System.out.println("Save file");
                saveFile();
                break;    
                
            case "InvoiceCreated":
                System.out.println("InvoiceCreated");       
                  InvoiceCreated();     
                 break;       
                   
            case "NoInvoiceCreated":
                System.out.println("NoInvoiceCreated");
                NoInvoiceCreated();
                break;
                
                case "LineCreated":
                System.out.println("LineCreated");       
                  LineCreated();     
                 break;       
                   
            case "NoLineCreated":
                System.out.println("NoLineCreated");
                NoLineCreated();
                break;
                        
                     
            default:
                throw new AssertionError();
        }
     
   
    }

    private void newInv() {
        invDialog = new InvDialog(frame);
        invDialog.setVisible(true);
        
    }
    

    private void delInv() {
        int rowChoosen= frame.getHeaderTable().getSelectedRow();
        if (rowChoosen != -1){
            frame.getInvoicess().remove(rowChoosen);
            frame.getTableView().fireTableDataChanged();
        }
    }
    
    

    private void addItem() {
    }

    private void deleteItem() {
       int invoiceSelected = frame.getHeaderTable().getSelectedRow();
       int rowSelected = frame.getLineTable().getSelectedRow();
  
        if (invoiceSelected != -1 && rowSelected != -1){
            InvoiceHeader invoiceHeader = frame.getInvoicess().get(invoiceSelected);
            invoiceHeader.getLines().remove(rowSelected);
            LineTableView lineTableView = new LineTableView(invoiceHeader.getLines());
            frame.getLineTable().setModel(lineTableView);
            lineTableView.fireTableDataChanged();
        }
            
    }
    
    
    private void saveFile() {
    }
    
    
    
    
 @Override
    public void valueChanged(ListSelectionEvent e) {
        int Index =frame.getHeaderTable().getSelectedRow();
       if (Index!= -1){
        InvoiceHeader selectedInvoice = frame.getInvoicess().get(Index);
      System.out.println("selection happened");
      
      frame.getNumLabel().setText(""+selectedInvoice.getNum());
      frame.getDateLabel().setText(""+selectedInvoice.getDate());
      frame.getCustomerLabel().setText(""+selectedInvoice.getCustomer());
      frame.getTotalLabel().setText(""+selectedInvoice.getTotal_price());
      LineTableView lineTableView= new LineTableView(selectedInvoice.getLines());
      frame.getLineTable().setModel(lineTableView);
      lineTableView.fireTableDataChanged();
    }
    }
    
    
    private void loadFile() {
        JFileChooser fChooser = new JFileChooser();
        try {
        int selection=fChooser.showOpenDialog(null);
        if(selection==JFileChooser.APPROVE_OPTION){
        File headerf=fChooser.getSelectedFile();
        Path headerPath=Paths.get(headerf.getAbsolutePath());
           List<String>headerLines = Files.readAllLines(headerPath);
            System.out.println("file read");
            ArrayList<InvoiceHeader>inv = new ArrayList<>();
            for(String headerline : headerLines){
             String [] splits = headerline.split(",");
             int invNum = Integer.parseInt(splits[0]);
             String invDate = splits[1];
             String name = splits[2];
                InvoiceHeader invoice = new InvoiceHeader(invNum,name , invDate);
            inv.add(invoice);
            }
            
            selection=fChooser.showOpenDialog(null);
            if(selection==JFileChooser.APPROVE_OPTION){
            File line = fChooser.getSelectedFile();
            Path linePath = Paths.get(line.getAbsolutePath());
            List<String>listLines=Files.readAllLines(linePath);
                for (String listLine : listLines) {
                    String [] lineSplit = listLine.split(",");
                    int num=Integer.parseInt(lineSplit[0]); 
                    String product = lineSplit[1];
                    int price = Integer.parseInt(lineSplit[2]);
                    int count=Integer.parseInt(lineSplit[3]);
                   InvoiceHeader Inv = null;
                    for(InvoiceHeader invoice :inv){
                    if(invoice.getNum()==num){
                    Inv =invoice;
                    break;
                    }
                       }
                    InvoiceLine linesss = new InvoiceLine(num, product, price, count, Inv);
                Inv.getLines().add(linesss);
                }
                
                                  }
        
frame.setInvoicess(inv);
TableView tableView = new TableView(inv);
frame.setTableView(tableView);
frame.getHeaderTable().setModel(tableView);
frame.getTableView().fireTableDataChanged();
        }
                    
                }catch (IOException ex){
                    ex.printStackTrace();
        }
        }

    private void InvoiceCreated() {
       String date = invDialog.getDateField().getText();
       String name = invDialog.getCustomerField().getText();
       int invNumber = frame.getMaxNumber();
       
       InvoiceHeader invoiceHeader = new InvoiceHeader(invNumber, name, date);
       frame.getInvoicess().add(invoiceHeader);
       frame.getTableView().fireTableDataChanged();
       invDialog.setVisible(false);
       invDialog.dispose();
       invDialog = null;
    }

    private void NoInvoiceCreated() {
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog=null;
    }

    private void LineCreated() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void NoLineCreated() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
   
    
    
        
      
  
    
    
     
}
