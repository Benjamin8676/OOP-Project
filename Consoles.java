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
    
    public Consoles(){}
    
    public Consoles(String model, String condition, double price)
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
    

    public String getModel()
    {
        return model;
    }
    
    public String getCondition()
    {
        return condition;
    }
    

    public void printInfo()
    {
        System.out.println("Model: " + model);
        System.out.println("Condition: " + condition);
        System.out.println("Price: " + price);
    }
    
    @Override
    public String toString()
    {
        return ("\nModel: " + model + 
                "\nCondition: " + condition + 
                "\nPrice: " + price);
    }
    
}