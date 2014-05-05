package setup;


/**
* setup/_PainterRegistryStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from api.idl
* Tuesday, May 6, 2014 12:57:50 AM CEST
*/

public class _PainterRegistryStub extends org.omg.CORBA.portable.ObjectImpl implements setup.PainterRegistry
{

  public model.PainterToken[] getRegisteredPainters ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRegisteredPainters", true);
                $in = _invoke ($out);
                model.PainterToken $result[] = setup.PainterTokenSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRegisteredPainters (        );
            } finally {
                _releaseReply ($in);
            }
  } // getRegisteredPainters

  public model.PainterToken registerPainter (String name) throws setup.exceptions.PlayerAlreadyRegisteredException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerPainter", true);
                $out.write_string (name);
                $in = _invoke ($out);
                model.PainterToken $result = model.PainterTokenHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:setup/exceptions/PlayerAlreadyRegisteredException:1.0"))
                    throw setup.exceptions.PlayerAlreadyRegisteredExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return registerPainter (name        );
            } finally {
                _releaseReply ($in);
            }
  } // registerPainter

  public void unregisterPainter (model.PainterToken painterToken) throws setup.exceptions.NoSuchPainterRegisteredException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("unregisterPainter", true);
                model.PainterTokenHelper.write ($out, painterToken);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:setup/exceptions/NoSuchPainterRegisteredException:1.0"))
                    throw setup.exceptions.NoSuchPainterRegisteredExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                unregisterPainter (painterToken        );
            } finally {
                _releaseReply ($in);
            }
  } // unregisterPainter

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:setup/PainterRegistry:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _PainterRegistryStub