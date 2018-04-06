package gamestore;
import java.util.ArrayList;
import java.util.Scanner;


public class Store {

    public static void main(String[] args) {
        int t = 1;
        int input, ID;
        double price, total;
        String name, password, title, genre, sys, condition, model;
        String itemName, itemDescription, itemType;
        String command = "", response = "";
        Scanner scan = new Scanner(System.in);
        
        // <editor-fold defaultstate="collapsed" desc="Array Lists"> 
        ArrayList<Members> dataMembers = new ArrayList<>();
        dataMembers.add(new Members("Member01", "pass001", 343343));
        dataMembers.add(new Members("Member02", "pass002", 121121));
        dataMembers.add(new Members("Member03", "pass003", 454454));
        dataMembers.add(new Members("Member04", "pass004", 565565));
        dataMembers.add(new Members("Member05", "pass005", 676676));
        
        ArrayList<Staff> dataStaff = new ArrayList<>();
        dataStaff.add(new Staff("Staff01", "pass999", 999999));
        dataStaff.add(new Staff("Staff02", "pass888", 888888));
        dataStaff.add(new Staff("Staff03", "pass777", 777777));
        dataStaff.add(new Staff("Staff04", "pass666", 666666));
        dataStaff.add(new Staff("Staff05", "pass555", 555555));
         
        ArrayList<Games> dataGames = new ArrayList<>();
        dataGames.add(new Games("Halo", "Sci-fi", "Xbox", "New", 60.00));
        dataGames.add(new Games("Halo Reach", "Sci-fi", "Xbox", "New", 60.00));
        dataGames.add(new Games("Halo 2", "Sci-fi", "Xbox", "New", 60.00));
        dataGames.add(new Games("Halo 3", "Sci-fi", "Xbox", "New", 60.00));
        dataGames.add(new Games("Halo Wars", "Sci-fi", "Xbox", "New", 60.00));
        
        ArrayList<Consoles> dataConsoles = new ArrayList<>();
        dataConsoles.add(new Consoles("PS4", "New", 400.00));
        dataConsoles.add(new Consoles("Xbox One", "New", 450.00));
        dataConsoles.add(new Consoles("Nintendo Switch", "New", 500.00));
         
        ArrayList<Misc> dataMisc = new ArrayList<>(); 
        dataMisc.add(new Misc("DVD01", "This is a movie.", "Video media", 8.00));
        dataMisc.add(new Misc("CD01", "This is a music cd.", "Music media", 12.00));
        dataMisc.add(new Misc("DVD02", "This is a movie.", "Video media", 6.00));
        dataMisc.add(new Misc("CD02", "This is a music cd.", "Music media", 15.00));
        
        ArrayList<Cart> dataCart = new ArrayList<>();
        // </editor-fold>
        
        
// for each 'if' and 'while' loop, command is set back to "" in  
// order for the user to traverse the menu.
        
        while(!command.equalsIgnoreCase("0")){
            
            System.out.println("1: Login as member");
            System.out.println("2: Login as staff");
            System.out.println("0: Quit");        
            command = scan.next();
            System.out.println();
       
        
            //Start for login as member
            if(command.equalsIgnoreCase("1"))
            {
                command = "";
                while(!command.equalsIgnoreCase("0"))
                {
                    System.out.println("1: View Games");
                    System.out.println("2: View Consoles");
                    System.out.println("3: View Misc Items");
                    System.out.println("4: View Cart");
                    System.out.println("0: Back");
                    command = scan.next();
                    System.out.println();
                    
                    
                    if(command.equalsIgnoreCase("1"))
                    {
                        for(Games obj: dataGames)
                        {
                            System.out.println(obj);
                        }
                        System.out.println();
                        System.out.println("Would you like to add an item to your cart?(y/n)");
                        response = scan.next();
                        
                        if(response.equalsIgnoreCase("y"))
                        {
                            System.out.println("Please enter the index of the item you wish to add to cart");
                            input = scan.nextInt();
                            title = dataGames.get(input).getTitle();
                            price = dataGames.get(input).getPrice();
                            dataCart.add(new Cart(title,price));
                            System.out.println("Item added to cart");
                            System.out.println();
                            
                        }
                        
                        System.out.println();
                    }
                    
                    if(command.equalsIgnoreCase("2"))
                    {
                        for(Consoles obj: dataConsoles)
                        {
                            System.out.println(obj);
                        }
                        System.out.println();
                        System.out.println("Would you like to add an item to your cart?(y/n)");
                        response = scan.next();
                        
                        if(response.equalsIgnoreCase("y"))
                        {
                            System.out.println("Please enter the index of the item you wish to add to cart");
                            input = scan.nextInt();
                            model = dataConsoles.get(input).getModel();
                            price = dataConsoles.get(input).getPrice();
                            dataCart.add(new Cart(model,price));
                            System.out.println("Item added to cart");
                            System.out.println();
                            
                        }
                        System.out.println();
                    }
                    
                    if(command.equalsIgnoreCase("3"))
                    {
                        for(Misc obj: dataMisc)
                        {
                            System.out.println(obj);
                        }
                        
                        System.out.println();
                        System.out.println("Would you like to add an item to your cart?(y/n)");
                        response = scan.next();
                        
                        if(response.equalsIgnoreCase("y"))
                        {
                            System.out.println("Please enter the index of the item you wish to add to cart");
                            input = scan.nextInt();
                            itemName = dataMisc.get(input).getItemName();
                            price = dataMisc.get(input).getPrice();
                            dataCart.add(new Cart(itemName,price));
                            System.out.println("Item added to cart");
                            System.out.println();
                            
                        }
                        
                        System.out.println();
                    }
                    
                    if(command.equalsIgnoreCase("4"))
                    {
                        if(dataCart.isEmpty())
                        {
                            System.out.println("Cart is empty");
                            System.out.println();
                        }
                        else
                        {
                            total = 0;
                            for(Cart obj: dataCart)
                                {
                                    System.out.println(obj);
                                }
                            for(Cart obj: dataCart)
                                {
                                    total = total + obj.price;
                                }
                            System.out.println();
                            System.out.println("The total cost of the items is " + total);
                            System.out.println();

                            while(!command.equalsIgnoreCase("0"))
                            {
                                System.out.println("1: Purchase Items");
                                System.out.println("2: Remove Item");
                                System.out.println("0: back");
                                command = scan.next();
                                System.out.println();


                                if(command.equalsIgnoreCase("1"))
                                {
                                    System.out.println("Thank you for your purchase");
                                    System.out.println();
                                    dataCart.clear();
                                    command = "0";
                                }

                                if(command.equalsIgnoreCase("2"))
                                {
                                    System.out.println("Please enter the index of the item you wish to remove");
                                    input = scan.nextInt();
                                    dataCart.remove(input);
                                    System.out.println("Item removed");
                                    if(dataCart.isEmpty())
                                    {
                                        System.out.println("Cart is empty");
                                        System.out.println();
                                        command = "0";
                                    }
                                    else
                                    {
                                        total = 0;

                                        for(Cart obj: dataCart)
                                        {
                                            System.out.println(obj);
                                        }
                                        for(Cart obj: dataCart)
                                        {
                                            total = total + obj.price;
                                        }
                                        System.out.println();
                                        System.out.println("The total cost of the items is " + total);
                                        System.out.println();
                                    }
                                }
                            }
                        }
                        command = "";
                    }
                }
                command = "";
               

            }

            
            //Start of login as staff
            if(command.equalsIgnoreCase("2"))
            {
                command = "";
                while(!command.equalsIgnoreCase("0"))
                {
                    System.out.println("1: Maintain members");
                    System.out.println("2: Maintain staff");
                    System.out.println("3: Maintain games");
                    System.out.println("4: Maintain consoles");
                    System.out.println("5: Maintain misc. items");
                    System.out.println("0: back");
                    command = scan.next();
                    System.out.println();
                    
//==============================================================================    
//=============================MEMBERS==========================================
//==============================================================================
                    
                    if(command.equalsIgnoreCase("1"))
                    {
                        command = "";
                        while(!command.equalsIgnoreCase("0"))
                        {
                            System.out.println("1: View members");
                            System.out.println("2: Add members");
                            System.out.println("3: Delete members");
                            System.out.println("0: back");
                            command = scan.next();
                            System.out.println();
                            
                            //View
                            if(command.equalsIgnoreCase("1"))
                            {
                                    for(Members obj: dataMembers)
                                    {
                                        System.out.println(obj);
                                    }
                                    System.out.println();
                            }
                            
                            //Add
                            if(command.equalsIgnoreCase("2"))
                            {
                                    System.out.println("How many members would you like to enter: ");
                                    input = scan.nextInt();

                                    for(int i=0;i<input;i++)
                                    {
                                        System.out.println("Enter details of member " + (i+1));
                                        System.out.println("Enter username: ");
                                        name = scan.next();
                                        System.out.println("Enter password: ");
                                        password = scan.next();
                                        System.out.println("Enter ID: ");
                                        ID = scan.nextInt();
                                        dataMembers.add(new Members(name, password, ID));
                                    }
                                    System.out.println();
                            }
                            
                            //Delete
                            if(command.equalsIgnoreCase("3"))
                            {
                                    System.out.println("Enter index of the member for deletion: ");
                                    input = scan.nextInt();
                                    dataMembers.remove(input);
                                    System.out.println();
                            }
                            
                        }
                        command = "";
                    }
                    
//==============================================================================
//===============================STAFF==========================================
//==============================================================================

                    if(command.equalsIgnoreCase("2"))
                    {
                        command = "";
                        while(!command.equalsIgnoreCase("0"))
                        {
                            System.out.println("1: View staff");
                            System.out.println("2: Add staff");
                            System.out.println("3: Delete staff");
                            System.out.println("0: back");
                            command = scan.next();
                            System.out.println();
                            
                            //View
                            if(command.equalsIgnoreCase("1"))
                            {
                                
                                
                                    for(Staff obj: dataStaff)
                                    {
                                        System.out.println(obj);
                                    }
                                    System.out.println();
                            }
                            
                            //Add
                            if(command.equalsIgnoreCase("2"))
                            {
                                    System.out.println("How many staff would you like to enter: ");
                                    input = scan.nextInt();

                                    for(int i=0;i<input;i++)
                                    {
                                        System.out.println("Enter details of staff member " + (i+1));
                                        System.out.println("Enter name: ");
                                        name = scan.next();
                                        System.out.println("Enter password: ");
                                        password = scan.next();
                                        System.out.println("Enter staff ID: ");
                                        ID = scan.nextInt();
                                        dataStaff.add(new Staff(name, password, ID));
                                    }
                                    System.out.println();
                            }
                            
                            //Delete
                            if(command.equalsIgnoreCase("3"))
                            {
                                    System.out.println("Enter index of the staff member for deletion: ");
                                    input = scan.nextInt();
                                    dataStaff.remove(input);
                                    System.out.println();
                            }
                            

                        }
                        command = "";
                    }
                    
//==============================================================================
//==============================GAMES===========================================
//==============================================================================                    
                    
                    if(command.equalsIgnoreCase("3"))
                    {
                        command = "";
                        while(!command.equalsIgnoreCase("0"))
                        {
                            System.out.println("1: View games");
                            System.out.println("2: Add games");
                            System.out.println("3: Delete games");
                            System.out.println("0: back");
                            command = scan.next();
                            System.out.println();
                            
                            //View
                            if(command.equalsIgnoreCase("1"))
                            {
                                    for(Games obj: dataGames)
                                    {
                                        System.out.println(obj);
                                    }
                                    System.out.println();
                            }          
                            
                            //Add
                            if(command.equalsIgnoreCase("2"))
                            {
                                    System.out.println("How many games would you like to enter: ");
                                    input = scan.nextInt();

                                    for(int i=0;i<input;i++)
                                    {
                                        System.out.println("Enter details of staff member " + (i+1));
                                        System.out.println("Enter title: ");
                                        title = scan.next();
                                        System.out.println("Enter genre: ");
                                        genre = scan.next();
                                        System.out.println("Enter system: ");
                                        sys = scan.next();
                                        System.out.println("Enter condition: ");
                                        condition = scan.next();
                                        System.out.println("Enter price: ");
                                        price = scan.nextInt();
                                        dataGames.add(new Games(title, genre, sys, condition, price));
                                    }
                                    System.out.println();
                            }
                            
                            //Delete
                            if(command.equalsIgnoreCase("3"))
                            {
                                    System.out.println("Enter index of the game for deletion: ");
                                    input = scan.nextInt();
                                    dataGames.remove(input);
                                    System.out.println();
                            }
                            
                        }
                        command = "";
                    }
//==============================================================================
//=============================CONSOLES=========================================
//==============================================================================
                    if(command.equalsIgnoreCase("4"))
                    {
                        command = "";
                        while(!command.equalsIgnoreCase("0"))
                        {
                            System.out.println("1: View consoles");
                            System.out.println("2: Add consoles");
                            System.out.println("3: Delete consoles");
                            System.out.println("0: back");
                            command = scan.next();
                            System.out.println();
                            
                            
                            //View
                            if(command.equalsIgnoreCase("1"))
                            {
                                    for(Consoles obj: dataConsoles)
                                    {
                                        System.out.println(obj);
                                    }
                                    System.out.println();
                            } 
                            
                            //Add
                            if(command.equalsIgnoreCase("2"))
                            {
                                    System.out.println("How many consoles would you like to enter: ");
                                    input = scan.nextInt();

                                    for(int i=0;i<input;i++)
                                    {
                                        System.out.println("Enter details for the console" + (i+1));
                                        System.out.println("Enter model: ");
                                        model = scan.next();
                                        System.out.println("Enter condition: ");
                                        condition = scan.next();
                                        System.out.println("Enter price: ");
                                        price = scan.nextInt();
                                        dataConsoles.add(new Consoles(model, condition, price));
                                    }
                                    System.out.println();
                            }
                            
                            //Delete
                            if(command.equalsIgnoreCase("3"))
                            {
                                    System.out.println("Enter index of the console for deletion: ");
                                    input = scan.nextInt();
                                    dataConsoles.remove(input);
                                    System.out.println();
                            }
                            
                        }
                        command = "";
                    }
//==============================================================================
//=============================MISC. ITEMS======================================
//==============================================================================                    
                    if(command.equalsIgnoreCase("5"))
                    {
                        command = "";
                        while(!command.equalsIgnoreCase("0"))
                        {
                            System.out.println("1: View misc. items");
                            System.out.println("2: Add misc. items");
                            System.out.println("3: Delete misc. items");
                            System.out.println("0: back");
                            command = scan.next();
                            System.out.println();
                            
                            //View
                            if(command.equalsIgnoreCase("1"))
                            {
                                    for(Misc obj: dataMisc)
                                    {
                                        System.out.println(obj);
                                    }
                                    System.out.println();
                            }                    
                            
                            //Add
                            if(command.equalsIgnoreCase("2"))
                            {
                                    System.out.println("How many misc. items would you like to enter: ");
                                    input = scan.nextInt();

                                    for(int i=0;i<input;i++)
                                    {
                                        System.out.println("Enter details of misc. item " + (i+1));
                                        System.out.println("Enter item name: ");
                                        itemName = scan.next();
                                        System.out.println("Enter item description: ");
                                        itemDescription = scan.next();
                                        System.out.println("Enter item type: ");
                                        itemType = scan.next();
                                        System.out.println("Enter item price: ");
                                        price = scan.nextInt();
                                        dataMisc.add(new Misc(itemName, itemDescription, itemType, price));
                                    }
                                    System.out.println();
                            }
                            
                            //Delete
                            if(command.equalsIgnoreCase("3"))
                            {
                                    System.out.println("Enter index of the misc. item for deletion: ");
                                    input = scan.nextInt();
                                    dataMisc.remove(input);
                                    System.out.println();
                            }
                            
                        }
                        command = "";
                    }

                    
                }
                command = "";
            }
        
        
        
        }   
    }
}

