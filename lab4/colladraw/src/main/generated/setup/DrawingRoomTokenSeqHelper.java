package setup;


/**
* setup/DrawingRoomTokenSeqHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

abstract public class DrawingRoomTokenSeqHelper
{
  private static String  _id = "IDL:setup/DrawingRoomTokenSeq:1.0";

  public static void insert (org.omg.CORBA.Any a, model.DrawingRoomToken[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static model.DrawingRoomToken[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = model.DrawingRoomTokenHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (setup.DrawingRoomTokenSeqHelper.id (), "DrawingRoomTokenSeq", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static model.DrawingRoomToken[] read (org.omg.CORBA.portable.InputStream istream)
  {
    model.DrawingRoomToken value[] = null;
    int _len0 = istream.read_long ();
    value = new model.DrawingRoomToken[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = model.DrawingRoomTokenHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, model.DrawingRoomToken[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      model.DrawingRoomTokenHelper.write (ostream, value[_i0]);
  }

}