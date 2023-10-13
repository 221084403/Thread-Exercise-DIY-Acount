/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.SQLException;
import za.ac.tut.entity.Account;

/**
 *
 * @author MNCEDISI
 */
public interface AccountInterface 
{
    public Boolean openAccount(Account account , String insertSQL) throws SQLException;
    
    public Boolean depositAmount(Account account ,Double amount , String balanceSQL ,String updateSQL) throws SQLException;
    
    public Boolean withdrawAmount(Account account ,Double amount ,  String balanceSQL ,String updateSQL) throws SQLException;
    
    public Double checkBalance(Account account , String balanceSQL) throws SQLException;
}
