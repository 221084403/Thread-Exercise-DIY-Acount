/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.entity;

/**
 *
 * @author MNCEDISI
 */
public class Account 
{
    private Long accountNum;
    private Double balance;

    public Account(Long accountNum ,Double balance )
    {
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public Long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Long accountNum) {
        this.accountNum = accountNum;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
