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
class Games extends ItemData
{
    private String title = "";
    private String genre = "";
    private String system = "";
    private String condition = "";
    private int price = 0;
    
    
    public Games(){}
    
    public Games(String title, String genre, String system, String condition, int price)
    {
        setTitle(title);
        this.genre = genre;
        this.system = system;
        this.condition = condition;
        this.price = price;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    
    public void setSystem(String system)
    {
        this.system = system;
    }
    
    public void setCondition(String condition)
    {
        this.condition = condition;
    }
    
    public void setPrice(int price)
    {
        this.price = price;
    }
    
    public String getTitle(String title)
    {
        return title;
    }
    
    public String getGenre(String genre)
    {
        return genre;
    }
    
    public String getSystem(String system)
    {
        return system;
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
        return ("\nTitle: " + title +
                "\nGenre: " + genre + 
                "\nSystem: " + system +
                "\nCondition: " + condition +
                "\nPrice: " + price);
    }
    
}
