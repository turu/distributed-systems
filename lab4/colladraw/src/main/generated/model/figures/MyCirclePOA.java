package model.figures;


/**
* model/figures/MyCirclePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public abstract class MyCirclePOA extends org.omg.PortableServer.Servant
 implements model.figures.MyCircleOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getRadius", new java.lang.Integer (0));
    _methods.put ("_get_token", new java.lang.Integer (1));
    _methods.put ("getStartPoint", new java.lang.Integer (2));
    _methods.put ("getEndPoint", new java.lang.Integer (3));
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
       case 0:  // model/figures/MyCircle/getRadius
       {
         double $result = (double)0;
         $result = this.getRadius ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 1:  // model/figures/MyFigure/_get_token
       {
         model.figures.FigureToken $result = null;
         $result = this.token ();
         out = $rh.createReply();
         model.figures.FigureTokenHelper.write (out, $result);
         break;
       }

       case 2:  // model/figures/MyFigure/getStartPoint
       {
         model.figures.Point $result = null;
         $result = this.getStartPoint ();
         out = $rh.createReply();
         model.figures.PointHelper.write (out, $result);
         break;
       }

       case 3:  // model/figures/MyFigure/getEndPoint
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
    "IDL:model/figures/MyCircle:1.0", 
    "IDL:model/figures/MyFigure:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public MyCircle _this() 
  {
    return MyCircleHelper.narrow(
    super._this_object());
  }

  public MyCircle _this(org.omg.CORBA.ORB orb) 
  {
    return MyCircleHelper.narrow(
    super._this_object(orb));
  }


} // class MyCirclePOA
