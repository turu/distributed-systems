package setup;

/**
* setup/PainterRegistryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class PainterRegistryHolder implements org.omg.CORBA.portable.Streamable
{
  public setup.PainterRegistry value = null;

  public PainterRegistryHolder ()
  {
  }

  public PainterRegistryHolder (setup.PainterRegistry initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = setup.PainterRegistryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    setup.PainterRegistryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return setup.PainterRegistryHelper.type ();
  }

}
