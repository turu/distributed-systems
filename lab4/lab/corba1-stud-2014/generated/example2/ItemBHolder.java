package example2;

/**
* example2/ItemBHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Monday, May 5, 2014 10:15:48 PM CEST
*/

public final class ItemBHolder implements org.omg.CORBA.portable.Streamable
{
  public example2.ItemB value = null;

  public ItemBHolder ()
  {
  }

  public ItemBHolder (example2.ItemB initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = example2.ItemBHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    example2.ItemBHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return example2.ItemBHelper.type ();
  }

}
