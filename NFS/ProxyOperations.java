package NFS;


/**
* NFS/ProxyOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from NFS.idl
* Wednesday, January 27, 2016 5:31:23 PM CET
*/

public interface ProxyOperations 
{
  String pwd ();
  String ls ();
  String lsd ();
  String history ();
  String mkdir (String dirName);
  String mkFile (String fileName);
  String cd (String directory);
  String ps ();
} // interface ProxyOperations
