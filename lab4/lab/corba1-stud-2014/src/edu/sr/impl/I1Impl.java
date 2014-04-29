package edu.sr.impl;

import edu.sr.generated.example1.S1;
import edu.sr.generated.example1.I1POA;
import edu.sr.generated.example1.I2;
import edu.sr.generated.example1.I2Helper;
import org.omg.CORBA.StringHolder;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class I1Impl extends I1POA
{

	POA poa;
	int i = 0;
	
	public I1Impl(POA poa)
	{
		this.poa = poa;
	}


	
	@Override
	public I2 getI2() 
	{
		I2ExtImpl i2Impl = new I2ExtImpl();
		
		try {
			I2 i2 = I2Helper.narrow(poa.servant_to_reference(i2Impl));
			return i2;

		//} catch (ServantNotActive e) {
			//e.printStackTrace();
		} catch (WrongPolicy e) {
			e.printStackTrace();
		//} catch (ServantAlreadyActive e) {
			//e.printStackTrace();
		//} catch (ObjectNotActive e) {
			//e.printStackTrace();
		//} catch (ObjectAlreadyActive e) {
			//e.printStackTrace();
		} catch (ServantNotActive e) {
			e.printStackTrace();
		}  
		return null;
	}



	@Override
	public short op1(int abc) {
		// TODO Auto-generated method stub
		System.out.println("OP1 " + abc);
		return 0;
	}



	@Override
	public String op2(String text, StringHolder text2, StringHolder text3,
			S1 struct1) {
		// TODO Auto-generated method stub
		return null;
	}

}
