package edu.sr.impl;

import edu.sr.generated.example1.*;
import org.omg.CORBA.StringHolder;
import org.omg.PortableServer.POA;
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
		System.out.println("OP1 " + abc);
		return (short) abc;
	}



	@Override
	public String op2(String text, StringHolder text2, StringHolder text3,
			S1 struct1) {
        text3.value = text;
		return "ala";
	}

    @Override
    public boolean doSthRandom(S1Holder s1) {
        final S1 value = s1.value;
        if (value.a < 0) {
            return false;
        }
        value.a = value.a * 2 + value.b;
        return true;
    }

}
