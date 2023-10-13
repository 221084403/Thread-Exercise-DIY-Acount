/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountapp;

import java.sql.*;
import za.ac.tut.bl.*;
import za.ac.tut.entity.*;

/**
 *
 * @author MNCEDISI
 */
public class AccountApp 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException
    {
        // TODO code application logic here
        
        //database connection
        String dbURL = "jdbc:derby://localhost:1527/AccountDatabase";
        String userName = "AccountDB";
        String password = "123";
        
        //insert into database
        String insertSQL ="INSERT INTO ACCOUNT_TBL VALUES(? , ?) ";
        
        //find available balance from database
        String balanceSQL = "SELECT * FROM ACCOUNT_TBL "+
                            "WHERE AccountNo = ? ";
        
        //update the balance into database
        String updateSQL = "UPDATE ACCOUNT_TBL "+
                           "SET Balance = ? "+
                           "WHERE AccountNo = ? ";
        
        Connection connection = DriverManager.getConnection(dbURL, userName, password);
        
        Account account1 = new Account((long)111, 100.0);
        Account account2 = new Account((long)222, 500.0);
        Account account3 = new Account((long)333, 300.0);
        Account account4 = new Account((long)333, 300.0);
        
        Thread thread1 = new AccountThread(connection, account1, 0.0, 2, insertSQL, balanceSQL, updateSQL);
        Thread thread2 = new AccountThread(connection, account2, 900.0, 2, insertSQL, balanceSQL, updateSQL);
        Thread thread3 = new AccountThread(connection, account3, 100.0, 3, insertSQL, balanceSQL, updateSQL);
        Thread thread4 = new AccountThread(connection, account4, 100.0, 4, insertSQL, balanceSQL, updateSQL);
    
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    
}
