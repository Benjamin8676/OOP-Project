/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestore;
import java.util.ArrayList;
/**
 *
 * @author ben
 */
class Members
{
    private String username;
    private String password;
    private int accountID;
    
    public Members(){}
    
    public Members(String username, String password, int accountID)
    {
        this.username = username;
        this.password = password;
        this.accountID = accountID;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }
    
    public int getAccountID()
    {
        return accountID;
    }
    
    public void printInfo()
    {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Account ID: " + accountID);
    }
    
    @Override
    public String toString()
    {
        return ("\nUsername: " + username +
                "\nPassword: " + password +
                "\nAccount ID: " + accountID);
    }
}
