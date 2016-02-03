package Server;

import NFS.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * 
 * @author hichaels
 *
 */
public class Server {

	public static void main(String[] args) {

		try {

			// Create and initialize the ORB
			
			ORB orb = ORB.init(args, null);

			// Activate POAManager
			
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// Create servant
			
			Servant serv = new Servant();

			// Store the reference from the Proxy
			
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serv);
			Proxy href = ProxyHelper.narrow(ref);

			// Initialize references
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// Bind the Proxy reference
			
			String name = "Proxy";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			// Standby for interaction with clients
			
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

	}
}
