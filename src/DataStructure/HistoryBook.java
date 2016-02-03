package DataStructure;

import java.util.ArrayList;

/** This datastructure contains History entries.
 *  It's use is mainly to display the recent commands that 
 *  a user has supplied to the system.
 * 
 * @author hichaels
 *
 */
public class HistoryBook {

	ArrayList <History> history;
		
	public HistoryBook() {
			history = new ArrayList<History>();
	}
	
	public void addToHistory(String command) {
		
		long currentTime = System.currentTimeMillis();
		int id = history.size() + 1;
		
		History h = new History(command, currentTime, id);
		
		history.add(h);
	}
	
	/** This method will display the 10 most recent commands that have been executed by the user.
	 *  Since returning an array of strings is not feasible, i am instead concatinating strings together
	 *  to produce a full history. This is more efficient when one is to transfer the results over the network.
	 * 
	 * @return A string containing the full history
	 */
	public String displayHistory() {
		
		final int MAX_ITERATIONS = 10;
		int counter = 0;
		
		String fullHistory ="-------------------------------------------------------\n";
		
		
		for(History h: history){
			
			if(counter >= MAX_ITERATIONS) break;	
			
			fullHistory+= h.id + " | " + h.timestamp + " | " + h.command + "\n";
			
			counter++;
		}
		
		fullHistory += "\n-------------------------------------------------------";
		
		return fullHistory;
		
	}
	
}
