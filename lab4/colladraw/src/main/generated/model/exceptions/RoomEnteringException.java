package model.exceptions;


/**
* model/exceptions/RoomEnteringException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class RoomEnteringException extends org.omg.CORBA.UserException
{

  public RoomEnteringException ()
  {
    super(RoomEnteringExceptionHelper.id());
  } // ctor


  public RoomEnteringException (String $reason)
  {
    super(RoomEnteringExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class RoomEnteringException
