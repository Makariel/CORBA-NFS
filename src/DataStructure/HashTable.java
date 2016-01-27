package DataStructure;

public class HashTable {

	File [] table;
	HashAlgorithm hash;
	
	public HashTable(int prime, HashAlgorithm hash) {
		table = new File[prime];
		this.hash = hash;
	}
	
	public boolean addFile(String fileName, String parentFolder, Character fileType) {
		
		int index = hash.hash(fileName);
		
		if(table[index] != null) {
			File newFile = new File(fileName, parentFolder, fileType);
			table[index] = newFile;
			return true;
		}
			
		return false;
	}
	
}
