package setup.exceptions;

/**
* setup/exceptions/AccessDeniedExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class AccessDeniedExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public setup.exceptions.AccessDeniedException value = null;

  public AccessDeniedExceptionHolder ()
  {
  }

  public AccessDeniedExceptionHolder (setup.exceptions.AccessDeniedException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = setup.exceptions.AccessDeniedExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    setup.exceptions.AccessDeniedExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return setup.exceptions.AccessDeniedExceptionHelper.type ();
  }

}
