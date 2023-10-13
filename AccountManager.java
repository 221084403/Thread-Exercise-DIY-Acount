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
public class AccountManager implements AccountInterface
{
    private Connection connection;

    public AccountManager(Connection connection) 
    {
        this.connection = connection;
    }
    
    @Override
    public synchronized Boolean openAccount(Account account, String insertSQL) throws SQLException 
    {
        PreparedStatement ps = connection.prepareStatement(insertSQL);
        ps.setLong(1, account.getAccountNum());
        ps.setDouble(2, account.getBalance());
        
        Integer update = ps.executeUpdate();
        ps.close();
        
        return update!=0?true:false;
    }

    @Override
    public synchronized Boolean depositAmount(Account account, Double amount , String balanceSQL, String updateSQL) throws SQLException
    {
        Double balance = getBalanceFromDB(account ,balanceSQL );
        
        Double newBalance = balance + amount;
        
        Integer update = updateBalance(account , newBalance , updateSQL);
        
        return update!=0?true:false;
    }

    @Override
    public synchronized Boolean withdrawAmount(Account account,  Double amount ,String balanceSQL, String updateSQL) throws SQLException 
    {
        Double balance = getBalanceFromDB(account ,balanceSQL );
        
        Double newBalance = balance - amount;
        
        Integer update = 0;
        
        if(newBalance>=0)
            update = updateBalance(account , newBalance , updateSQL);
        
        return update!=0?true:false;
    }

    @Override
    public synchronized Double checkBalance(Account account, String balanceSQL) throws SQLException 
    {
        return getBalanceFromDB(account , balanceSQL);
    }

    private Double getBalanceFromDB(Account account, String balanceSQL) throws SQLException 
    {
        PreparedStatement ps = connection.prepareStatement(balanceSQL);
        ps.setLong(1, account.getAccountNum());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())
        {
            Double balance = rs.getDouble("Balance");
            rs.close();
            
            return balance;
        }
        rs.close();
        return null;
    }

    private synchronized Integer updateBalance(Account account, Double newBalance, String updateSQL) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement(updateSQL);
        ps.setDouble(1, newBalance);
        ps.setLong(2, account.getAccountNum());
        
        int update = ps.executeUpdate();
        ps.close();
        
        return update;
    }   
}
