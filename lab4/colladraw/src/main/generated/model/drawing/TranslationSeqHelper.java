package model.drawing;


/**
* model/drawing/TranslationSeqHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

abstract public class TranslationSeqHelper
{
  private static String  _id = "IDL:model/drawing/TranslationSeq:1.0";

  public static void insert (org.omg.CORBA.Any a, model.figures.Point[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static model.figures.Point[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = model.figures.PointHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (model.drawing.TranslationSeqHelper.id (), "TranslationSeq", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static model.figures.Point[] read (org.omg.CORBA.portable.InputStream istream)
  {
    model.figures.Point value[] = null;
    int _len0 = istream.read_long ();
    value = new model.figures.Point[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = model.figures.PointHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, model.figures.Point[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      model.figures.PointHelper.write (ostream, value[_i0]);
  }

}
