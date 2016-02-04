package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import DataStructure.HistoryBook;
import NFS.*;

/** This class contains the methods that the client can call on the server side. 
 * 
 * @author hichaels
 *
 */
public class Servant extends ProxyPOA {
	
	HistoryBook hb;

	public Servant() {
		hb = new HistoryBook();
	}

	@Override
	public String pwd() {
		System.out.println("Command pwd has been invoked.");
		return executeCommand("pwd");
	}

	@Override
	public String cd(String directory) {
		System.out.println("Command cd has been invoked.");
		System.out.println("Directory: [" + directory + "]");
		executeCommand("cd" + " " + directory);
		return "lol";
	}
	
	@Override
	public String ls() {
		System.out.println("Command ls has been invoked.");
		return executeCommand("ls");
	}
	
	@Override
	public String lsd() {
		System.out.println("Command ls -l has been invoked.");
		return executeCommand("ls -l");
	}
	
	@Override
	public String ps() {
		System.out.println("Command ps has been invoked.");
		return executeCommand("ps");
	}

	@Override
	public String history() {
		System.out.println("Command history has been invoked.\n");
		return hb.displayHistory();
	}

	@Override
	public String mkdir(String dirName) {
		System.out.println("Command mkdir has been invoked.");
		String cmd = "mkdir " + dirName;
		return executeCommand(cmd);
	}

	@Override
	public String mkFile(String fileName) {
		System.out.println("Command mkFile has been invoked.");
		return executeCommand("m");
	}
	
	/** This generic method will run any legal command as specified @param cmd.
	 *  The results of the execution of the commands will be returned as a concatinated String.
	 * 
	 * @param cmd The specified command
	 * @return String containing the results
	 */
	public String executeCommand(String cmd) {
		
		int runs = 0;
		String s = null;
		String results = null;
		
		try {
		
	    // Add the command to the History
			
		hb.addToHistory(cmd);
		
		Process p = Runtime.getRuntime().exec(cmd);
        
        BufferedReader stdInput = new BufferedReader(new
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
             InputStreamReader(p.getErrorStream()));

        // Read the output from the command
   
        while ((s = stdInput.readLine()) != null) {
            
        	/* If the loop has only been run once, there is no need for
        	 * concatination, as it is a indication of there being only
        	 * one line of output. If loop has been run > one iterations
        	 * there is a need for concatination. I am using simple String
        	 * concatination over returning a String arrray, or compressing
        	 * it to bytes (chars), because it is more feasible for 
        	 * network transfer.
        	*/
        	
            if(runs > 0) {
            	results = results + s + "\n";
            } else {
            	results  = s;
            }
            runs++;
        }
         
        // Read any errors from the attempted command
        
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
		}
        
		// Handle errors
		
        catch (IOException e) {
            System.out.println("[An error has occurred]");
            System.out.println("------------------------");
            e.printStackTrace();
            System.out.println("------------------------");
            System.exit(-1);
        }
		
		// Return the concatinated String
		
		return results;
	}
}
