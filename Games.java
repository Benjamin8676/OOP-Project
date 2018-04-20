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
class Games extends ItemData
{
    private String title = "";
    private String genre = "";
    private String system = "";
    private String condition = "";

    
    
    public Games(){}
    
    public Games(String title, String genre, String system, String condition, double price)
    {
        this.title = title;
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
    
    public String getTitle()
    {
        return title;
    }
    
    public String getGenre()
    {
        return genre;
    }
    
    public String getSystem()
    {
        return system;
    }
    
    public String getCondition()
    {
        return condition;
    }
    
    public void printInfo()
    {
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("System: " + system);
        System.out.println("Condition: " + condition);
        System.out.println("Price: " + price);
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
