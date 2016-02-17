package Client;

import java.util.ArrayList;
import java.util.Scanner;

import NFS.*;

/** This class provides a basic user interface for the client to interact with.
 * 
 * @author hichaels
 *
 */
public class Shell {
	
	
	Scanner shellScanner;
	String start = "------------------------------------------------\n";
	String end = "\n------------------------------------------------\n";
	
		Shell (Proxy proxy) {
			
			printMenu();
			
			/* Below is an infinite loop prompting for user input. 
			 * This is the main interaction betweeen user and the server.
			 * 
			 * 
			 * The available commands are as following:
			 * -------------------------------------------
			 * ls: Display a list of the files contained in the current directory.
			 * pwd: Display the current working path
			 * history: Display the most recent commands. This is capped to 10 commands.
			 * ps: Display the current processes running on the server
			 * mkdir: Create a new directory with
			 * cd: Change directory into a new existing directory.
			 * -------------------------------------------
			 *
			 */
			
			while(true) {
	
			    shellScanner = new Scanner(System.in);
				String input = shellScanner.nextLine();
				
				switch(input) {
					case "ls": System.out.println(start + proxy.ls() + end); break;
					case "pwd": System.out.println(start + proxy.pwd() + end); break;
					case "history": System.out.println(start + proxy.history() + end); break;
					case "ps": System.out.println(start + proxy.ps() + end); break;
					case "mkdir": System.out.println(start + proxy.mkdir("lymap") + end); break;
					case "cd": changeDirectory(proxy); break;
					case "menu": printMenu(); break;
					default: System.out.println("Error: Bad command"); break;
					
				}
			} 
		}
		
		/** Change the current directory to another existing directory. 
		 * For this to work, one first has invoke the command "ls -d", 
		 * to list available folders. Then the user may choose one the folders listed, 
		 * and finally a change of current working path will occur.
		 * @param p
		 */
		private void changeDirectory(Proxy p) {
			
			System.out.println("| Fetching possible directories from the server |");
			
		    String results = p.lsd();
		    
		    ArrayList<String> directories = new ArrayList<String>();
		    ArrayList<String> lines = new ArrayList<String>();
		    
		   char[] charArray = results.toCharArray();
		   
		   String temp = "";
		   Boolean hit = false;
		   int index = 0;
		   
		   for (index = 0; index < charArray.length; index++) {
			   
			   if(charArray[index] == 'd' && charArray[index+1] == 'r') {
				   hit = true;
			   } else {
				   
				   if(hit == true) {
					   
					   if(charArray[index] != '\n') {
						   temp += charArray[index];
					   } else {						   
						
						   // I am assuming that you have read rights on the first bit of the secrity descriptor
						   
						if(temp.charAt(0) == 'r')
							   lines.add(temp); 
						   
						   hit = false;
						   temp = "";
						   
					   }
				   }
			   }
			   
		   }
		   
		   /* 
		     
		     Please note that in order to successfully change the directory, you have to spawn
		   a custom, new process within the shell. It is not possible to change the directory of 
		   the main process which is operating the actual program, as that would be malignant.
		   
		   */
		   
		 String tempest = "";
		  
		  for (String s: lines) {
			  
			  int lastChar = s.length() - 1;
			  
			  while(!Character.isWhitespace(s.charAt(lastChar))) {
				  tempest += s.charAt(lastChar);
				  lastChar--;
			  }
			
			 String reversed = new StringBuilder(tempest).reverse().toString();
			 directories.add(reversed);
			 tempest = "";
		  }
		  
		  System.out.println("|Please choose one of the following folders|\n\n");
		  
		  
		  System.out.println("-------------------------------------------");
		  for(String s: directories) {
			  System.out.println(s);
		  }
		  System.out.println("-------------------------------------------");
		
		  Scanner input = new Scanner(System.in);  
		  String chosenFolder = input.nextLine();
		  
		  if(directories.contains(chosenFolder)) {
			  System.out.println("Commencing directory change...");
			  
			  // You need to customize the "cd" command so that it spawn a new process for the shell to operate within
			  
			  String response = p.cd(chosenFolder);
			  
			  if(response.equals("SUCCESS")) {
				  System.out.println("Succeeded in changing directory.");
			  } else if(response.equals("FAILURE")) {
				  System.out.println("Failed to change directory.");
			  }
			  
		  } else {
			  System.out.println("The specified folder does not exist.");
		  }
		  
		}
		
		/** Prints a menu containing a list of legal commands.
		 * 
		 */
		public void printMenu() {
			System.out.println("- - - - - - * |CORBA NFS| * - - - - - - -\n");
			
			System.out.println("|Commands         |        Synopsis             |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|menu             | display menu                |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|ls               | display files               |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|pwd              | display directory path      |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|history          | display history             |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|ps               | display processes           |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|cd <directory>   | change directory            |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|mkdir <directory>| create new directory        |");
			System.out.println("|-----------------|-----------------------------|");
			
			System.out.println();
		}
}
