import java.util.HashMap;
import java.util.Map;


public class Directory extends StorageUnit {
    private String directoryType;
    private int amountOfLink;
    //use hashmap to save can form a tree without the limits of the number of directories and files
    private Map<String, Files> subFile;
    private Map<String, Directory> subDirectory;

    public Directory(int ownerAccess, int groupAccess, int publicAccess, String user, String groupUser, int size, String time, String name, Directory father) {
        super(ownerAccess, groupAccess, publicAccess, user, groupUser, size, time, name, father);
        //d:directory,-:file
        this.directoryType = "d";
        this.amountOfLink = 2;
        this.subFile = new HashMap<>();
        this.subDirectory = new HashMap<>() ;
    }

    public Map<String, Files> getSubFile() {
        return subFile;
    }

    public void setSubFile(Map<String, Files> subFile) {
        this.subFile = subFile;
    }

    public Map<String, Directory> getSubDirectory() {
        return subDirectory;
    }

    public void setSubDirectory(Map<String, Directory> subDirectory) {
        this.subDirectory = subDirectory;
    }

    public int getAmountOfLink() {
        return amountOfLink;
    }

    public void setAmountOfLink(int amountOfLink) {
        this.amountOfLink = amountOfLink;
    }

    public String toString() {
        return directoryType + gainAccess(getOwnerAccess()) + gainAccess(getGroupAccess()) + gainAccess(getPublicAccess()) + "\t" +
                getAmountOfLink() + "\t" + getUser() + "\t" + getGroupUser() + "\t" + getSize() + "\t" + getTime() + "\t" + getName();
    }
}
