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
        int ID;
        double price = 0, total = 0.0;
        String name, password, title = "", genre, sys, condition, model = "";
        String itemName = "", itemDescription, itemType;
        
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
//	    int count = 0;
            
            output.writeObject(new Message("1: Login as member \n2: Login as staff \n0: Quit"));
            
	    do{
		// read and print message
		msg = (Message)input.readObject();
		System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.theMessage);

                if(msg.theMessage.equalsIgnoreCase("1"))
                    //System.out.println("inside 1");
                    loginAsMember(output, input, msg, title, model, itemName, price, total, dataGames, dataCart, dataConsoles, dataMisc);
             //   if(msg.theMessage.equalsIgnoreCase("2"))
//                    loginAsStaff(output, input, msg);
		// Write an ACK back to the sender
//		count++;
//		output.writeObject(new Message("Recieved message #" + count));
                    
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
    
    public void loginAsMember(ObjectOutputStream output, ObjectInputStream input, Message msg, String title, String model, String itemName, Double price,Double total, ArrayList<Games> dataGames, ArrayList<Cart> dataCart, ArrayList<Consoles> dataConsoles, ArrayList<Misc> dataMisc)//, ArrayList<Members> dataMembers)
    {
        try {
           // final DataInputStream input;// = new ObjectInputStream(socket.getInputStream());
	   // final OutputStream output = new FileOutputStream("inside login try");//t = new ObjectOutputStream(socket.getOutputStream());
           // output.writeUTF("insdie login try");
           // System.out.println("inside login try");
    	    
        
        output.writeObject(new Message ("1: View Games \n2: View Consoles \n3: View Misc Items \n4: View Cart \n0: Back"));
        msg = (Message)input.readObject();
        
        do{
		if(msg.theMessage.equalsIgnoreCase("1"))
                    {
                        String [] array = new String[dataGames.size()];
                        int i = 0;
                        for(Games obj: dataGames)
                        {
                            array[i] = obj.toString();
                            i++;
//                            output.writeObject(new Message (obj.toString()));
                        }
                        output.writeObject(new Message (array[0] + "\n" + array[1] + "\n" + array[2] + "\n" + array[3] + "\n" + array[4] + "\n \nWould you like to add an item to your cart?(y/n)"));
                        //System.out.println();
                        //output.writeObject(new Message ("\nWould you like to add an item to your cart?(y/n)"));
                        msg = (Message)input.readObject();
                        
                        if(msg.theMessage.equalsIgnoreCase("y"))
                        {
                            output.writeObject(new Message ("Please enter the item number you wish to add to cart"));
                            msg = (Message)input.readObject();
                            int num = Integer.parseInt(msg.theMessage);
                            num = num - 1;
                            title = dataGames.get(num).getTitle();
                            //title = dataGames.get(input).getTitle();
                            price = dataGames.get(num).getPrice();
                            dataCart.add(new Cart(title,price));
                            output.writeObject(new Message ("Item added to cart \n\n1: View Games \n2: View Consoles \n3: View Misc Items \n4: View Cart \n0: Back"));
                            msg = (Message)input.readObject();
                            //System.out.println();
                            
                        }
                        
                        //System.out.println();
                    }
                    
                    if(msg.theMessage.equalsIgnoreCase("2"))
                    {
                        String [] array = new String[dataConsoles.size()];
                        int i = 0;
                        for(Consoles obj: dataConsoles)
                        {
                            array[i] = obj.toString();
                            i++;
                        }
                        output.writeObject(new Message (array[0] + "\n" + array[1] + "\n" + array[2] + "\n \nWould you like to add an item to your cart?(y/n)"));
                       // System.out.println();
                        //System.out.println("Would you like to add an item to your cart?(y/n)");
                        //response = scan.next();
                        msg = (Message)input.readObject();
                        
                        if(msg.theMessage.equalsIgnoreCase("y"))
                        {
                            output.writeObject(new Message ("Please enter the index of the item you wish to add to cart"));
                            //input = scan.nextInt();
                            msg = (Message)input.readObject();
                           // title = dataGames.get(Integer.parseInt(msg.theMessage)).getTitle();
                            model = dataConsoles.get(Integer.parseInt(msg.theMessage)).getModel();
                           // model = dataConsoles.get(input).getModel();
                            price = dataConsoles.get(Integer.parseInt(msg.theMessage)).getPrice();
                            //price = dataConsoles.get(input).getPrice();
                            dataCart.add(new Cart(model,price));
                            output.writeObject(new Message ("Item added to cart \n\n1: View Games \n2: View Consoles \n3: View Misc Items \n4: View Cart \n0: Back"));
                            msg = (Message)input.readObject();
                            //System.out.println();
                            
                        }
                       // System.out.println();
                    }
                    
                    if(msg.theMessage.equalsIgnoreCase("3"))
                    {
                        String [] array = new String[dataMisc.size()];
                        int i = 0;
                        for(Misc obj: dataMisc)
                        {
                            array[i] = obj.toString();
                            i++;
                        }
                        output.writeObject(new Message (array[0] + "\n" + array[1] + "\n" + array[2] + "\n" + array[3] + "\n \nWould you like to add an item to your cart?(y/n)"));
                        //System.out.println();
                        //System.out.println("Would you like to add an item to your cart?(y/n)");
                        //response = scan.next();
                        msg = (Message)input.readObject();
                        
                        if(msg.theMessage.equalsIgnoreCase("y"))
                        {
                            output.writeObject(new Message ("Please enter the index of the item you wish to add to cart"));
                            //input = scan.nextInt();
                            msg = (Message)input.readObject();
                            //model = dataConsoles.get(Integer.parseInt(msg.theMessage)).getModel();
                            itemName = dataMisc.get(Integer.parseInt(msg.theMessage)).getItemName();
                            //itemName = dataMisc.get(input).getItemName();
                            price = dataMisc.get(Integer.parseInt(msg.theMessage)).getPrice();
                            dataCart.add(new Cart(itemName,price));
                            output.writeObject(new Message ("Item added to cart \n\n1: View Games \n2: View Consoles \n3: View Misc Items \n4: View Cart \n0: Back"));
                            msg = (Message)input.readObject();
                            //System.out.println();
                            
                        }
                        
                        //System.out.println();
                    }
                    
                    if(msg.theMessage.equalsIgnoreCase("4"))
                    {
                        if(dataCart.isEmpty())
                        {
                            output.writeObject(new Message("Cart is empty"));
                            //System.out.println();
                        }
                        else
                        {
                            total = 0.0;
                            for(Cart obj: dataCart)
                                {
                                    System.out.println(obj);
                                }
                            for(Cart obj: dataCart)
                                {
                                    total = total + obj.price;
                                }
                            //System.out.println();
                            output.writeObject(new Message("The total cost of the items is " + total));
                            //System.out.println();

                            while(!msg.theMessage.equalsIgnoreCase("0"))
                            {
                                output.writeObject(new Message("1: Purchase Items" + "\n2: Remove Item" + "\n0: back"));
                                //command = scan.next();
                                msg = (Message)input.readObject();
                                //System.out.println();


                                if(msg.theMessage.equalsIgnoreCase("1"))
                                {
                                    output.writeObject(new Message("Thank you for your purchase"));
                                    //System.out.println();
                                    dataCart.clear();
                                    msg.theMessage = "0";
                                }

                                if(msg.theMessage.equalsIgnoreCase("2"))
                                {
                                    output.writeObject(new Message("Please enter the index of the item you wish to remove"));
                                    //input = scan.nextInt();
                                    msg = (Message)input.readObject();
                                    dataCart.remove(msg);
                                    output.writeObject(new Message("Item removed"));
                                    if(dataCart.isEmpty())
                                    {
                                        output.writeObject(new Message("Cart is empty"));
                                        //System.out.println();
                                        msg.theMessage = "0";
                                    }
                                    else
                                    {
                                        total = 0.0;

                                        for(Cart obj: dataCart)
                                        {
                                            System.out.println(obj);
                                        }
                                        for(Cart obj: dataCart)
                                        {
                                            total = total + obj.price;
                                        }
                                        //System.out.println();
                                        output.writeObject(new Message("The total cost of the items is " + total));
                                        //System.out.println();
                                    }
                                }
                            }
                        }
                        msg.theMessage = "";
                    }
                //}
                msg.theMessage = "";
               

            //}

	//	msg = (Message)input.readObject();
        output.writeObject(new Message ("1: View Games \n2: View Consoles \n3: View Misc Items \n4: View Cart \n0: Back"));
        msg = (Message)input.readObject();
        }while(!msg.theMessage.equalsIgnoreCase("0"));
    }
        catch(Exception e){
	    System.err.println("Error: " + e.getMessage());
	    e.printStackTrace(System.err);
	}
    }
    
