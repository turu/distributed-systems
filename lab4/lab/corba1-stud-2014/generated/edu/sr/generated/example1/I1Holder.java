package edu.sr.generated.example1;

/**
* edu/sr/generated/example1/I1Holder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Monday, May 5, 2014 10:15:48 PM CEST
*/

public final class I1Holder implements org.omg.CORBA.portable.Streamable
{
  public edu.sr.generated.example1.I1 value = null;

  public I1Holder ()
  {
  }

  public I1Holder (edu.sr.generated.example1.I1 initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = edu.sr.generated.example1.I1Helper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    edu.sr.generated.example1.I1Helper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return edu.sr.generated.example1.I1Helper.type ();
  }

}
