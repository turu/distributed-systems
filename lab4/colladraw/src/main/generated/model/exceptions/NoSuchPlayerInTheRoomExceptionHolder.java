package model.exceptions;

/**
* model/exceptions/NoSuchPlayerInTheRoomExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class NoSuchPlayerInTheRoomExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public model.exceptions.NoSuchPlayerInTheRoomException value = null;

  public NoSuchPlayerInTheRoomExceptionHolder ()
  {
  }

  public NoSuchPlayerInTheRoomExceptionHolder (model.exceptions.NoSuchPlayerInTheRoomException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = model.exceptions.NoSuchPlayerInTheRoomExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    model.exceptions.NoSuchPlayerInTheRoomExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return model.exceptions.NoSuchPlayerInTheRoomExceptionHelper.type ();
  }

}
