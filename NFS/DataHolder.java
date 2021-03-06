package NFS;

/**
* NFS/DataHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from NFS.idl
* Tuesday, January 26, 2016 6:40:19 PM CET
*/

public final class DataHolder implements org.omg.CORBA.portable.Streamable
{
  public NFS.Data value = null;

  public DataHolder ()
  {
  }

  public DataHolder (NFS.Data initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = NFS.DataHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    NFS.DataHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return NFS.DataHelper.type ();
  }

}
