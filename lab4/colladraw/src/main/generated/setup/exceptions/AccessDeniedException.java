package setup.exceptions;


/**
* setup/exceptions/AccessDeniedException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public final class AccessDeniedException extends org.omg.CORBA.UserException
{

  public AccessDeniedException ()
  {
    super(AccessDeniedExceptionHelper.id());
  } // ctor


  public AccessDeniedException (String $reason)
  {
    super(AccessDeniedExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class AccessDeniedException
