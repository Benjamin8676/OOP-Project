package RevisedGamestore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;            // We will extend Java's base Thread class
import java.net.Socket;
import java.io.ObjectInputStream;   // For reading Java objects off of the wire
import java.io.ObjectOutputStream;  // For writing Java objects to the wire
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * A simple server thread.  This class just echoes the messages sent
 * over the socket until the socket is closed.
 *
 */
public class Threads extends Thread
{
    private final Socket socket;                   // The socket that we'll be talking over

    /**
     * Constructor that sets up the socket we'll chat over
     *
     * @param _socket The socket passed in from the server
     *
     */
    public Threads(Socket _socket)
    {
	socket = _socket;
    }


    /**
     * run() is basically the main method of a thread.  This thread
     * simply reads Message objects off of the socket.
     *
     */
    public void run()
    {
        int ID = 0, num = 0;
        double price = 0, total = 0.0;
        String name = "", password = "", title = "", genre = "", sys = "", condition = "", model = "";
        String itemName = "", itemDescription = "", itemType = "";
        boolean login;
        
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
	try{
	    // Print incoming message
	    System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

	    // set up I/O streams with the client
    	    final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	    final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

	    // Loop to read messages
	    Message msg = null;
            
	    do{
                output.writeObject(new Message("\n1: Login as member \n2: Login as staff \n0: Exit"));
		// read and print message
		msg = (Message)input.readObject();
		System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);

                if(msg.theMessage.equalsIgnoreCase("1"))
                {
                    login = false;
                    
                    if(login == false)
                    {
                        while(!login)
                        {
                        output.writeObject(new Message("\nPlease enter your username"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        name = msg.theMessage;

                        output.writeObject(new Message("\nPlease enter your password"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        password = msg.theMessage;
                        
                        for(int i = 0; i < dataMembers.size(); i++)
                        {
                            System.out.println(dataMembers.size());
                            System.out.println("username " + dataMembers.get(i).getUsername().equals(name) + "password: " + dataMembers.get(i).getPassword().equals(password) + "login " + login);
                            if(dataMembers.get(i).getUsername().equals(name) && dataMembers.get(i).getPassword().equals(password))
                            {
                                    login = true;
                                    break;
                            }
                            else
                                login = false;
                        }
                        break;
                        }
                    }
                    
                    if(login == true)
                    msg = loginAsMember(output, input, msg, title, model, itemName, num, price, total, dataGames, dataCart, dataConsoles, dataMisc);
                }
                
                if(msg.theMessage.equalsIgnoreCase("2"))
                {
                    login = false;
                    
                    if(login == false)
                    {
                        while(!login)
                        {
                        output.writeObject(new Message("\nPlease enter your username"));
                        // read and print message
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        name = msg.theMessage;

                        output.writeObject(new Message("\nPlease enter your password"));
                        // read and print message
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        password = msg.theMessage;
                        
                        for(int i = 0; i < dataStaff.size(); i++)
                        {
                            if(dataStaff.get(i).getName().equals(name) && dataStaff.get(i).getPassword().equals(password))
                            {
                                    login = true;
                                    break;
                            }
                            else
                                login = false;
                        }
                        break;
                        }
                    }
                    
                    if(login == true)
                    msg = loginAsStaff(output, input, msg, title, model, genre, sys, condition, name, password, itemName, itemDescription, itemType, ID, num, price, dataMembers, dataStaff, dataGames, dataConsoles, dataMisc);
                }
                    
	    }while(!msg.theMessage.equalsIgnoreCase("0"));

	    // Close and cleanup
	    System.out.println("** Closing connection with " + socket.getInetAddress() + ":" + socket.getPort() + " **");
	    socket.close();

	}
	catch(Exception e){
	    System.err.println("Error: " + e.getMessage());
	    e.printStackTrace(System.err);
	}

    }  //-- end run()
    
    public Message loginAsMember(ObjectOutputStream output, ObjectInputStream input, Message msg, String title, String model, String itemName, int num, Double price,Double total, ArrayList<Games> dataGames, ArrayList<Cart> dataCart, ArrayList<Consoles> dataConsoles, ArrayList<Misc> dataMisc)//, ArrayList<Members> dataMembers)
    {
        try {
            output.writeObject(new Message ("\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
            msg = (Message)input.readObject();
            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
        do{
                if(msg.theMessage.equalsIgnoreCase("1"))
                {
                    break;
                }
		if(msg.theMessage.equalsIgnoreCase("2"))
                    {
                        String [] array = new String[dataGames.size()];
                        int i = 0;
                        for(Games obj: dataGames)
                        {
                            array[i] = obj.toString();
                            i++;
                        }
                        String str = String.join("\n",array);
                        output.writeObject(new Message(str + "\n \nWould you like to add an item to your cart?(y/n)"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        
                        if(msg.theMessage.equalsIgnoreCase("y"))
                        {
                            output.writeObject(new Message ("Please enter the item number you wish to add to cart"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            num = Integer.parseInt(msg.theMessage);
                            num = num - 1;
                            title = dataGames.get(num).getTitle();
                            price = dataGames.get(num).getPrice();
                            dataCart.add(new Cart(title,price));
                            output.writeObject(new Message ("Item added to cart \n\n1: Back \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        }
                        
                        if(msg.theMessage.equalsIgnoreCase("n"))
                        {
                            output.writeObject(new Message ("\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        }
                        
                    }
                    
                    if(msg.theMessage.equalsIgnoreCase("3"))
                    {
                        String [] array = new String[dataConsoles.size()];
                        int i = 0;
                        for(Consoles obj: dataConsoles)
                        {
                            array[i] = obj.toString();
                            i++;
                        }
                        
                        String str = String.join("\n",array);
                        output.writeObject(new Message(str + "\n \nWould you like to add an item to your cart?(y/n)"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        
                        if(msg.theMessage.equalsIgnoreCase("y"))
                        {
                            output.writeObject(new Message ("Please enter the item number you wish to add to cart"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            num = Integer.parseInt(msg.theMessage);
                            num = num - 1;
                            model = dataConsoles.get(num).getModel();
                            price = dataConsoles.get(num).getPrice();
                            dataCart.add(new Cart(model,price));
                            output.writeObject(new Message ("Item added to cart \n\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            
                        }
                        
                        if(msg.theMessage.equalsIgnoreCase("n"))
                        {
                            output.writeObject(new Message ("\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        }
                    }
                    
                    if(msg.theMessage.equalsIgnoreCase("4"))
                    {
                        String [] array = new String[dataMisc.size()];
                        int i = 0;
                        for(Misc obj: dataMisc)
                        {
                            array[i] = obj.toString();
                            i++;
                        }
                        
                        String str = String.join("\n",array);
                        output.writeObject(new Message(str + "\n \nWould you like to add an item to your cart?(y/n)"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        
                        if(msg.theMessage.equalsIgnoreCase("y"))
                        {
                            output.writeObject(new Message ("Please enter the item number you wish to add to cart"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            num = Integer.parseInt(msg.theMessage);
                            num = num - 1;
                            itemName = dataMisc.get(num).getItemName();
                            price = dataMisc.get(num).getPrice();
                            dataCart.add(new Cart(itemName,price));
                            output.writeObject(new Message ("Item added to cart \n\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        }
                        
                        if(msg.theMessage.equalsIgnoreCase("n"))
                        {
                            output.writeObject(new Message ("\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        }
                    }
                    
                    if(msg.theMessage.equalsIgnoreCase("5"))
                    {
                        if(dataCart.isEmpty())
                        {
                            output.writeObject(new Message("Cart is empty \n\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        }
                        else
                        {
                            total = 0.0;
                            String [] array = new String[dataCart.size()];
                            int i = 0;
                            for(Cart obj: dataCart)
                                {
                                    total = total + obj.price;
                                    array[i] = obj.toString();
                                    i++;
                                }
                            
                            String str = String.join("\n",array);
                            output.writeObject(new Message(str + "\n\nThe total cost of the items is " + total + "\n\n1: Back \n2: Purchase Items" + "\n3: Remove Item" + "\n0: Exit"));
                            msg = (Message)input.readObject();
                            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);

                            while(true)
                            {
                                if(msg.theMessage.equalsIgnoreCase("1"))
                                {
                                    output.writeObject(new Message("\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    break;
                                }

                                if(msg.theMessage.equalsIgnoreCase("2"))
                                {
                                    output.writeObject(new Message("Thank you for your purchase \n\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    dataCart.clear();
                                    break;
                                }

                                if(msg.theMessage.equalsIgnoreCase("3"))
                                {
                                    output.writeObject(new Message("Please enter the item number you wish to remove"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);
                                    num = num - 1;
                                    dataCart.remove(num);
                                    if(dataCart.isEmpty())
                                    {
                                        output.writeObject(new Message("Item removed \n\nCart is empty \n\n1: Logout \n2: View Games \n3: View Consoles \n4: View Misc Items \n5: View Cart \n0: Exit"));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        break;
                                    }
                                    else
                                    {
                                        total = 0.0;
                                        i=0;
                                        String [] newArray = new String[dataCart.size()];

                                        for(Cart obj: dataCart)
                                        {
                                            total = total + obj.price;
                                            newArray[i] = obj.toString();
                                            i++;
                                        }
                                        
                                        str = String.join("\n",newArray);
                                        output.writeObject(new Message(str + "\n\nThe total cost of the items is " + total + "\n\n1: Back \n2: Purchase Items" + "\n3: Remove Item" + "\n0: Exit"));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    }
                                }
                                if(msg.theMessage.equalsIgnoreCase("0"))
                                {
                                    return msg;
                                }
                            }
                        }
                    }
                if(msg.theMessage.equalsIgnoreCase("0"))
                {
                    return msg;
                }
        }while(!msg.theMessage.equalsIgnoreCase("1") || !msg.theMessage.equalsIgnoreCase("0"));
    }
        catch(Exception e){
	    System.err.println("Error: " + e.getMessage());
	    e.printStackTrace(System.err);
	}
        return msg;
    }
    
    public Message loginAsStaff(ObjectOutputStream output, ObjectInputStream input, Message msg, String title, String model, String genre, String sys, String condition, String name, String password, String itemName, String itemDescription, String itemType, int ID, int num, double price, ArrayList<Members> dataMembers, ArrayList<Staff> dataStaff, ArrayList<Games> dataGames, ArrayList<Consoles> dataConsoles, ArrayList<Misc> dataMisc)
    {
        try{
            
            output.writeObject(new Message("\n1: Logout \n2: Maintain members \n3: Maintain staff \n4: Maintain games \n5: Maintain consoles \n6: Maintain misc. items \n0: Exit"));
            msg = (Message)input.readObject();
            System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
            
            if(msg.theMessage.equalsIgnoreCase("1"))
                return msg;
                
            do
            {
                if(msg.theMessage.equalsIgnoreCase("0"))
                    return msg;
                if(msg.theMessage.equalsIgnoreCase("1"))
                {
                    output.writeObject(new Message("\n1: Logout \n2: Maintain members \n3: Maintain staff \n4: Maintain games \n5: Maintain consoles \n6: Maintain misc. items \n0: Exit"));
                    msg = (Message)input.readObject();
                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                    if(msg.theMessage.equalsIgnoreCase("1"))
                    break;
                }
                if(msg.theMessage.equalsIgnoreCase("2"))
                    {
                        output.writeObject(new Message("1: Back \n2: View members \n3: Add members \n4: Delete members \n0: Exit"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            
                        while(!msg.theMessage.equalsIgnoreCase("0"))
                        {
                            if(msg.theMessage.equalsIgnoreCase("1"))
                            {
                                break;
                            }
                            //View
                            if(msg.theMessage.equalsIgnoreCase("2"))
                            {
                                String [] array = new String[dataMembers.size()];
                                int i = 0;
                                
                                for(Members obj: dataMembers)
                                {
                                    array[i] = obj.toString();
                                    i++;
                                }
                                String str = String.join("\n",array);
                                output.writeObject(new Message(str + "\n\n1: Back \n2: View members \n3: Add members \n4: Delete members \n0: Exit"));
                                msg = (Message)input.readObject();
                                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Add
                            if(msg.theMessage.equalsIgnoreCase("3"))
                            {
                                    output.writeObject(new Message("How many members would you like to enter: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);

                                    for(int i = 0; i < num; i++)
                                    {
                                        output.writeObject(new Message("Enter details of member " + (i+1) + "\nEnter username: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        name = msg.theMessage;
                                        output.writeObject(new Message("Enter password: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        password = msg.theMessage;
                                        output.writeObject(new Message("Enter ID: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        ID = Integer.parseInt(msg.theMessage);
                                        dataMembers.add(new Members(name, password, ID));
                                    }
                                    
                                    output.writeObject(new Message("\n\n1: Back \n2: View members \n3: Add members \n4: Delete members \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Delete
                            if(msg.theMessage.equalsIgnoreCase("4"))
                            {
                                    output.writeObject(new Message("Enter index of the member for deletion: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);
                                    dataMembers.remove(num);
                                    output.writeObject(new Message("\nMember deleted \n\n1: Back \n2: View members \n3: Add members \n4: Delete members \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                        }
                    }
                    
//==============================================================================
//===============================STAFF==========================================
//==============================================================================

                    if(msg.theMessage.equalsIgnoreCase("3"))
                    {
                        output.writeObject(new Message("\n1: Back \n2: View staff \n3: Add staff \n4: Delete staff \n0: Exit"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        while(!msg.theMessage.equalsIgnoreCase("0"))
                        {
                            if(msg.theMessage.equalsIgnoreCase("1"))
                            break;
                            //View
                            if(msg.theMessage.equalsIgnoreCase("2"))
                            {
                                String [] array = new String[dataStaff.size()];
                                int i = 0;
                                
                                for(Staff obj: dataStaff)
                                {
                                    array[i] = obj.toString();
                                    i++;
                                }
                                String str = String.join("\n",array);
                                output.writeObject(new Message(str + "\n\n1: Back \n2: View staff \n3: Add staff \n4: Delete staff \n0: Exit"));
                                msg = (Message)input.readObject();
                                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Add
                            if(msg.theMessage.equalsIgnoreCase("3"))
                            {
                                    output.writeObject(new Message("\nHow many staff would you like to enter: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);

                                    for(int i=0; i < num; i++)
                                    {
                                        output.writeObject(new Message("Enter details of staff member " + (i+1) + "\nEnter name: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        name = msg.theMessage;
                                        output.writeObject(new Message("Enter password: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        password = msg.theMessage;
                                        output.writeObject(new Message("Enter staff ID: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        ID = Integer.parseInt(msg.theMessage);
                                        dataStaff.add(new Staff(name, password, ID));
                                    }
                                    output.writeObject(new Message("\n\n1: Back \n2: View staff \n3: Add staff \n4: Delete staff \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Delete
                            if(msg.theMessage.equalsIgnoreCase("4"))
                            {
                                    output.writeObject(new Message("Enter index of the staff member for deletion: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);
                                    dataStaff.remove(num);
                                    output.writeObject(new Message("\nStaff member deleted \n\n1: Back \n2: View staff \n3: Add staff \n4: Delete staff \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                        }
                    }
                    
//==============================================================================
//==============================GAMES===========================================
//==============================================================================                    
                    
                    if(msg.theMessage.equalsIgnoreCase("4"))
                    {
                        output.writeObject(new Message("\n1: Back \n2: View games \n3: Add games \n4: Delete games \n0: Exit"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        
                        while(!msg.theMessage.equalsIgnoreCase("0"))
                        {
                            if(msg.theMessage.equalsIgnoreCase("1"))
                            break;
                            //View
                            if(msg.theMessage.equalsIgnoreCase("2"))
                            {
                                String [] array = new String[dataGames.size()];
                                int i = 0;
                                
                                for(Games obj: dataGames)
                                {
                                    array[i] = obj.toString();
                                    i++;
                                }
                                String str = String.join("\n",array);
                                output.writeObject(new Message(str + "\n\n1: Back \n2: View games \n3: Add games \n4: Delete games \n0: Exit"));
                                msg = (Message)input.readObject();
                                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }          
                            
                            //Add
                            if(msg.theMessage.equalsIgnoreCase("3"))
                            {
                                    output.writeObject(new Message("How many games would you like to enter: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);

                                    for(int i = 0; i < num; i++)
                                    {
                                        output.writeObject(new Message("Enter details of game number " + (i+1) + "\nEnter title: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        title = msg.theMessage;
                                        output.writeObject(new Message("Enter genre: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        genre = msg.theMessage;
                                        output.writeObject(new Message("Enter system: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        sys = msg.theMessage;
                                        output.writeObject(new Message("Enter condition: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        condition = msg.theMessage;
                                        output.writeObject(new Message("Enter price: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        price = Integer.parseInt(msg.theMessage);
                                        dataGames.add(new Games(title, genre, sys, condition, price));
                                    }
                                    
                                    output.writeObject(new Message("\n\n1: Back \n2: View games \n3: Add games \n4: Delete games \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Delete
                            if(msg.theMessage.equalsIgnoreCase("4"))
                            {
                                    output.writeObject(new Message("Enter index of the game for deletion: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);
                                    dataGames.remove(num);
                                    output.writeObject(new Message("\nGame deleted \n\n1: Back \n2: View games \n3: Add games \n4: Delete games \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                        }
                    }
//==============================================================================
//=============================CONSOLES=========================================
//==============================================================================
                    if(msg.theMessage.equalsIgnoreCase("5"))
                    {
                        output.writeObject(new Message("\n1: Back \n2: View consoles \n3: Add consoles \n4: Delete consoles \n0: Exit"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        
                        while(!msg.theMessage.equalsIgnoreCase("0"))
                        {
                            if(msg.theMessage.equalsIgnoreCase("1"))
                            break;
                            
                            //View
                            if(msg.theMessage.equalsIgnoreCase("2"))
                            {
                                String [] array = new String[dataConsoles.size()];
                                int i = 0;
                                
                                for(Consoles obj: dataConsoles)
                                {
                                    array[i] = obj.toString();
                                    i++;
                                }
                                String str = String.join("\n",array);
                                output.writeObject(new Message(str + "\n\n1: Back \n2: View consoles \n3: Add consoles \n4: Delete consoles \n0: Exit"));
                                msg = (Message)input.readObject();
                                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            } 
                            
                            //Add
                            if(msg.theMessage.equalsIgnoreCase("3"))
                            {
                                    output.writeObject(new Message("How many consoles would you like to enter: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);

                                    for(int i = 0; i < num; i++)
                                    {
                                        output.writeObject(new Message("Enter details for the console " + (i+1) + "\nEnter model: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        model = msg.theMessage;
                                        output.writeObject(new Message("Enter condition: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        condition = msg.theMessage;
                                        output.writeObject(new Message("Enter price: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        price = Integer.parseInt(msg.theMessage);
                                        dataConsoles.add(new Consoles(model, condition, price));
                                    }
                                    
                                    output.writeObject(new Message("\n\n1: Back \n2: View consoles \n3: Add consoles \n4: Delete consoles \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Delete
                            if(msg.theMessage.equalsIgnoreCase("4"))
                            {
                                    output.writeObject(new Message("Enter index of the console for deletion: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);
                                    dataConsoles.remove(num);
                                    output.writeObject(new Message("\nConsole deleted \n\n1: Back \n2: View consoles \n3: Add consoles \n4: Delete consoles \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                        }
                    }
//==============================================================================
//=============================MISC. ITEMS======================================
//==============================================================================                    
                    if(msg.theMessage.equalsIgnoreCase("6"))
                    {
                        output.writeObject(new Message("\n1: Back \n2: View misc. items \n3: Add misc. items \n4: Delete misc. items \n0: Exit"));
                        msg = (Message)input.readObject();
                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                        
                        while(!msg.theMessage.equalsIgnoreCase("0"))
                        {
                            if(msg.theMessage.equalsIgnoreCase("1"))
                            break;
                            //View
                            if(msg.theMessage.equalsIgnoreCase("2"))
                            {
                                String [] array = new String[dataMisc.size()];
                                int i = 0;
                                
                                for(Misc obj: dataMisc)
                                {
                                    array[i] = obj.toString();
                                    i++;
                                }
                                String str = String.join("\n",array);
                                output.writeObject(new Message(str + "\n\n1: Back \n2: View misc. items \n3: Add misc. items \n4: Delete misc. items \n0: Exit"));
                                msg = (Message)input.readObject();
                                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }                    
                            
                            //Add
                            if(msg.theMessage.equalsIgnoreCase("3"))
                            {
                                    output.writeObject(new Message("How many misc. items would you like to enter: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage);

                                    for(int i = 0; i < num; i++)
                                    {
                                        output.writeObject(new Message("Enter details of misc. item " + (i+1) + "\nEnter item name: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        itemName = msg.theMessage;
                                        output.writeObject(new Message("Enter item description: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        itemDescription = msg.theMessage;
                                        output.writeObject(new Message("Enter item type: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        itemType = msg.theMessage;
                                        output.writeObject(new Message("Enter item price: "));
                                        msg = (Message)input.readObject();
                                        System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                        price = Integer.parseInt(msg.theMessage);
                                        dataMisc.add(new Misc(itemName, itemDescription, itemType, price));
                                    }
                                    output.writeObject(new Message("\n\n1: Back \n2: View misc. items \n3: Add misc. items \n4: Delete misc. items \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                            //Delete
                            if(msg.theMessage.equalsIgnoreCase("4"))
                            {
                                    output.writeObject(new Message("Enter index of the misc. item for deletion: "));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                                    num = Integer.parseInt(msg.theMessage); 
                                    dataMisc.remove(num);
                                    output.writeObject(new Message("\nMisc item deleted \n\n1: Back \n2: View misc. items \n3: Add misc. items \n4: Delete misc. items \n0: Exit"));
                                    msg = (Message)input.readObject();
                                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);
                            }
                            
                        }
                    }

            }while(!msg.theMessage.equalsIgnoreCase("1") || !msg.theMessage.equalsIgnoreCase("0"));
            
        }
        catch(Exception e){
	    System.err.println("Error: " + e.getMessage());
	    e.printStackTrace(System.err);
	}
        return msg;
    }

} //-- end class EchoThread