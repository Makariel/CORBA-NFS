package DataStructure;

/**  This class will be used in conjunction with Backup class.
 * 
 *  * @author hichaels
 *
 */
public class File {

	String fileName;
	String parentFolder;
	Character fileType;
	
		File(String fileName, String parentFolder, Character fileType) {
			this.fileName = fileName;
			this.parentFolder = parentFolder;
			this.fileType = fileType;
		}
	
}