//    public static String UserCheck(String Email) {
//
//		String result = "Valid E-mail";			
//		int atLoc =0;						
//		int perLoc =0;						
//		int atCount =0;						
//		int perCount =0;					
//
//		for (int x =0; x < Email.length(); x++) {
//			if ((Email.charAt(x) >= 48 && Email.charAt(x) <= 57) || (Email.charAt(x) >= 65 && Email.charAt(x) <= 90) ||
//				(Email.charAt(x) >= 97 && Email.charAt(x) <= 122) || (Email.charAt(x) == 64) || (Email.charAt(x) == 46)
//				|| (Email.charAt(x) == 95)) {
//					
//				} else {
//					result = "E-mail Contains Invalid Character!";		
//				}
//
//			if (Email.charAt(x) == 64) {							
//				atLoc = x;
//				atCount++;
//			}
//
//			if (Email.charAt(x) == 46) {							
//				perLoc = x;
//				perCount++;
//			}
//		} 
//
//		if (atLoc < 1 || perLoc < 1)								
//		{
//			result = "Not a valid email Address";
//		}
//
//		if (perLoc < atLoc) {
//			result = "Not a valid email address";						
//		}
//
//		if ((atCount > 1) || (perCount > 1)) {							
//			result = "Not a valid email address";
//		}
//
//		return (result);						
//
//	} 
//
//    public static String PassCheck (String Password) {
//
//		String result = "Valid Password";			
//		int length = 0;						
//					
//
//
//		for (int x =0; x < Password.length(); x++) {
//			if ((Password.charAt(x) >= 47 && Password.charAt(x) <= 58) || (Password.charAt(x) >= 64 && Password.charAt(x) <= 91) ||
//				(Password.charAt(x) >= 97 && Password.charAt(x) <= 122)) {
//					
//				} else {
//					result = "Password Contains Invalid Character!";		
//				}
//
//
//			length = (x + 1);								
//
//		} 
//
//
//		if (length < 8){									
//			result = "Password is Too Short!";
//		}
//
//			return (result);								
//
//	} 

