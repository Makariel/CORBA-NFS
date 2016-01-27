package Client;

import java.util.Scanner;

import NFS.*;

public class Shell {
	
	Scanner shellScanner;
	String start = "------------------------------------------------\n";
	String end = "\n------------------------------------------------\n";

		Shell (Proxy proxy) {
			
			printMenu();
				
			
			while(true) {
	
			    shellScanner = new Scanner(System.in);
				String input = shellScanner.nextLine();
				
				switch(input) {
					case "ls": System.out.println(start + proxy.ls() + end); break;
					case "pwd": System.out.println(start + proxy.pwd() + end); break;
					case "history": System.out.println(start + proxy.history() + end); break;
					case "ps": System.out.println(start + proxy.ps() + end); break;
					case "mkdir": System.out.println(start + proxy.mkdir("lymap") + end); break;
					case "cd": System.out.println(start + proxy.cd("lymap") + end); break;
					default: System.out.println("Error: Bad command"); break;
					
				}
		
			} 
			
		}
		
		public void printMenu() {
			System.out.println("- - - - - - * |CORBA NFS| * - - - - - - -\n");
			
			//Take account for amount allocated bytes to this workstation
			
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
