package example2;


/**
* example2/ItemNotExists.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Tuesday, April 29, 2014 12:10:44 PM CEST
*/

public final class ItemNotExists extends org.omg.CORBA.UserException
{

  public ItemNotExists ()
  {
    super(ItemNotExistsHelper.id());
  } // ctor


  public ItemNotExists (String $reason)
  {
    super(ItemNotExistsHelper.id() + "  " + $reason);
  } // ctor

} // class ItemNotExists
