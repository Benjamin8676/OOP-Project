/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisedGamestore;

/**
 *
 * @author ben
 */
public class Misc extends ItemData
{
    private String itemName = "";
    private String itemDescription = "";
    private String itemType = "";
    
    
    public Misc(){}
    
    public Misc(String itemName, String itemDescription, String itemType, double price)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.price = price;
    }
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }
    
    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    public String getItemType()
    {
        return itemType;
    }
    
    public void printInfo()
    {
        System.out.println("Item: " + itemName);
        System.out.println("Info: " + itemDescription);
        System.out.println("Type: " + itemType);
        System.out.println("Price: " + price);
    }
    
    @Override
    public String toString()
    {
        return ("\nItem: " + itemName + 
                "\nDescription: " + itemDescription + 
                "\nType: " + itemType +
                "\nPrice: " + price);
    }
}
