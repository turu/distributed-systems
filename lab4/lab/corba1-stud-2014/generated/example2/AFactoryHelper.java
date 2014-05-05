package example2;


/**
* example2/AFactoryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Monday, May 5, 2014 10:15:48 PM CEST
*/

abstract public class AFactoryHelper
{
  private static String  _id = "IDL:example2/AFactory:1.0";

  public static void insert (org.omg.CORBA.Any a, example2.AFactory that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static example2.AFactory extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (example2.AFactoryHelper.id (), "AFactory");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static example2.AFactory read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AFactoryStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, example2.AFactory value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static example2.AFactory narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof example2.AFactory)
      return (example2.AFactory)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      example2._AFactoryStub stub = new example2._AFactoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static example2.AFactory unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof example2.AFactory)
      return (example2.AFactory)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      example2._AFactoryStub stub = new example2._AFactoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}