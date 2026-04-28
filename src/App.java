import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import net.datastructures.*;
import project2.Process;
import project2.CPU;
/**
 * Name: Atticus Kaphaem
 * Date: 4/28/2026
 * Description: Project 2
 * Known bugs: None
 * Reflection: this project was a pretty fun way to see how the CPU worked and I didn't struggle much with it. There was one point where
 * I got stuck and it was because i wasn't decrementing the ticks and i figured that out using the debugger. I did not use any
 * LLMs while programming this at all. Overall I found this to be quite a trivial task for someone with my experience but someone
 * with less experience programming might find it a little more tricky.
 */
public class App {
    public static void main(String[] args) throws Exception {
        readLine();
    }
    /**
     * Reads a line and see if it validates a process
     */
    public static void readLine(){ 
        LinkedQueue<Process> processesToAdd = new LinkedQueue<>(); // a queue of valid processes to add
        try {
           Scanner readline = new Scanner(new File("incomingProcesses.txt"));
            while(readline.hasNext()){ // whiel readline has next
                String line = readline.nextLine();
                if(line.contains("#")){ // if it contains an # there is a comment in the line somewhere
                    if(line.charAt(0) == '#'){ // if it's the first character go to the next line
                        continue;
                    } else { // otherwise split it  from the # and read the left part in lineToSplit[0]
                        String[] lineToSplit = line.split("#");
                        line = lineToSplit[0].trim(); //trim trailing whitespace
                    } 

                }
                if(isLineValid(line)){
                    String[] splitLine = line.split(","); //since it's a valid process split the line using the , regex for each value
                    splitLine[2] = splitLine[2].trim();// trim trailing whitespace from the last value
                    // construct a process with the first line being the name, the second value being the priority and the third being the number
                    Process curProcess = new Process(splitLine[0].charAt(0),Integer.parseInt(splitLine[1]),Integer.parseInt(splitLine[2]));
                    processesToAdd.enqueue(curProcess); //enqueue the process since it's valid to the process queue
                    
                    
                }
            }
            readline.close();
            runCPU(processesToAdd);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        
    }
    /**
     * Runs the cpu with all the process
     * @param processesToRun - A queue of valid processes to run.
     */
    public static void runCPU(LinkedQueue<Process> processesToRun){
        CPU cpu = new CPU(processesToRun); // constructs the cpu with valid processes
        System.out.println(cpu.CPUOutput()); //outputs the CPU output using the methods inside CPU class.
        
    }
    /**
     * sees if a line is valid
     * @param line - line to see if it's valid
     * @return- true if valid false if not
     */
    public static boolean isLineValid(String line){
        final int MIN_VALUE = -19; //minimum value for a process
        final int MAX_VALUE = 25; // maximum value for a process
        String[] splitline = line.split(","); // split the line  using commas to validate it
        if(splitline[0].charAt(0) <= 'A' && splitline[0].charAt(0) >= 'Z'){ // if it is not a character A-Z by using their ascii values to determine that return false
            return false;
        }
        try{ // this is put in a try catch just in case the parts that need to have integers don't have integers in them
            int priority = Integer.parseInt(splitline[1]);// 
            if(priority < MIN_VALUE || priority > MAX_VALUE){ //if the priority is below the min or above the max it's not valid
                return false;
            }
            Integer.parseInt(splitline[2].trim()); // this line makes sure that the ticks are a valid integer. 
            return true;
        } catch (NumberFormatException e){ //if there's an exception for when the integers are caught  return false.
            return false;
        }
    }
}
