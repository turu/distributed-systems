package edu.sr.generated.example1;


/**
* edu/sr/generated/example1/_I1Stub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Tuesday, April 29, 2014 12:10:44 PM CEST
*/

public class _I1Stub extends org.omg.CORBA.portable.ObjectImpl implements edu.sr.generated.example1.I1
{

  public short op1 (int abc)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("op1", true);
                $out.write_long (abc);
                $in = _invoke ($out);
                short $result = $in.read_short ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return op1 (abc        );
            } finally {
                _releaseReply ($in);
            }
  } // op1

  public String op2 (String text, org.omg.CORBA.StringHolder text2, org.omg.CORBA.StringHolder text3, edu.sr.generated.example1.S1 struct1)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("op2", true);
                $out.write_string (text);
                $out.write_string (text2.value);
                edu.sr.generated.example1.S1Helper.write ($out, struct1);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                text2.value = $in.read_string ();
                text3.value = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return op2 (text, text2, text3, struct1        );
            } finally {
                _releaseReply ($in);
            }
  } // op2

  public edu.sr.generated.example1.I2 getI2 ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getI2", true);
                $in = _invoke ($out);
                edu.sr.generated.example1.I2 $result = edu.sr.generated.example1.I2Helper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getI2 (        );
            } finally {
                _releaseReply ($in);
            }
  } // getI2

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:example1/I1:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _I1Stub
