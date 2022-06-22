
package SIG.View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class InvDialog extends JDialog {
    
    private JTextField customerField;
    private JTextField dateField;
    private JLabel customerLab1;
    private JLabel dateLab1 ;
    private JButton okBtn ;
    private JButton cancelBtn ;        
            
    public InvDialog(SalesInvoiceFrame fr){
        customerLab1 = new JLabel ("customer: ");
        customerField = new JTextField(15);
        dateLab1 = new JLabel ("Date");
        dateField = new JTextField(40);
        okBtn = new JButton ("ok");
        cancelBtn = new JButton("cancel");
        
        okBtn.setActionCommand("InvoiceCreated");
        cancelBtn.setActionCommand("NoInvoiceCreated");
        okBtn.addActionListener(fr.getHandler());
        cancelBtn.addActionListener(fr.getHandler());
        setLayout(new GridLayout(3,2));
        
    add(customerField);
    add(dateField) ;
    add(customerLab1);
    add(dateLab1) ;
    add(okBtn) ;
    add(cancelBtn);
            pack();
    }

    public JTextField getCustomerField() {
        return customerField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JLabel getCustomerLab1() {
        return customerLab1;
    }

    public JLabel getDateLab1() {
        return dateLab1;
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
