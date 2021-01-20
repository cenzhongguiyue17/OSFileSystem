public class StorageUnit {
    private int ownerAccess;
    private int groupAccess;
    private int publicAccess;
    private String user;
    private String groupUser;
    private int size;
    private String time;
    private String name;
    private Directory father = null;

    public StorageUnit(int ownerAccess, int groupAccess, int publicAccess, String user, String groupUser, int size, String time, String name, Directory father) {
        this.ownerAccess = ownerAccess;
        this.groupAccess = groupAccess;
        this.publicAccess = publicAccess;
        this.user = user;
        this.groupUser = groupUser;
        this.size = size;
        this.time = time;
        this.name = name;
        this.father = father;
    }

    public int getOwnerAccess() {
        return ownerAccess;
    }

    public void setOwnerAccess(int ownerAccess) {
        this.ownerAccess = ownerAccess;
    }

    public int getGroupAccess() {
        return groupAccess;
    }

    public void setGroupAccess(int groupAccess) {
        this.groupAccess = groupAccess;
    }

    public int getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(int publicAccess) {
        this.publicAccess = publicAccess;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(String groupUser) {
        this.groupUser = groupUser;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getFather() {
        return father;
    }

    public void setFather(Directory father) {
        this.father = father;
    }

    public String gainAccess(int accessNum){
        String access = null;
        switch (accessNum){
            case 7:{
                access = "rwx";break;
            }
            case 6:{
            	access = "rw-";break;
            }
            case 5:{
            	access = "r-x";break;
            }
            case 4:{
            	access = "r--";break;
            }
            case 3:{
            	access = "-wx";break;
            }
            case 2:{
            	access = "-w-";break;
            }
            case 1:{
            	access = "--x";break;
            }
            case 0:{
            	access = "---";break;
            }
        }
        return access;
    }
}