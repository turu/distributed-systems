package example2;

/**
* example2/ItemAHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Tuesday, April 29, 2014 12:10:44 PM CEST
*/

public final class ItemAHolder implements org.omg.CORBA.portable.Streamable
{
  public example2.ItemA value = null;

  public ItemAHolder ()
  {
  }

  public ItemAHolder (example2.ItemA initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = example2.ItemAHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    example2.ItemAHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return example2.ItemAHelper.type ();
  }

}