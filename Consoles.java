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
class Consoles extends ItemData
{
    private String model = "";
    private String condition = "";
    private int price = 0;
    
    public Consoles(){}
    
    public Consoles(String model, String condition, int price)
    {
        this.model = model;
        this.condition = condition;
        this.price = price;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public void setCondition(String condition)
    {
        this.condition =  condition;
    }
    
    public void setPrice(int price)
    {
        this.price = price;
    }
    
    public String getModel(String model)
    {
        return model;
    }
    
    public String getCondition(String condition)
    {
        return condition;
    }
    
    public int getPrice(int price)
    {
        return price;
    }
    
    @Override
    public String toString()
    {
        return ("\nModel: " + model + 
                "\nCondition: " + condition + 
                "\nPrice: " + price);
    }
    
}