package model.drawing;

/**
* model/drawing/ScaleFigureCommandHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class ScaleFigureCommandHolder implements org.omg.CORBA.portable.Streamable
{
  public model.drawing.ScaleFigureCommand value = null;

  public ScaleFigureCommandHolder ()
  {
  }

  public ScaleFigureCommandHolder (model.drawing.ScaleFigureCommand initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = model.drawing.ScaleFigureCommandHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    model.drawing.ScaleFigureCommandHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return model.drawing.ScaleFigureCommandHelper.type ();
  }

}
