package NFS;

/**
* NFS/ProxyHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from NFS.idl
* Tuesday, January 26, 2016 6:47:22 PM CET
*/

public final class ProxyHolder implements org.omg.CORBA.portable.Streamable
{
  public NFS.Proxy value = null;

  public ProxyHolder ()
  {
  }

  public ProxyHolder (NFS.Proxy initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = NFS.ProxyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    NFS.ProxyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return NFS.ProxyHelper.type ();
  }

}