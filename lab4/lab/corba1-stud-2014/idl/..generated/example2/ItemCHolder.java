package example2;

/**
* example2/ItemCHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Tuesday, April 29, 2014 12:10:44 PM CEST
*/

public final class ItemCHolder implements org.omg.CORBA.portable.Streamable
{
  public example2.ItemC value = null;

  public ItemCHolder ()
  {
  }

  public ItemCHolder (example2.ItemC initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = example2.ItemCHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    example2.ItemCHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return example2.ItemCHelper.type ();
  }

}
