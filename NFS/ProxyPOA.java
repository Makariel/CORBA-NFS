package NFS;


/**
* NFS/ProxyPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from NFS.idl
* Tuesday, January 26, 2016 6:47:22 PM CET
*/

public abstract class ProxyPOA extends org.omg.PortableServer.Servant
 implements NFS.ProxyOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("pwd", new java.lang.Integer (0));
    _methods.put ("ls", new java.lang.Integer (1));
    _methods.put ("history", new java.lang.Integer (2));
    _methods.put ("mkdir", new java.lang.Integer (3));
    _methods.put ("mkFile", new java.lang.Integer (4));
    _methods.put ("cd", new java.lang.Integer (5));
    _methods.put ("ps", new java.lang.Integer (6));
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
       case 0:  // NFS/Proxy/pwd
       {
         String $result = null;
         $result = this.pwd ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // NFS/Proxy/ls
       {
         String $result = null;
         $result = this.ls ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // NFS/Proxy/history
       {
         String $result = null;
         $result = this.history ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // NFS/Proxy/mkdir
       {
         String dirName = in.read_string ();
         String $result = null;
         $result = this.mkdir (dirName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // NFS/Proxy/mkFile
       {
         String fileName = in.read_string ();
         String $result = null;
         $result = this.mkFile (fileName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // NFS/Proxy/cd
       {
         String directory = in.read_string ();
         String $result = null;
         $result = this.cd (directory);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 6:  // NFS/Proxy/ps
       {
         String $result = null;
         $result = this.ps ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:NFS/Proxy:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Proxy _this() 
  {
    return ProxyHelper.narrow(
    super._this_object());
  }

  public Proxy _this(org.omg.CORBA.ORB orb) 
  {
    return ProxyHelper.narrow(
    super._this_object(orb));
  }


} // class ProxyPOA
