/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestore;

/**
 *
 * @author ben
 */
class Staff extends UserData
{
    private String name = "";
    private String password = "";
    private String staffID = "";
    
    public Staff(){}
    
    public Staff(String name, String password, String staffID)
    {
        
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName(String name)
    {
        return name;
    }
    
    public void setStaffID(String staffID)
    {
        this.staffID = staffID;
    }
    
    public String getStaffID(String staffID)
    {
        return staffID;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword(String password)
    {
        return password;
    }
    
    @Override
    public String toString()
    {
        return ("\nStaff Name: " + name +
                "\nStaff ID: " + staffID +
                "\nPassword: " + password);
    }
}
