package model.drawing;

/**
* model/drawing/RotateFigureCommandHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class RotateFigureCommandHolder implements org.omg.CORBA.portable.Streamable
{
  public model.drawing.RotateFigureCommand value = null;

  public RotateFigureCommandHolder ()
  {
  }

  public RotateFigureCommandHolder (model.drawing.RotateFigureCommand initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = model.drawing.RotateFigureCommandHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    model.drawing.RotateFigureCommandHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return model.drawing.RotateFigureCommandHelper.type ();
  }

}
