public class Files extends StorageUnit {
    private String fileType = "-";

    public Files(int ownerAccess, int groupAccess, int publicAccess, String user, String groupUser, int size, String time, String name, Directory father) {
        super(ownerAccess, groupAccess, publicAccess, user, groupUser, size, time, name, father);
        
    }
    public String toString() {
        return fileType + gainAccess(getOwnerAccess()) + gainAccess(getGroupAccess()) + gainAccess(getPublicAccess()) + "\t" +
                "1" + "\t" + getUser() + "\t" + getGroupUser() + "\t" + getSize() + "\t" + getTime() + "\t" + getName();
    }
}