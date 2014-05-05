package example2;


/**
* example2/ItemHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Monday, May 5, 2014 10:15:48 PM CEST
*/

abstract public class ItemHelper
{
  private static String  _id = "IDL:example2/Item:1.0";

  public static void insert (org.omg.CORBA.Any a, example2.Item that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static example2.Item extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (example2.ItemHelper.id (), "Item");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static example2.Item read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ItemStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, example2.Item value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static example2.Item narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof example2.Item)
      return (example2.Item)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      example2._ItemStub stub = new example2._ItemStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static example2.Item unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof example2.Item)
      return (example2.Item)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      example2._ItemStub stub = new example2._ItemStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
