package example2;

/**
* example2/AFactoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Tuesday, April 29, 2014 12:11:50 PM CEST
*/

public final class AFactoryHolder implements org.omg.CORBA.portable.Streamable
{
  public example2.AFactory value = null;

  public AFactoryHolder ()
  {
  }

  public AFactoryHolder (example2.AFactory initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = example2.AFactoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    example2.AFactoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return example2.AFactoryHelper.type ();
  }

}
