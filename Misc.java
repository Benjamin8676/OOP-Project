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
public class Misc extends ItemData
{
    private String itemName = "";
    private String itemDescription = "";
    private String itemType = "";
    
    public Misc(){}
    
    public Misc(String itemName, String itemDescription, String itemType)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
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
    
    public String getItemName(String itemName)
    {
        return itemName;
    }
    
    public String getItemDescription(String itemDescription)
    {
        return itemDescription;
    }
    
    public String getItemType(String itemType)
    {
        return itemType;
    }
    
    @Override
    public String toString()
    {
        return ("\nItem: " + itemName + 
                "\nDescription: " + itemDescription + 
                "\nType: " + itemType);
    }
}
