package Client;

import NFS.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {

	private static Proxy proxy;

	public static void main(String[] args) {
		
		/* Remember to start he daemons in the terminal with the following commands:
		"orbd -ORBInitialPort <port>" */
	
		try {

			ORB orb = ORB.init(args, null);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "Proxy";
			proxy = ProxyHelper.narrow(ncRef.resolve_str(name));
			
			new Shell(proxy);

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}
}
