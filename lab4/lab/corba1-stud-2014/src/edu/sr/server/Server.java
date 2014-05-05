package edu.sr.server;

import edu.sr.impl.I1Impl;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Server {


	public Server()
	{
	}
	
	public void base1(String[] args) 
		throws InvalidName, AdapterInactive, ServantAlreadyActive, WrongPolicy, ServantNotActive, IOException
	{
		// create and initialize the ORB
		ORB orb = ORB.init( args, null );
		
		// get reference to rootpoa & activate the POAManager
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references( "RootPOA" ));

		rootpoa.the_POAManager().activate();
		
		// create servant
		I1Impl servant = new I1Impl(rootpoa);

		// register it with the POA and get the object reference
		//rootpoa.activate_object_with_id("moj_pierwszy_serwant".getBytes(), i1Impl);
		rootpoa.activate_object(servant);
		
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(servant);

		// export object reference to a file
		PrintWriter pw = new PrintWriter(new FileWriter("Test.IOR"));
		pw.print(orb.object_to_string(ref));
		pw.close();
		
		// wait for invocations from clients
		System.out.println("Entering event processing loop...");
		orb.run();
	}

	
	public void base2(String[] args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, IOException, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, ServantAlreadyActive, ObjectAlreadyActive
	{
		// create and initialize the ORB
		ORB orb = ORB.init( args, null );
		
		// get reference to rootpoa & activate the POAManager
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references( "RootPOA" ));

		rootpoa.the_POAManager().activate();
		
		// create servants
		I1Impl servant1 = new I1Impl(rootpoa);
		I1Impl servant2 = new I1Impl(rootpoa);
	
		// register them with the POA and get the object reference
		//rootpoa.activate_object_with_id("moj_pierwszy_serwant".getBytes(), i1Impl);
		rootpoa.activate_object(servant1);
		rootpoa.activate_object(servant2);
		
		org.omg.CORBA.Object ref1 = rootpoa.servant_to_reference(servant1);
		org.omg.CORBA.Object ref2 = rootpoa.servant_to_reference(servant2);

		org.omg.CORBA.Object nsRef = null;
		
		//nsRef = orb.resolve_initial_references("NameService");
//		nsRef = orb.string_to_object("corbaloc:iiop:127.0.0.1:23232");
//		nsRef = orb.string_to_object("corbaloc:iiop:149.156.97.155:23232");
        nsRef = orb.string_to_object("IOR:000000000000002b49444c3a6f6d672e6f72672f436f734e616d696e672f4e616d696e67436f6e746578744578743a312e30000000000001000000000000009e000102000000000f3139322e3136382e3235332e313100000384000000000045afabcb0000000020000f424000000001000000000000000200000008526f6f74504f41000000000d544e616d65536572766963650000000000000008000000010000000114000000000000020000000100000020000000000001000100000002050100010001002000010109000000010001010000000026000000020002");
		
		//narrow NS reference
		NamingContextExt ncRef = NamingContextExtHelper.narrow( nsRef );

		// bind the Object Reference in Naming
		NameComponent path1[] = ncRef.to_name("ala i janek1");
		NameComponent path2[] = ncRef.to_name("ala i janek2");

		ncRef.rebind(path1, ref1);
		ncRef.rebind(path2, ref2);
		
		// wait for invocations from clients
		System.out.println("Entering event processing loop...");
		orb.run();

	}
	
	public static void main(String[] args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, IOException, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed, ServantAlreadyActive, ObjectAlreadyActive 
	{
		Server s = new Server();
		s.base2(args);
	}

}
