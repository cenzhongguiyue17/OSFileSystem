import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * At this test,you have access to all commands
 */
public class TestFileSystem {
    public static void main(String[] args) {
        try{
            // Begin!
            FileSystem fileSystem = new FileSystem();
            fileSystem.pointer = fileSystem.root;
            UI(fileSystem);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void UI(FileSystem fileSystem) {
        Scanner input = new Scanner(System.in);
        String str = null;
        System.out.println("***********" + "Welcome to use this file system" + "***********");
       
        fileSystem.showData(fileSystem.root);
        System.out.println("you can hava these operations:" + "\n" + "pwd,cd,cd..,ls,ls -l,mkdir,rmdir,mk,rm,chmod");
        System.out.println("Please enter your command:");
        //while loop
        while ((str = input.nextLine()) != null) {
            if (str.equals("exit")) {
                System.out.println("Thank you£¡");
                break;
            }
            String[] strs = str.split(" ");
            //the first element of string array can be used to judge command type
            switch (strs[0]) {
                case "pwd":{
                	//use length to judge whether the command is valid
                    if (strs.length > 2){
                        System.out.println("Invalid command. Please check your commmand.");
                    }else {
                        fileSystem.getPath(fileSystem.pointer);
                    }
                    break;
                }
                case "cd":{
                    if (strs.length < 2) {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    else {
                        fileSystem.enterChildDirectory(fileSystem.pointer, strs[1]);
                    }
                    break;
                }
                case "cd..":{
                    if (strs.length > 2){
                        System.out.println("Invalid command. Please check your commmand.");
                    }else {
                        fileSystem.enterParentDirectory(fileSystem.pointer);
                    }
                    break;
                }
                case "ls":{
                    if (strs.length <= 1){
                        fileSystem.showFile(fileSystem.pointer);
                    }
                    //ls -l
                    else if (strs[1].equals("-l")){
                        fileSystem.showData(fileSystem.pointer);
                    }else {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    break;
                }
                case "mkdir":{
                    if (strs.length < 2) {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy h:m:s aa", Locale.ENGLISH);
                        Date time = new Date();
                        fileSystem.createDirectory(strs[1], dateFormat.format(time), fileSystem.pointer);
                    }
                    break;
                }
                case "rmdir":{
                    if (strs.length < 2) {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    else {
                        fileSystem.removeDirectory(fileSystem.pointer, strs[1]);
                    }
                    break;
                }

                case "mk":{
                    if (strs.length < 2) {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy h:m:s aa", Locale.ENGLISH);
                        Date time = new Date();
                        fileSystem.createFile(strs[1],256, dateFormat.format(time), fileSystem.pointer);
                    }
                    break;
                }
                case "rm":{
                    if (strs.length < 2) {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    else {
                        fileSystem.removeFiles(fileSystem.pointer, strs[1]);
                    }
                    break;
                }
                case "chmod":{
                    if (strs.length < 3) {
                        System.out.println("Invalid command. Please check your commmand.");
                    }
                    else {
                        fileSystem.chMod(fileSystem.pointer, strs[1], strs[2]);
                    }
                    break;
                }
                default:
                    for(String st : strs)
                        System.out.println(st);
                    System.out.println("Invalid command. Please check your commmand.");
            }
        }
    }

}
