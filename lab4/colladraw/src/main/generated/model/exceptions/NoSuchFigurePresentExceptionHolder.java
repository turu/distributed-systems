package model.exceptions;

/**
* model/exceptions/NoSuchFigurePresentExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class NoSuchFigurePresentExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public model.exceptions.NoSuchFigurePresentException value = null;

  public NoSuchFigurePresentExceptionHolder ()
  {
  }

  public NoSuchFigurePresentExceptionHolder (model.exceptions.NoSuchFigurePresentException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = model.exceptions.NoSuchFigurePresentExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    model.exceptions.NoSuchFigurePresentExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return model.exceptions.NoSuchFigurePresentExceptionHelper.type ();
  }

}
