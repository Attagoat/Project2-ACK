package project2;
import net.datastructures.*;
public class CPU{
    PriorityQueue<Integer,Process> processQueue;
    Process runningProcess;
    
    public CPU(LinkedQueue<Process> validProcesses){
        while(!validProcesses.isEmpty()){
            Process curProcess = validProcesses.dequeue();
            processQueue.insert(curProcess.getPriority(), curProcess);
        }
    }




    

    
}