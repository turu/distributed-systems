package example2;

/**
* example2/ItemBusyHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Monday, May 5, 2014 10:15:48 PM CEST
*/

public final class ItemBusyHolder implements org.omg.CORBA.portable.Streamable
{
  public example2.ItemBusy value = null;

  public ItemBusyHolder ()
  {
  }

  public ItemBusyHolder (example2.ItemBusy initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = example2.ItemBusyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    example2.ItemBusyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return example2.ItemBusyHelper.type ();
  }

}