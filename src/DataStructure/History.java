package DataStructure;

/** History entries contains the name of the command, 
 * the time it was issued, and it's identifier.
 * 
 * @author hichaels
 *
 */
public class History {
	
	String command;
	long timestamp;
	int id;
	
	
		History(String command, long timestamp, int id) {
			this.command = command;
			this.timestamp = timestamp;
			this.id = id;
		}

}
