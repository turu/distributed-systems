package model.figures;

/**
* model/figures/FigureTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class FigureTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public model.figures.FigureType value = null;

  public FigureTypeHolder ()
  {
  }

  public FigureTypeHolder (model.figures.FigureType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = model.figures.FigureTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    model.figures.FigureTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return model.figures.FigureTypeHelper.type ();
  }

}
