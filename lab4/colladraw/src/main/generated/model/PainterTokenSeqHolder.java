package model;


/**
* model/PainterTokenSeqHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class PainterTokenSeqHolder implements org.omg.CORBA.portable.Streamable
{
  public model.PainterToken value[] = null;

  public PainterTokenSeqHolder ()
  {
  }

  public PainterTokenSeqHolder (model.PainterToken[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = model.PainterTokenSeqHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    model.PainterTokenSeqHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return model.PainterTokenSeqHelper.type ();
  }

}
