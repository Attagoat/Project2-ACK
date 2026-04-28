import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public static void readLine(){
        Scanner readline;
        try {
            readline = new Scanner(new File("incomingProcesses.txt"));
            while(readline.hasNext()){
                String line = readline.nextLine();
                if(line.contains("#")){
                    if(line.charAt(0) == '#'){
                        continue;
                    } else {
                        String[] lineToSplit = line.split("#");
                        line = lineToSplit[0].trim();
                    } 

                }
                if(isLineValid(line)){
                    String[] splitLine = line.split(",");
                    line.charAt(0);
                }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        }
    }

    public static boolean isLineValid(String line){
        String[] splitline = line.split(",");
        if(splitline[0].charAt(0) <= 'A' && splitline[0].charAt(0) >= 'Z'){
            return false;
        }
        try{
            Integer.parseInt(splitline[1]);
            Integer.parseInt(splitline[2].trim());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
