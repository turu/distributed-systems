package model.drawing;


/**
* model/drawing/DestroyFigureCommandPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public abstract class DestroyFigureCommandPOA extends org.omg.PortableServer.Servant
 implements model.drawing.DestroyFigureCommandOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_figure", new java.lang.Integer (0));
    _methods.put ("_get_issuing", new java.lang.Integer (1));
    _methods.put ("apply", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // model/drawing/DestroyFigureCommand/_get_figure
       {
         model.figures.FigureToken $result = null;
         $result = this.figure ();
         out = $rh.createReply();
         model.figures.FigureTokenHelper.write (out, $result);
         break;
       }

       case 1:  // model/drawing/Command/_get_issuing
       {
         model.PainterToken $result = null;
         $result = this.issuing ();
         out = $rh.createReply();
         model.PainterTokenHelper.write (out, $result);
         break;
       }

       case 2:  // model/drawing/Command/apply
       {
         try {
           model.DrawingBoard board = model.DrawingBoardHelper.read (in);
           this.apply (board);
           out = $rh.createReply();
         } catch (model.exceptions.CommandExecutionException $ex) {
           out = $rh.createExceptionReply ();
           model.exceptions.CommandExecutionExceptionHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:model/drawing/DestroyFigureCommand:1.0", 
    "IDL:model/drawing/Command:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DestroyFigureCommand _this() 
  {
    return DestroyFigureCommandHelper.narrow(
    super._this_object());
  }

  public DestroyFigureCommand _this(org.omg.CORBA.ORB orb) 
  {
    return DestroyFigureCommandHelper.narrow(
    super._this_object(orb));
  }


} // class DestroyFigureCommandPOA