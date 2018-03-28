package gamestore;
import java.util.ArrayList;
import java.util.*;


public class Store {

    public static void main(String[] args) {

        String commandMenu = "";
        
        ArrayList<Member> dataMember = new ArrayList<>();
        String defaultNames[] = new String[]{"Ben", "Rebecca", "Alex", "Jon", "Omar"};
        String defaultPasswords[] = new String[]{"pass01", "pass02", "pass03", "pass04", "pass05", };
        Member defaultMembers[] = new Member[5];
        Scanner input = new Scanner(System.in);
        
        ArrayList<Staff> dataStaff = new ArrayList<>();
        
        
        for(int i = 1; i < defaultNames.length; i++)
        {
            defaultMembers[i] = new Member(defaultNames[i], defaultPasswords[i]);
            dataMember.add(defaultMembers[i]);
        }
        
        while (!commandMenu.equalsIgnoreCase("0"))  
        {
            System.out.println("\nEnter command");
            System.out.println("1: Current users.");
            System.out.println("2: Staff");
            System.out.println("0: Quit");
            commandMenu = input.next();
            System.out.println();
            
            
            //DISPLY FOR CURRENT USERS
            if(commandMenu.equalsIgnoreCase("1"))
            {
                {
                    System.out.println("===Current Users===");
                    for(int x = 0; x < defaultNames.length; x ++)  
                    {  
                        System.out.println(defaultNames[x] + " " +  defaultPasswords[x]);
                    }
                }
            }
            //END OF DISPLAY FOR CURRENT USERS
            
            
        }
    }
    
    	public static String UserCheck(String Email) 
        {

		String result = "Valid E-mail";			
		int atLoc =0;						
		int perLoc =0;						
		int atCount =0;						
		int perCount =0;					

		for (int x =0; x < Email.length(); x++) {
			if ((Email.charAt(x) >= 48 && Email.charAt(x) <= 57) || (Email.charAt(x) >= 65 && Email.charAt(x) <= 90) ||
				(Email.charAt(x) >= 97 && Email.charAt(x) <= 122) || (Email.charAt(x) == 64) || (Email.charAt(x) == 46)
				|| (Email.charAt(x) == 95)) {
					
				} else {
					result = "E-mail Contains Invalid Character!";		
				}

			if (Email.charAt(x) == 64) {							
				atLoc = x;
				atCount++;
			}

			if (Email.charAt(x) == 46) {							
				perLoc = x;
				perCount++;
			}
		} 

		if (atLoc < 1 || perLoc < 1)								
		{
			result = "Not a valid email Address";
		}

		if (perLoc < atLoc) {
			result = "Not a valid email address";						
		}

		if ((atCount > 1) || (perCount > 1)) {							
			result = "Not a valid email address";
		}

		return (result);						

	} 
	public static String PassCheck (String Password) 
        {

		String result = "Valid Password";			
		int length = 0;						
					


		for (int x =0; x < Password.length(); x++) {
			if ((Password.charAt(x) >= 47 && Password.charAt(x) <= 58) || (Password.charAt(x) >= 64 && Password.charAt(x) <= 91) ||
				(Password.charAt(x) >= 97 && Password.charAt(x) <= 122)) {
					
				} else {
					result = "Password Contains Invalid Character!";		
				}


			length = (x + 1);								

		} 


		if (length < 8){									
			result = "Password is Too Short!";
		}

			return (result);								

	} 
    
    
}

// <editor-fold defaultstate="collapsed" desc="UserData Class">
class UserData
{
    
}
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="ItemData Class">
class ItemData
{
    
}
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Member Class">
class Member extends UserData
{
    private String username = "default";
    private String password = "default";
    
    public Member(){}
    
    public Member(String username, String password)
    {
        getUsername();
        getPassword();
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
    
    @Override
    public String toString()
    {
        return ("\nUsername: " + username +
                "\nPassword: " + password);
    }
    
    

}
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Staff Class">
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
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Game Class">
class Game extends ItemData
{
    private String title = "";
    private String genre = "";
    private String system = "";
    private String condition = "";
    private int price = 0;
    
    
    public Game(){}
    
    public Game(String title, String genre, String system, String condition, int price)
    {
        setTitle(title);//////////do this
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
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Console Class">
class Console extends ItemData
{
    private String model = "";
    private String condition = "";
    private int price = 0;
    
    public Console(){}
    
    public Console(String model, String condition, int price)
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
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Misc Class">
class Misc extends ItemData
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
// </editor-fold>