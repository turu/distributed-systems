package setup.exceptions;

/**
* setup/exceptions/DrawingRoomAlreadyExistsExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class DrawingRoomAlreadyExistsExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public setup.exceptions.DrawingRoomAlreadyExistsException value = null;

  public DrawingRoomAlreadyExistsExceptionHolder ()
  {
  }

  public DrawingRoomAlreadyExistsExceptionHolder (setup.exceptions.DrawingRoomAlreadyExistsException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = setup.exceptions.DrawingRoomAlreadyExistsExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    setup.exceptions.DrawingRoomAlreadyExistsExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return setup.exceptions.DrawingRoomAlreadyExistsExceptionHelper.type ();
  }

}
