package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

import javax.swing.LayoutStyle;

import DataStructure.HistoryBook;
import NFS.*;

/** This class contains the methods that the client can call on the server side. 
 * 
 * @author hichaels
 *
 */
public class Servant extends ProxyPOA {
	
	// This is a datastructure that contains all commands that have been executed
	
	HistoryBook hb;
	
	
	int amountCalls = 0;
	String outputLog = "";

	public Servant() {
		hb = new HistoryBook();
        
	}

	@Override
	public String pwd() {
		System.out.println("Command pwd has been invoked.");
		return executeCommand("pwd");
	}

	/* 	Here you have to make sure that the correct information wrt. choice of folders
		are being specified to the client, 
		so that one cannot change into something that doesn't exist	 */
	
	@Override
	public String cd(String directory) {
		String cmd = "cd " + directory;
		return executeCommand(cmd);
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
		
		if(amountCalls > 0) {
			outputLog += " " + cmd + " ; ";
			} else {
				outputLog = cmd + " ; ";
			}
		
		/* I am utilizing ProcessBuilder to run consequetive system calls stacked together. 
		 * This way i can simulate an actual standalone process. I am using my HistoryBook
		 * datastructure to store commands, which will be written to disc as a means to
		 * restore the system state upon a spawn of a new session. */
		
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", outputLog);
		
		// Be careful here. All commands will be stacked together. You only want the last command to be run
		
		int runs = 0;
		String s = "";
		String results = "";
		
		try {
		
	    // Add the command to the History
			
		hb.addToHistory(cmd);
		
		Process p = pb.start();		
		
		amountCalls++;
		
        BufferedReader stdInput = new BufferedReader(new
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
             InputStreamReader(p.getErrorStream()));

        // Read the output from the command
   
        while ((s = stdInput.readLine()) != null) {
            
        	System.out.println("Line: [" + s + "]");
        	
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
            	results  = s + "\n";
            }
            runs++;
            
        }
        
        String[] resultsArray = results.split("\n");
        int amountLines = resultsArray.length;
        
        
        switch(cmd) {
        
    		case "pwd": 
    			System.out.println("Amount lines for pwd: " + amountLines);
    			return resultsArray[amountLines - 1];
    		
    		case "ls": 
    			System.out.println("Amount lines for ls: " + amountLines);
    			int maxLines = 7;
    			int index = (resultsArray.length) - (maxLines);
    			String finalString = "";
    			
    			while(maxLines > 0) {
    				finalString += resultsArray[index++] + "\n";
    				maxLines--;
    			}
    			
    			return finalString;
    		}
        
       
         
        // Read any errors from the attempted command
        
        while ((s = stdError.readLine()) != null) {
            System.out.println("Error: " + s);
            return "FAILURE";
        }
        
        if(cmd.startsWith("cd ")) {
        	return "SUCCESS";
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
		
		System.out.println(results);
		
		return results;
	}
}
