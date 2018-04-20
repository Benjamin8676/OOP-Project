/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisedGamestore;

/**
 *
 * @author Jonathon
 */
public class Cart extends ItemData{
    
    String itemName = "";
    
    Cart(String itemname, double price)
    {
        this.itemName = itemname;
        this.price = price;
    }
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
     public String getItemName()
    {
        return itemName;
    }
    
    public String toString()
    {
        return ("\nItem: " + itemName + 
                "\nPrice: " + price);
    }
     
}
