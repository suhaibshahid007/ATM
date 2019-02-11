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


/**
 *
 * @author Personal Thoughts
 */
public class DepositDAO {
    
    
    public boolean validateWithdraw(String cardNum,String amount){
        boolean valid=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st  =con.createStatement();
            
            String sql="select total_amount from balance_detail where card_num= '"+cardNum+"'";
            ResultSet a=st.executeQuery(sql);
            String accountBalance="";
            int balance,total_Amount1;
            int remain_Value;
            if (a.next()) {
                accountBalance= a.getString("total_amount");
            }
            // convert the balance in integer....
            balance= Integer.parseInt(accountBalance);
            total_Amount1 = Integer.parseInt(amount);
            // now add and deposit the money in the account....
            // here i check the required withdrawl amount present in user account....
            if(balance>=total_Amount1)
            {
                remain_Value =balance - total_Amount1;
           String sql1=" update balance_detail set total_Amount= '"+remain_Value+"' where card_num = '"+cardNum+"'";
           int b = st.executeUpdate(sql1);
           if(b!=0)
           {
               String sql4="insert into withdraw (card_num,with_amount,with_time) values('"+cardNum+"','"+total_Amount1+"',now())";
               int f= st.executeUpdate(sql4);
               if(f!=0)
               {
                   valid=true;
               }
           }
            }
            else
            {
                valid =false;
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
        } 
        catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally{
            
        }
        
        return valid;
    }
    
    public boolean validateDeposit(String cardNum,String amount)
    {
        boolean valid=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/atm?autoReconnect=true&useSSL=false";
            Connection con=DriverManager.getConnection(url,"root","root");
            
            Statement st=con.createStatement();
            
            String sql="select total_amount from balance_detail where card_num= '"+cardNum+"'";
            String accountBalance="";
            int balance,total_Amount1;
            int deposited_Value;
            ResultSet a=st.executeQuery(sql);
            if (a.next()) {
                accountBalance= a.getString("total_amount");
            }
            // convert the balance in integer....
            balance= Integer.parseInt(accountBalance);
            total_Amount1 = Integer.parseInt(amount);
            // now add and deposit the money in the account....
            deposited_Value = balance + total_Amount1;
           String sql1=" update balance_detail set total_Amount= '"+deposited_Value+"' where card_num = '"+cardNum+"'";
           int b = st.executeUpdate(sql1);
           if(b!=0)
           {

               String sql3="insert into deposit (card_num,depo_amount,depo_time) values('"+cardNum+"','"+total_Amount1+"',now()) ";
               int d= st.executeUpdate(sql3);
               if(d!=0)
               {
                   valid=true;
               }
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
