package Client;

import NFS.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/**
 * 
 * @author hichaels
 *
 */
public class Client {

	private static Proxy proxy;

	public static void main(String[] args) {
		
		/* Remember to start he daemons in the terminal with the following commands:
		"orbd -ORBInitialPort <port>" */
	
		try {

			// Initialize the ORB broker
			
			ORB orb = ORB.init(args, null);

			// Bind the references so that the system recognizes the interfaces as specified
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "Proxy";
			
			// Bind the helper to the Proxy interface
			
			proxy = ProxyHelper.narrow(ncRef.resolve_str(name));
			
			// Launch the shell
			
			new Shell(proxy);

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}
}
