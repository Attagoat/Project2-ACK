package project2;
import net.datastructures.*;
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
public class CPU{
    HeapPriorityQueue<Integer,Process> processQueue;
    Process runningProcess;
    /**
     * Constructs a CPU with valid processes and puts them in the process queue.
     * @param validProcesses - A queue of valid processes
     */
    public CPU(LinkedQueue<Process> validProcesses){ // take an input of validprocesses from the constructor
        processQueue = new HeapPriorityQueue<>(); //make a priority queue for the processes that has a key for priority and stores a process as it's value
        while(!validProcesses.isEmpty()){ // while the queue of valid processes is empty
            Process curProcess = validProcesses.dequeue(); //set the current process to the next value of validprocesses
            processQueue.insert(curProcess.getPriority(), curProcess);
        }

        
    }
    /**
     * Print the output of the CPU made by the CPU and calculate it
     * @return - String of all the CPU outputs
     */
    public String CPUOutput(){
        StringBuilder sb = new StringBuilder();
        while(!processQueue.isEmpty()){ // if the process queue is not empty keep doing this
            Process curProcess = processQueue.removeMin().getValue(); // remove the minimum process
            while(curProcess.getTicks() != 0){ //if ticks are not 0 than the current process is still running
                sb.append("CPU: Running: "); 
                sb.append(printProcess(curProcess)); // print the process using a helper method.
                sb.append("|");
                sb.append(" Waiting: ");
                curProcess.setTicks(curProcess.getTicks() - 1); // set the ticks to ticks - 1 to remove a tick value from the time
                sb.append(processQueueString()); // print the process queue as a string using a helper method
                sb.append("\n"); // make a new line for the next CPU operation
            }
                sb.append("CPU: Running: "); // works same as above but print when the cpu is empty.
                sb.append("None"); 
                sb.append(" |");
                sb.append(" Waiting: ");
                curProcess.setTicks(curProcess.getTicks() - 1);
                sb.append(processQueueString());
                sb.append("\n");
        }

        return sb.toString();
        
    }
    /**
     * 
     * Utility method returns a string of what is waiting in the process queue
     * @return - what's waitigng in the process queue.
     */
    public String processQueueString(){
        StringBuilder sb = new StringBuilder();
        sb.append("["); //start with a "[" to put in an array format."
        LinkedQueue<Process> processesToPrint = new LinkedQueue<>(); //A queue of processes for printing
        while(!processQueue.isEmpty()){ // remove from the process queue for printing 
            processesToPrint.enqueue(processQueue.removeMin().getValue()); // add to the processesToPrint queue
        }
        while(!processesToPrint.isEmpty()){ // once the process queue is empty we can empty the queue of processes to print
            Process curProcess = processesToPrint.dequeue(); // curProcess is the current process to print
            sb.append(printProcess(curProcess)); //append the process to print
            if(processesToPrint.size() >= 1){  // if the size is >=1 print a comma otherwise don't as to not leave a trailing coomma
                sb.append(", ");
            }
            processQueue.insert(curProcess.getPriority(), curProcess); // insert the process back when done with the priority as a key
    
        }
        sb.append("]");
        
        return sb.toString();
    }

    /**
     * 
     * @param process - process to print
     * @return - Process in a printed format of (Name)(P: (priority) T: (ticks))
     */
    public String printProcess(Process process){
        StringBuilder sb = new StringBuilder();
        sb.append(process.getName()); //start with the process name
        sb.append("(P:"); //prefix for the priority
        sb.append(process.getPriority()); // get the process priority
        sb.append(", T: "); //prefix for process ticks
        sb.append(process.getTicks());//print the process for ticks
        sb.append(")"); //end the printing of the process
        return sb.toString();

    }




    

    
}