//    public void loginAsStaff(ObjectOutputStream output, ObjectInputStream input, Message msg)
//    {
//        try{
//            
//            output.writeObject(new Message("1: Maintain members \n2: Maintain staff \n3: Maintain games \n4: Maintain consoles \n5: Maintain misc. items \n0: back"));
//            msg = (Message)input.readObject();
//            do
//            {
//                if(msg.theMessage.equalsIgnoreCase("1"))
//                    {
//                        msg.theMessage = "";
//                        while(!msg.theMessage.equalsIgnoreCase("0"))
//                        {
//                            output.writeObject(new Message("1: View members \n2: Add members \n3: Delete members \n0: back"));
//                            msg = (Message)input.readObject();
//                            //System.out.println();
//                            
//                            //View
//                            if(msg.theMessage.equalsIgnoreCase("1"))
//                            {
//                                    for(Members obj: dataMembers)
//                                    {
//                                        System.out.println(obj);
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Add
//                            if(msg.theMessage.equalsIgnoreCase("2"))
//                            {
//                                    output.writeObject(new Message("How many members would you like to enter: "));
//                                    msg = (Message)input.readObject();
//
//                                    for(int i=0;i<Integer.parseInt(msg.theMessage);i++)
//                                    {
//                                        output.writeObject(new Message("Enter details of member " + (i+1)));
//                                        System.out.println("Enter username: ");
//                                        name = scan.next();
//                                        System.out.println("Enter password: ");
//                                        password = scan.next();
//                                        System.out.println("Enter ID: ");
//                                        ID = scan.nextInt();
//                                        dataMembers.add(new Members(name, password, ID));
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Delete
//                            if(command.equalsIgnoreCase("3"))
//                            {
//                                    System.out.println("Enter index of the member for deletion: ");
//                                    input = scan.nextInt();
//                                    dataMembers.remove(input);
//                                    System.out.println();
//                            }
//                            
//                        }
//                        command = "";
//                    }
//                    
////==============================================================================
////===============================STAFF==========================================
////==============================================================================
//
//                    if(command.equalsIgnoreCase("2"))
//                    {
//                        command = "";
//                        while(!command.equalsIgnoreCase("0"))
//                        {
//                            System.out.println("1: View staff");
//                            System.out.println("2: Add staff");
//                            System.out.println("3: Delete staff");
//                            System.out.println("0: back");
//                            command = scan.next();
//                            System.out.println();
//                            
//                            //View
//                            if(command.equalsIgnoreCase("1"))
//                            {
//                                
//                                
//                                    for(Staff obj: dataStaff)
//                                    {
//                                        System.out.println(obj);
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Add
//                            if(command.equalsIgnoreCase("2"))
//                            {
//                                    System.out.println("How many staff would you like to enter: ");
//                                    input = scan.nextInt();
//
//                                    for(int i=0;i<input;i++)
//                                    {
//                                        System.out.println("Enter details of staff member " + (i+1));
//                                        System.out.println("Enter name: ");
//                                        name = scan.next();
//                                        System.out.println("Enter password: ");
//                                        password = scan.next();
//                                        System.out.println("Enter staff ID: ");
//                                        ID = scan.nextInt();
//                                        dataStaff.add(new Staff(name, password, ID));
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Delete
//                            if(command.equalsIgnoreCase("3"))
//                            {
//                                    System.out.println("Enter index of the staff member for deletion: ");
//                                    input = scan.nextInt();
//                                    dataStaff.remove(input);
//                                    System.out.println();
//                            }
//                            
//
//                        }
//                        command = "";
//                    }
//                    
////==============================================================================
////==============================GAMES===========================================
////==============================================================================                    
//                    
//                    if(command.equalsIgnoreCase("3"))
//                    {
//                        command = "";
//                        while(!command.equalsIgnoreCase("0"))
//                        {
//                            System.out.println("1: View games");
//                            System.out.println("2: Add games");
//                            System.out.println("3: Delete games");
//                            System.out.println("0: back");
//                            command = scan.next();
//                            System.out.println();
//                            
//                            //View
//                            if(command.equalsIgnoreCase("1"))
//                            {
//                                    for(Games obj: dataGames)
//                                    {
//                                        System.out.println(obj);
//                                    }
//                                    System.out.println();
//                            }          
//                            
//                            //Add
//                            if(command.equalsIgnoreCase("2"))
//                            {
//                                    System.out.println("How many games would you like to enter: ");
//                                    input = scan.nextInt();
//
//                                    for(int i=0;i<input;i++)
//                                    {
//                                        System.out.println("Enter details of staff member " + (i+1));
//                                        System.out.println("Enter title: ");
//                                        title = scan.next();
//                                        System.out.println("Enter genre: ");
//                                        genre = scan.next();
//                                        System.out.println("Enter system: ");
//                                        sys = scan.next();
//                                        System.out.println("Enter condition: ");
//                                        condition = scan.next();
//                                        System.out.println("Enter price: ");
//                                        price = scan.nextInt();
//                                        dataGames.add(new Games(title, genre, sys, condition, price));
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Delete
//                            if(command.equalsIgnoreCase("3"))
//                            {
//                                    System.out.println("Enter index of the game for deletion: ");
//                                    input = scan.nextInt();
//                                    dataGames.remove(input);
//                                    System.out.println();
//                            }
//                            
//                        }
//                        command = "";
//                    }
////==============================================================================
////=============================CONSOLES=========================================
////==============================================================================
//                    if(command.equalsIgnoreCase("4"))
//                    {
//                        command = "";
//                        while(!command.equalsIgnoreCase("0"))
//                        {
//                            System.out.println("1: View consoles");
//                            System.out.println("2: Add consoles");
//                            System.out.println("3: Delete consoles");
//                            System.out.println("0: back");
//                            command = scan.next();
//                            System.out.println();
//                            
//                            
//                            //View
//                            if(command.equalsIgnoreCase("1"))
//                            {
//                                    for(Consoles obj: dataConsoles)
//                                    {
//                                        System.out.println(obj);
//                                    }
//                                    System.out.println();
//                            } 
//                            
//                            //Add
//                            if(command.equalsIgnoreCase("2"))
//                            {
//                                    System.out.println("How many consoles would you like to enter: ");
//                                    input = scan.nextInt();
//
//                                    for(int i=0;i<input;i++)
//                                    {
//                                        System.out.println("Enter details for the console" + (i+1));
//                                        System.out.println("Enter model: ");
//                                        model = scan.next();
//                                        System.out.println("Enter condition: ");
//                                        condition = scan.next();
//                                        System.out.println("Enter price: ");
//                                        price = scan.nextInt();
//                                        dataConsoles.add(new Consoles(model, condition, price));
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Delete
//                            if(command.equalsIgnoreCase("3"))
//                            {
//                                    System.out.println("Enter index of the console for deletion: ");
//                                    input = scan.nextInt();
//                                    dataConsoles.remove(input);
//                                    System.out.println();
//                            }
//                            
//                        }
//                        command = "";
//                    }
////==============================================================================
////=============================MISC. ITEMS======================================
////==============================================================================                    
//                    if(command.equalsIgnoreCase("5"))
//                    {
//                        command = "";
//                        while(!command.equalsIgnoreCase("0"))
//                        {
//                            System.out.println("1: View misc. items");
//                            System.out.println("2: Add misc. items");
//                            System.out.println("3: Delete misc. items");
//                            System.out.println("0: back");
//                            command = scan.next();
//                            System.out.println();
//                            
//                            //View
//                            if(command.equalsIgnoreCase("1"))
//                            {
//                                    for(Misc obj: dataMisc)
//                                    {
//                                        System.out.println(obj);
//                                    }
//                                    System.out.println();
//                            }                    
//                            
//                            //Add
//                            if(command.equalsIgnoreCase("2"))
//                            {
//                                    System.out.println("How many misc. items would you like to enter: ");
//                                    input = scan.nextInt();
//
//                                    for(int i=0;i<input;i++)
//                                    {
//                                        System.out.println("Enter details of misc. item " + (i+1));
//                                        System.out.println("Enter item name: ");
//                                        itemName = scan.next();
//                                        System.out.println("Enter item description: ");
//                                        itemDescription = scan.next();
//                                        System.out.println("Enter item type: ");
//                                        itemType = scan.next();
//                                        System.out.println("Enter item price: ");
//                                        price = scan.nextInt();
//                                        dataMisc.add(new Misc(itemName, itemDescription, itemType, price));
//                                    }
//                                    System.out.println();
//                            }
//                            
//                            //Delete
//                            if(command.equalsIgnoreCase("3"))
//                            {
//                                    System.out.println("Enter index of the misc. item for deletion: ");
//                                    input = scan.nextInt();
//                                    dataMisc.remove(input);
//                                    System.out.println();
//                            }
//                            
//                        }
//                        command = "";
//                    }
//
//                    
//               // }
//                command = "";
//            }while(!msg.theMessage.equalsIgnoreCase("0"));
//            
//        }
//        catch(Exception e){
//	    System.err.println("Error: " + e.getMessage());
//	    e.printStackTrace(System.err);
//	}
//    }

} //-- end class EchoThread