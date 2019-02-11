/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 8470p
 */
import DAO.*;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
public class pinChange extends javax.swing.JFrame {

    /**
     * Creates new form pinChange
     */
    String cardNum;
    public pinChange(String no) {
        cardNum=no;
        icons();
        initComponents();
    }
public void icons()
     {
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("if_atm-machine_531881.png")));
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        currentPin = new javax.swing.JPasswordField();
        newPin = new javax.swing.JPasswordField();
        renewPin = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CHANGE YOUR PIN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 260, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Current PIN  :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 100, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText(" New PIN  :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 80, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Re-Enter New Pin  :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 150, 20));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("SAVE ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 110, 30));
        jPanel1.add(currentPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 160, 30));
        jPanel1.add(newPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 160, 30));
        jPanel1.add(renewPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 160, 30));

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Delete-icon.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 44, 30, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/light-blue-wallpaper-7837-8130-hd-wallpapers-1024x640.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 350));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // here the code to change the pin of the customer.....
        String oldPin;
        if(currentPin.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter the Current  pin code","Automated Teller Machine",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        oldPin= currentPin.getText();
        GeneralDAO g= new GeneralDAO();
       boolean a= g.validateLogin(cardNum, oldPin);
       if(a==false)
       {
           JOptionPane.showMessageDialog(null,"Wrong Current Pin Code","Automated Teller Machine",JOptionPane.ERROR_MESSAGE);
           currentPin.setText("");
           return;
       }
       String newPinCode,renewPinCode;
       if(newPin.getText().isEmpty())
       {
            JOptionPane.showMessageDialog(null,"Enter New Pin Code","Automated Teller Machine",JOptionPane.ERROR_MESSAGE);
            return;
       }
        if(renewPin.getText().isEmpty())
       {
            JOptionPane.showMessageDialog(null,"Re-Enter New Pin Code","Automated Teller Machine",JOptionPane.ERROR_MESSAGE);
            return;
       }
       
       newPinCode=newPin.getText();
       renewPinCode= renewPin.getText();
       if(!(newPinCode.equals(renewPinCode)))
       {
            JOptionPane.showMessageDialog(null,"Re-Enter New Pin Code","Automated Teller Machine",JOptionPane.ERROR_MESSAGE);
            renewPin.setText("");
       }
        else
       {
           boolean value=g.updatePinCode(cardNum,newPinCode);
           if(value)
           {
               JOptionPane.showMessageDialog(null,"Pin Code Successfully Changed ","Automated Teller Machine",JOptionPane.INFORMATION_MESSAGE);
           }
           currentPin.setText("");
           newPin.setText("");
           renewPin.setText("");
           otherTransaction trans = new otherTransaction(cardNum);
           trans.setVisible(true);
           trans.setLocationRelativeTo(null);
           this.dispose();
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        signin in = new signin();
        in.setVisible(true);
        in.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pinChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pinChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pinChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pinChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pinChange("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField currentPin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPin;
    private javax.swing.JPasswordField renewPin;
    // End of variables declaration//GEN-END:variables
}
