package DataStructure;

import java.util.ArrayList;

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
