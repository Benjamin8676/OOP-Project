package gamestore;
import java.util.ArrayList;
import java.util.Scanner;


public class Store {

    public static void main(String[] args) {
        int t = 1;

        
       
        
        Scanner scan = new Scanner(System.in);
        ArrayList<Members> dataMembers = new ArrayList<>();
        
        String name, password;
        int ID, input;
        
        do{
        
        System.out.println("How many people would you like to enter: ");
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
        
        for(Members object : dataMembers)
        {        
            System.out.println("Member: " + object.getUsername());
            System.out.println("Password: " + object.getPassword());         
        }

        }while(t==1);
        
    }
}






//    public static String UserCheck(String Email) 
//        {
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
//    public static String PassCheck (String Password) 
//        {
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
//
//    }
    





//        ArrayList<Member> dataMember = new ArrayList<>();
//        String defaultNames[] = new String[]{"Ben", "Rebecca", "Alex", "Jon", "Omar"};
//        String defaultPasswords[] = new String[]{"pass01", "pass02", "pass03", "pass04", "pass05", };
//        Member defaultMembers[] = new Member[5];
//        Scanner input = new Scanner(System.in);


//
//        
//        
//        for(int i = 1; i < defaultNames.length; i++)
//        {
//            defaultMembers[i] = new Member(defaultNames[i], defaultPasswords[i]);
//            dataMember.add(defaultMembers[i]);
//        }
//        
//        while (!commandMenu.equalsIgnoreCase("0"))  
//        {
//            System.out.println("\nEnter command");
//            System.out.println("1: Current users.");
//            System.out.println("2: Staff");
//            System.out.println("0: Quit");
//            commandMenu = input.next();
//            System.out.println();
//            
//            
//            //DISPLY FOR CURRENT USERS
//            if(commandMenu.equalsIgnoreCase("1"))
//            {
//                {
//                    System.out.println("===Current Users===");
//                    for(int x = 0; x < defaultNames.length; x ++)  
//                    {  
//                        System.out.println(defaultNames[x] + " " +  defaultPasswords[x]);
//                    }
//                }
//            }
//            //END OF DISPLAY FOR CURRENT USERS

    	//if your implementing the runnable interface you can extend the methods of that object