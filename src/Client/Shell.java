package Client;

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
		   
		    System.out.println(results);
			
		}

		/** Prints a menu containing a list of legal commands.
		 * 
		 */
		public void printMenu() {
			System.out.println("- - - - - - * |CORBA NFS| * - - - - - - -\n");
			
			System.out.println("|Commands         |        Synopsis             |");
			System.out.println("|-----------------|-----------------------------|");
			System.out.println("|ls               | show files                  |");
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
