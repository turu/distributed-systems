package model.exceptions;


/**
* model/exceptions/NoSuchFigurePresentException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class NoSuchFigurePresentException extends org.omg.CORBA.UserException
{

  public NoSuchFigurePresentException ()
  {
    super(NoSuchFigurePresentExceptionHelper.id());
  } // ctor


  public NoSuchFigurePresentException (String $reason)
  {
    super(NoSuchFigurePresentExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class NoSuchFigurePresentException
