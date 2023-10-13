/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.*;
import za.ac.tut.entity.Account;

/**
 *
 * @author MNCEDISI
 */
public class AccountThread extends Thread
{
    private Connection connection;
    private Account account;  
    private Double amount;  
    private Integer option;
    private String insertSQL;
    private String balanceSQL;
    private String updateSQL;
    
    private AccountManager am;

    public AccountThread(Connection connection,Account account ,Double amount ,  Integer option,String insertSQL , String balanceSQL, String updateSQL)
    {
        this.connection = connection;
        this.account = account;
        this.amount = amount;
        this.option = option;
        this.insertSQL = insertSQL;
        this.balanceSQL = balanceSQL;
        this.updateSQL = updateSQL;
        this.am = new AccountManager(connection);
    }

    @Override
    public void run() 
    {
        Boolean dbUpdate = null;
       
        try
        {
            switch(option)
            {
                case 1:
                    dbUpdate = am.openAccount(account, insertSQL);
                    
                    if(dbUpdate)
                        System.out.println("The account is open successfully\n");
                    else
                        System.err.println("The account is not open\n");
                break;
                
                case 2:
                    dbUpdate = am.depositAmount(account, amount, balanceSQL, updateSQL);
                    
                    if(dbUpdate)
                        System.out.println(Thread.currentThread().getName()+ " R"+account.getBalance()+" is deposited successfully\n");
                    else
                        System.err.println("The amount is not deposited\n");
                break;
                
                case 3:
                    dbUpdate = am.withdrawAmount(account, amount, balanceSQL, updateSQL);
                  
                    if(dbUpdate)
                        System.out.println(Thread.currentThread().getName()+ " R"+account.getBalance()+" is withdrawn successfully\n");
                    else
                        System.err.println("The amount is not withdrawn\n");
                break;
                    
                case 4:
                       System.out.println(Thread.currentThread().getName()+ " available balance R"+am.checkBalance(account, balanceSQL)); 
                break;
                    
                default:
                    System.err.println("Invalid option. Please re-enter option again");
                break;
            }
        }
        catch (SQLException ex) 
        {
            System.err.println("Something went wrong\n"+ex.getMessage());
        }
    }
    
}
