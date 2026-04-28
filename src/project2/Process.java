package project2;
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
public class Process{
    char name;
    int priority;
    int ticks;
    /**
     * Consturctor for elements of a process
     * @param name - A character A-Z of a name for the process
     * @param priority - the priority of the process
     * @param ticks - how long the process takes to run
     */
    public Process(char name, int priority, int ticks){
        this.name = name;
        this.priority = priority;
        this.ticks = ticks;
    }
    /**
     * Setter method for the ticks
     * @param ticks - number of ticks in a process
     */
    public void setTicks(int ticks){
        this.ticks = ticks;
    }
    /**
     * Getter method for priority
     * @return - the priority of the process
     */
    public int getPriority(){
        return priority;
    }
    /**
     * the number of ticks a process takes to run
     * @return - the current number of ticks the process is running on.
     */
    public int getTicks(){
        return ticks;
    }
    /**
     * Getter method for the name of the process
     * @return - the name of the process
     */
    public char getName(){
        return name;
    }
}