package DataStructure;

public class SimpleHash implements HashAlgorithm {

	@Override
	public int hash(String fileName) {
		
		int hashValue = 0;
		
		for(char c: fileName.toCharArray()) {
			hashValue += Character.valueOf(c);
		}
		
		return hashValue;
	}

}
