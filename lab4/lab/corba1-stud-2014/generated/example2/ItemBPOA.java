package example2;


/**
* example2/ItemBPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sr.idl
* Monday, May 5, 2014 10:15:48 PM CEST
*/

public abstract class ItemBPOA extends org.omg.PortableServer.Servant
 implements example2.ItemBOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("actionB", new java.lang.Integer (0));
    _methods.put ("_get_name", new java.lang.Integer (1));
    _methods.put ("get_item_age", new java.lang.Integer (2));
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
       case 0:  // example2/ItemB/actionB
       {
         String a = in.read_string ();
         float $result = (float)0;
         $result = this.actionB (a);
         out = $rh.createReply();
         out.write_float ($result);
         break;
       }

       case 1:  // example2/Item/_get_name
       {
         String $result = null;
         $result = this.name ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // example2/Item/get_item_age
       {
         int $result = (int)0;
         $result = this.get_item_age ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:example2/ItemB:1.0", 
    "IDL:example2/Item:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ItemB _this() 
  {
    return ItemBHelper.narrow(
    super._this_object());
  }

  public ItemB _this(org.omg.CORBA.ORB orb) 
  {
    return ItemBHelper.narrow(
    super._this_object(orb));
  }


} // class ItemBPOA
