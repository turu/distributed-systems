package setup.exceptions;


/**
* setup/exceptions/DrawingRoomAlreadyExistsException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class DrawingRoomAlreadyExistsException extends org.omg.CORBA.UserException
{

  public DrawingRoomAlreadyExistsException ()
  {
    super(DrawingRoomAlreadyExistsExceptionHelper.id());
  } // ctor


  public DrawingRoomAlreadyExistsException (String $reason)
  {
    super(DrawingRoomAlreadyExistsExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class DrawingRoomAlreadyExistsException
