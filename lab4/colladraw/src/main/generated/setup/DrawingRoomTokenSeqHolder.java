package setup;


/**
* setup/DrawingRoomTokenSeqHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class DrawingRoomTokenSeqHolder implements org.omg.CORBA.portable.Streamable
{
  public model.DrawingRoomToken value[] = null;

  public DrawingRoomTokenSeqHolder ()
  {
  }

  public DrawingRoomTokenSeqHolder (model.DrawingRoomToken[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = setup.DrawingRoomTokenSeqHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    setup.DrawingRoomTokenSeqHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return setup.DrawingRoomTokenSeqHelper.type ();
  }

}