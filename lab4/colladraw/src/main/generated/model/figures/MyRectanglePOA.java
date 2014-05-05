package model.figures;


/**
* model/figures/MyRectanglePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public abstract class MyRectanglePOA extends org.omg.PortableServer.Servant
 implements model.figures.MyRectangleOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getAngle", new java.lang.Integer (0));
    _methods.put ("getWidth", new java.lang.Integer (1));
    _methods.put ("getHeight", new java.lang.Integer (2));
    _methods.put ("_get_token", new java.lang.Integer (3));
    _methods.put ("getStartPoint", new java.lang.Integer (4));
    _methods.put ("getEndPoint", new java.lang.Integer (5));
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
       case 0:  // model/figures/MyRectangle/getAngle
       {
         double $result = (double)0;
         $result = this.getAngle ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 1:  // model/figures/MyRectangle/getWidth
       {
         double $result = (double)0;
         $result = this.getWidth ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 2:  // model/figures/MyRectangle/getHeight
       {
         double $result = (double)0;
         $result = this.getHeight ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 3:  // model/figures/MyFigure/_get_token
       {
         model.figures.FigureToken $result = null;
         $result = this.token ();
         out = $rh.createReply();
         model.figures.FigureTokenHelper.write (out, $result);
         break;
       }

       case 4:  // model/figures/MyFigure/getStartPoint
       {
         model.figures.Point $result = null;
         $result = this.getStartPoint ();
         out = $rh.createReply();
         model.figures.PointHelper.write (out, $result);
         break;
       }

       case 5:  // model/figures/MyFigure/getEndPoint
       {
         model.figures.Point $result = null;
         $result = this.getEndPoint ();
         out = $rh.createReply();
         model.figures.PointHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:model/figures/MyRectangle:1.0", 
    "IDL:model/figures/MyFigure:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public MyRectangle _this() 
  {
    return MyRectangleHelper.narrow(
    super._this_object());
  }

  public MyRectangle _this(org.omg.CORBA.ORB orb) 
  {
    return MyRectangleHelper.narrow(
    super._this_object(orb));
  }


} // class MyRectanglePOA