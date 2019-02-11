/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 8470p
 */
public class GeneralDAO {
    
    // here i implement the function for update the pin code....
      public void miniStatemnet(String cardNum){
       
        String trans1=" ";
        String trans2= " ";
        String trans3= " ";
        String trans_1=" ";
        String trans_2=" ";
        String trans_3=" ";
        int with1,with2,with3;
        int depo1,depo2,depo3;
        String date_1=" ";
        String date_2=" ";
        String date_3=" ";
        String dates_1=" ",dates_2=" ", dates_3=" ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select  with_time,with_amount from withdraw where card_num='"+cardNum+"'";
            ResultSet a=st.executeQuery(sql);
            a.afterLast();
           if(a.previous())
           {
               trans1= a.getString("with_amount");
               date_1= a.getDate("with_time").toString();
               
           }
            if(a.previous())
           {
               trans2= a.getString("with_amount");
                date_2= a.getDate("with_time").toString();
           }
             if(a.previous())
           {
               trans3= a.getString("with_amount");
               date_3=a.getDate("with_time").toString();
           }
             String sql1="select  depo_time,depo_amount from deposit where card_num='"+cardNum+"'";
             ResultSet b = st.executeQuery(sql1);
             b.afterLast();
             if(b.previous())
             {
                 trans_1= b.getString("depo_amount"); 
                 dates_1=b.getDate("depo_time").toString();
             }
             if(b.previous())
             {
                 trans_2= b.getString("depo_amount");  
                  dates_2=b.getDate("depo_time").toString();
             }
             if(b.previous())
             {
                 trans_3= b.getString("depo_amount");  
                  dates_3=b.getDate("depo_time").toString();
             }
            st.close();
            con.close();
            
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            DateFormat dateFormat = new SimpleDateFormat();
            Date date = new Date();
            String dat = dateFormat.format(date);
                
            with1= Integer.parseInt(trans1);
            with2=Integer.parseInt(trans2);
            with3=Integer.parseInt(trans3);
            depo1= Integer.parseInt(trans_1);
            depo2= Integer.parseInt(trans_2);
            depo3= Integer.parseInt(trans_3);
           
            JOptionPane.showMessageDialog(null,"     " + dat + "\n--------------Mini Statement------------" + "\n Card No :" + cardNum +  "\n " + date_3+" ATM Cash Withddrawl: " + with3+ " Rs" + "\n" +date_2 + " ATM Cash Withddrawl: " + with2 +" Rs" + "\n" + dates_3+ " Cash Deposit : " +depo3 + " Rs" +" \n" + date_1+" ATM Cash Withddrawl: " + with1 + " Rs"+ "\n" + dates_2 +" Cash Deposit :" + depo2+" Rs"+"\n" + dates_1 + " Cash Deposit : " + depo1 + " Rs","Automated Teller Machine",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
    
      
     public int lastDeposit(String cardNum){
        String accountBalance="";
        int amount=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select  depo_amount from deposit where card_num='"+cardNum+"'";
            ResultSet a=st.executeQuery(sql);
            a.afterLast();
            
            if (a.previous()) {
               accountBalance= a.getString("depo_amount");
            }
            amount = Integer.parseInt(accountBalance);
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return amount;
    }
    public int lastWithdraw(String cardNum){
        String accountBalance="";
        int amount=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select  with_amount from withdraw where card_num='"+cardNum+"'";
            ResultSet a=st.executeQuery(sql);
            a.afterLast();
            
            if (a.previous()) {
               accountBalance= a.getString("with_amount");
            }
            amount = Integer.parseInt(accountBalance);
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        System.out.println(amount);
        return amount;
    }
    
    public boolean billPayed2(String cardNum, String billID, String billAmount ){
        boolean valid=false;
        String amount="",account_num="13678654";
        String compName="Electricity";
        int money,accountMoney,remain_amount;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select total_amount from balance_detail where card_num='"+cardNum+"'  ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                amount= a.getString("total_amount");
            }
            accountMoney = Integer.parseInt(amount);
            money= Integer.parseInt(billAmount);
            if(accountMoney < money)
            {
                valid=false;
            }
            else
            {
                remain_amount = accountMoney - money;
                String sql1="update balance_detail set total_amount ='"+remain_amount+"'";
                int c= st.executeUpdate(sql1);
                if(c!=0)
                {
                    valid=true;
                }
                String sql2="insert into bill_details (bill_amount,card_num,company_name,company_account,time,bill_number) values('"+billAmount+"','"+cardNum+"','"+compName+"','"+account_num+"',now(),'"+billID+"')";
                int d= st.executeUpdate(sql2);
                if(d!=0)
                {
                    valid=true;
                }
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
     
     public boolean billPayed3(String cardNum, String billID, String billAmount ){
        boolean valid=false;
        String amount="",account_num="33476868";
        String compName="Ptcl";
        int money,accountMoney,remain_amount;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select total_amount from balance_detail where card_num='"+cardNum+"'  ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                amount= a.getString("total_amount");
            }
            accountMoney = Integer.parseInt(amount);
            money= Integer.parseInt(billAmount);
            if(accountMoney < money)
            {
                valid=false;
            }
            else
            {
                remain_amount = accountMoney - money;
                String sql1="update balance_detail set total_amount ='"+remain_amount+"'";
                int c= st.executeUpdate(sql1);
                if(c!=0)
                {
                    valid=true;
                }
                String sql2="insert into bill_details (bill_ID,bill_amount,card_num,company_name,company_account,time,bill_number) values('"+billID+"','"+billAmount+"','"+cardNum+"','"+compName+"','"+account_num+"',now(),'"+billID+"')";
                int d= st.executeUpdate(sql2);
                if(d!=0)
                {
                    valid=true;
                }
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
     
    
     public boolean billPayed1(String cardNum, String billID, String billAmount ){
        boolean valid=false;
        String amount="",account_num="23678964";
        String compName="Sui Gas";
        int money,accountMoney,remain_amount;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select total_amount from balance_detail where card_num='"+cardNum+"'  ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                amount= a.getString("total_amount");
            }
            accountMoney = Integer.parseInt(amount);
            money= Integer.parseInt(billAmount);
            if(accountMoney < money)
            {
                valid=false;
            }
            else
            {
                remain_amount = accountMoney - money;
                String sql1="update balance_detail set total_amount ='"+remain_amount+"'";
                int c= st.executeUpdate(sql1);
                if(c!=0)
                {
                    valid=true;
                }
                String sql2="insert into bill_details (bill_amount,card_num,company_name,company_account,time,bill_number) values('"+billAmount+"','"+cardNum+"','"+compName+"','"+account_num+"',now(),'"+billID+"')";
                int d= st.executeUpdate(sql2);
                if(d!=0)
                {
                    valid=true;
                }
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
     
      public boolean transferFund(String cardNum1,String cardNum2 , String amount){
        boolean valid=false;
        String pin;
        String money="",money1="";
        int transferAmount , balanceAmount,remainAmount,depositAmount,balanceAmount1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select total_amount from balance_detail where card_num='"+cardNum1+"' ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
               money= a.getString("total_amount");
            }
            transferAmount= Integer.parseInt(amount);
            balanceAmount= Integer.parseInt(money);
            
            String sql1="select total_amount from balance_detail where card_num='"+cardNum2+"' ";
            ResultSet b=st.executeQuery(sql1);
            if (b.next()) {
               money1= b.getString("total_amount");
            }
             balanceAmount1= Integer.parseInt(money1);
            if(balanceAmount >= transferAmount)
            {
                remainAmount=balanceAmount-transferAmount;
                depositAmount= balanceAmount1 + transferAmount;
                String sql3= "update balance_detail set total_amount='"+remainAmount+"' where card_num='"+cardNum1+"'";
                int c= st.executeUpdate(sql3);
                if(c!=0)
                {
                    String sql4= "update balance_detail set total_amount='"+depositAmount+"' where card_num='"+cardNum2+"'";
                    int d= st.executeUpdate(sql4);
                    if(d!=0)
                    {
                        String sql5= "insert into fund_transfer_detail (sender_card_num,receiver_card_num,total_amount_transfer,transfer_time) values ('"+cardNum1+"','"+cardNum2+"','"+amount+"',now())";
                        int e= st.executeUpdate(sql5);
                        if(e!=0)
                        {
                            valid=true;
                        }
                        else
                        {
                            valid=false;
                        }
                    }
                }
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    
    
     public boolean verifyCard(String cardNum){
        boolean valid=false;
        String pin;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select * from registeration where card_num='"+cardNum+"' ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
               valid=true;
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    
    
     public boolean updatePinCode(String cardNum, String pinCode ){
        boolean valid=false;
        String pin="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select pin from registeration where card_num='"+cardNum+"'  ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                pin= a.getString("pin");
            }
            String sql1="update registeration set pin ='"+pinCode+"' where card_num='"+cardNum+"'";
            int b= st.executeUpdate(sql1);
            if(b!=0)
            {
                valid=true;
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    
    public int checkBalance(String cardNum){
        String accountBalance="";
        int amount=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            String sql="select  total_amount from balance_detail where card_num='"+cardNum+"'";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
               accountBalance= a.getString("total_amount");
            }
            amount = Integer.parseInt(accountBalance);
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return amount;
    }
    
    public boolean validateLogin(String cardNum, String pinCode ){
        boolean valid=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
             System.out.println("validated Login");
            
            Statement st=con.createStatement();
            String sql="select * from registeration where card_num='"+cardNum+"' AND pin='"+pinCode+"' ";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                valid=true;
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    
    
     public boolean validateCard(String cardNum){
        boolean valid=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            System.out.println("validated card");
            
            Statement st=con.createStatement();
            
            String sql="select * from registeration where card_num='"+cardNum+"'";
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                valid=true;
            }
//            ResultSet rs=st.executeQuery(sql);
//            while (rs.next()) {                
//                String a=rs.getString("id");
//                String b=rs.getString("fname");
//                String c=rs.getString("lname");
//                String d=rs.getString("password");
//                System.out.println(a+" "+b+" "+c+" "+d);
//            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    public boolean registration(String name,String f_name,String cnic,String email,String dob,String gender,String status,String pin,String city,String state,String address,int cardNum){
        boolean valid=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            System.out.println("Hello");
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            
            String sql="insert into registeration (name,father_name,cnic,email,birth_date,gender,status,pin,city,state,address,card_num) values('"+name+"','"+f_name+"','"+cnic+"','"+email+"','"+dob+"','"+gender+"','"+status+"','"+pin+"','"+city+"','"+state+"','"+address+"','"+cardNum+"')"  ;

            int a=st.executeUpdate(sql);
            if (a!=0) {
                valid=true;
            }
            // here i insert the cardnumber and amount in the balance details....
            String sql1="insert into balance_detail(card_num,total_amount) values('"+cardNum+"','"+0+"')";
            int b= st.executeUpdate(sql1);
            if(b!=0)
            {
                valid= true;
            }
            else
            {
                valid= false;
            }
//            ResultSet rs=st.executeQuery(sql);
//            while (rs.next()) {                
//                String a=rs.getString("id");
//                String b=rs.getString("fname");
//                String c=rs.getString("lname");
//                String d=rs.getString("password");
//                System.out.println(a+" "+b+" "+c+" "+d);
//            }
            st.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    
    
}
