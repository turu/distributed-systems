package edu.sr.client;

import edu.sr.generated.example1.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Client1 
{
	I1 i1 = null;
	ORB orb = null;
	
	
	// Get the reference to the object based on its IOR and narrow it appropriately
	public void base1(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CORBA.ORBPackage.InvalidName
	{
		// create and initialize the ORB
		orb = ORB.init( args, null );

		// get the Object Reference from IOR
		org.omg.CORBA.Object objRef = orb.string_to_object("IOR:000000000000001449444c3a6578616d706c65312f49313a312e3000000000010000000000000086000102000000000e31302e32322e3130382e31313900dfb800000031afabcb0000000020acfd4cdd00000001000000000000000100000008526f6f74504f410000000008000000010000000014000000000000020000000100000020000000000001000100000002050100010001002000010109000000010001010000000026000000020002");
		
		// narrow the reference
		i1 = I1Helper.narrow(objRef);
	}

	
	// Get the reference to the object using NS and narrow it appropriately
	public void base2(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CORBA.ORBPackage.InvalidName
	{
		// create and initialize the ORB
		orb = ORB.init( args, null );

		org.omg.CORBA.Object nsRef = null;
				
		// get the reference from IOR (it points to NS Object (NS is a CORBA Object, too!)

		nsRef = orb.resolve_initial_references("NameService");
		//nsRef = orb.string_to_object("corbaloc:iiop:127.0.0.1:23232");
		//nsRef = orb.string_to_object("IOR:000000000000002b494

		// narrow the reference appropriately
		NamingContextExt ncRef = NamingContextExtHelper.narrow( nsRef );

		// use the reference calling the object's operations 
		
		// get the Object Reference from NS
		org.omg.CORBA.Object objRef = ncRef.resolve_str("ala i janek");

		System.out.println("OBJ= " + objRef);
		
		// narrow the reference
		i1 = I1Helper.narrow(objRef);
	}
	

	void callI1()
	{
		StringHolder text2 = new StringHolder("bolek");
		StringHolder text3 = new StringHolder();
		S1 s1 = new S1();
		s1.a = 77;
		s1.b = 'r';

		i1.op1(123);
		
		String res = i1.op2("zenek", text2, text3, s1);

		System.out.println("I1::op2 returned: " + text2.value + " " + text3.value + " " + res);
	}
	
	
	void callFactory()
	{
		I2 i21 = i1.getI2();
		System.out.println("I2::op returned: " + i21.op());
		System.out.println("i21 = " + orb.object_to_string(i21));

		I2 i22 = i1.getI2();
		System.out.println("I2::op returned: " + i22.op());	
		if(i22._is_a("IDL:example1/I2Ext:1.0")) 
		{
			I2Ext i22e = I2ExtHelper.narrow(i1.getI2());
			System.out.println("I2Ext::opExt returned: " + i22e.opExt());
		}
		System.out.println("i22 = " + orb.object_to_string(i22));
	}
	
	
	void callDII()
	{
		org.omg.CORBA.Request r = i1._request("op1");
		r.add_in_arg().insert_long(77);
		r.set_return_type(orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short));

		r.invoke();

		if( r.env().exception() == null )
        {
		    System.out.println("I1::op1 (DII request) returned: " + r.return_value().extract_short() );  
        }
	}
	
	public static void main(String[] args) 
		throws InvalidName, NotFound, CannotProceed, org.omg.CORBA.ORBPackage.InvalidName 
	{
		Client1 c1 = new Client1();
		c1.base1(args);
		c1.callI1();
		//c1.callFactory();
		//c1.callDII();
	}

}
