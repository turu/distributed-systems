package example2;

/**
* example2/ItemNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Tuesday, April 29, 2014 12:11:50 PM CEST
*/

public final class ItemNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public example2.ItemNotExists value = null;

  public ItemNotExistsHolder ()
  {
  }

  public ItemNotExistsHolder (example2.ItemNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = example2.ItemNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    example2.ItemNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return example2.ItemNotExistsHelper.type ();
  }

}
