package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import DataStructure.HistoryBook;
import NFS.*;

/** This class contains the methods that the client can call. 
 *  Before the server computes and returns the results of the
 *  client call the server will produce a 60 ms delay to 
 *  simulate network latency.
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
		return executeCommand("cd" + " " + directory);
	}
	
	@Override
	public String ls() {
		System.out.println("Command ls has been invoked.");
		return executeCommand("ls");
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
	
	public String executeCommand(String cmd) {
		
		int runs = 0;
		String s = null;
		String results = null;
		
		try {
			
		hb.addToHistory(cmd);
		
		
		
		Process p = Runtime.getRuntime().exec(cmd);
        
        BufferedReader stdInput = new BufferedReader(new
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
             InputStreamReader(p.getErrorStream()));

        // Read the output from the command
   
        while ((s = stdInput.readLine()) != null) {
        	
           // System.out.println(s);
            
            if(runs > 0) {
            	results = results + "\n" + s;
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
        
        catch (IOException e) {
            System.out.println("[An error has occurred]");
            System.out.println("------------------------");
            e.printStackTrace();
            System.out.println("------------------------");
            System.exit(-1);
        }
		
		return results;
	}
}
