
public class FileSystem {
    // initialize a file system's directory
    public static Directory root = new Directory(7,7, 1,"chn","chn",1024,"2019-12-25","root",null);
    // pointer represent current directory
    public static Directory pointer;

    public FileSystem() {
    }

    
    public void createFile(String name, int size, String time, Directory currDirectory){
        if (size <= root.getSize()){
            Files file = new Files(7,6, 0,"chn","chn",size,time,name,currDirectory);
            root.setSize(root.getSize() - size);
            // Set current directory's size.
            currDirectory.setSize(size + currDirectory.getSize());
            // Print the detail of the file.
            System.out.println(file.toString());
            // Add it to memory.
            addStorageUnit(currDirectory, file);
        }else {
            System.err.println("There isn't enough memory!");
        }
    }

    
    public void createDirectory(String name, String time, Directory currDirectory){
        // When initialize a directory, set size 0;
        Directory directory = new Directory(7,6, 0,"chn","chn",0,time,name,currDirectory);
        // Print the detail of the directory.
        System.out.println(directory.toString());
        // Add it to memory.
        addStorageUnit(currDirectory, directory);
    }

    
    public void addStorageUnit(Directory currDirectory, StorageUnit storageUnit){
        // Judge the class.
        if (storageUnit instanceof Files){
            Files newFile = (Files)storageUnit;
            // Judge if there is a same one in memory.
            if (currDirectory.getSubFile().containsKey(newFile.getName())){
                System.err.println("The same file has existed!");
                return;
            }
            // Store it.
            currDirectory.getSubFile().put(newFile.getName(), newFile);
            // Set the amount.
            currDirectory.setAmountOfLink(currDirectory.getAmountOfLink() + 1);
        }else {
            Directory newDirectory = (Directory)storageUnit;
            // Judge if there is a same one in memory.
            if (currDirectory.getSubDirectory().containsKey(newDirectory.getName())){
                System.err.println("The same directory has existed!");
                return;
            }
            // Store it.
            currDirectory.getSubDirectory().put(newDirectory.getName(), newDirectory);
            // Set the amount.
            currDirectory.setAmountOfLink(currDirectory.getAmountOfLink() + 1);
        }
    }

   
    public void removeFiles(Directory currDirectory, String name){
        // Judge if exist.
        if (currDirectory.getSubFile().containsKey(name)){
            // Set the rest size of the memory.
            root.setSize(root.getSize() + currDirectory.getSubFile().get(name).getSize());
            // Set the amount.
            currDirectory.setAmountOfLink(currDirectory.getAmountOfLink() - 1);
            // Set the current directory size.
            currDirectory.setSize(currDirectory.getSize() - currDirectory.getSubFile().get(name).getSize());
            // Remove it.
            currDirectory.getSubFile().remove(name);
            System.out.println("Remove successfully!");
        }else {
            System.err.println("The file doesn't exist!");
        }
    }

   
    public void removeDirectory(Directory currDirectory, String name){
        // Judge if exist.
        if (currDirectory.getSubDirectory().containsKey(name)){
            Directory directory = currDirectory.getSubDirectory().get(name);
            // Judge if the directory is empty, if is, we remove it, or we deny.
            if (directory.getSubDirectory().isEmpty() && directory.getSubFile().isEmpty()){
                currDirectory.getSubDirectory().remove(name);
            }else {
                System.err.println("The directory is not empty, you can't remove it!");
            }
        }else {
            System.err.println("The directory doesn't exist!");
        }
    }

   
    public void enterParentDirectory(Directory currDirectory){
        if(currDirectory.getFather() == null) {
            System.err.println("No parent directory!");
        } else {
            // Back!
            pointer = currDirectory.getFather();
        }
    }

    
    public void enterChildDirectory(Directory currDirectory, String name){
        // Judge if exists.
        if (currDirectory.getSubDirectory().containsKey(name)){
            pointer = currDirectory.getSubDirectory().get(name);
        }
        else {
            System.err.println("The directory doesn't exist! ");
        }
    }

    
    public void getPath(Directory currDirectory){
        String path = "";
        // Traverse.
        while (currDirectory.getFather() != null){
            path = "/" + currDirectory.getName() + path;
            currDirectory = currDirectory.getFather();
        }
        path = "/" + currDirectory.getName() + path;
        System.out.println(path);
    }

    
    public void chMod(Directory currDirectory, String newAccess, String name){
        // Split the number.
        char[] chs = newAccess.toCharArray();
        if (currDirectory.getSubFile().containsKey(name)){
            // Set it.
            currDirectory.getSubFile().get(name).setOwnerAccess(Integer.parseInt(String.valueOf(chs[0])));
            currDirectory.getSubFile().get(name).setGroupAccess(Integer.parseInt(String.valueOf(chs[1])));
            currDirectory.getSubFile().get(name).setPublicAccess(Integer.parseInt(String.valueOf(chs[2])));
        }else if (currDirectory.getSubDirectory().containsKey(name)){
            // Set it.
            currDirectory.getSubDirectory().get(name).setOwnerAccess(Integer.parseInt(String.valueOf(chs[0])));
            currDirectory.getSubDirectory().get(name).setGroupAccess(Integer.parseInt(String.valueOf(chs[1])));
            currDirectory.getSubDirectory().get(name).setPublicAccess(Integer.parseInt(String.valueOf(chs[2])));
        }else {
            System.err.println("No such file or directory!");
        }
    }

    
    public void showFile(Directory currDirectory){
        String fileName = "";
        //for loop traverse map
        for(String key : currDirectory.getSubFile().keySet()) {
        	Files files = currDirectory.getSubFile().get(key);
        	 fileName = fileName + files.getName() + "\t";
        }
        for(String key : currDirectory.getSubDirectory().keySet()) {
        	Directory directory = currDirectory.getSubDirectory().get(key);
        	fileName = fileName + directory.getName() + "\t";
        }
        System.out.println(fileName);
    }

    
    public void showData(Directory currDirectory) {
        System.out.println("***************** < " + currDirectory.getName() + " > *****************");
       
        for(String key : currDirectory.getSubFile().keySet()) {
        	Files files = currDirectory.getSubFile().get(key);
        	System.out.println(files.toString());
            System.out.println();
        }
        for(String key : currDirectory.getSubDirectory().keySet()) {
        	Directory directory = currDirectory.getSubDirectory().get(key);
        	 System.out.println(directory.toString());
        }
        System.out.println("Disk Surplus Space: " + root.getSize());
        System.out.println();
    }
}
