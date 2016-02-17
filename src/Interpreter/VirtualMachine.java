/* Description: Walks through the program and executes the bytecode that is loaded in to 
 * the program one by one. It keeps track of the current position in 
 * the program; also, it holds a reference to the runtime stack.
 * Author: Poushali Banerjee  
 * Last Update: May 5, 2010
 */

package Interpreter;

import Interpreter.ByteCode.ByteCode;
import java.util.*;




public class VirtualMachine {
    
    protected RunTimeStack runStack; //VM owns the runtime stack    
    protected int pc; //program counter
    protected Stack returnAddrs; //push-pop when call-return functions
    protected boolean isRunning; //true while the VM is running
    protected Program program; //bytecode program
    private Boolean dumpSwitch = false; 
    
    
    public VirtualMachine(){}; //default constructor

    public VirtualMachine(Program p){
        program = p; 
    }
    
    public void executeProgram(){
        pc = 0; 
        runStack = new RunTimeStack(); 
        returnAddrs = new Stack(); 
        returnAddrs.push(0); 
        isRunning = true;
        while(isRunning){
            ByteCode code = program.getCode(pc); 
            code.execute(this); //calls the bytecode's execute method 
            pc++;
        }//end while loop
    }
    
    public void setRunningFalse(){
        isRunning = false; 
    }
    
    public int getRunStackSize() {
        return runStack.getVectorSize();
    }
    
    public void turnDumpOn(){
        dumpSwitch = true; 
    }
    
    public void turnDumpOff(){
        dumpSwitch = false; 
    }
    
    public boolean isDumpOn(){
        return dumpSwitch; 
    }
    
    public void dumpRunStack(){
        runStack.dump(); 
    }
    
    public int peekRunStack(){
        return runStack.peek(); 
    }
    
    public int popRunStack(){
        return runStack.pop(); 
    }
    
    public int pushRunStack(int i){
        return runStack.push(i); 
    }
    
    public Integer pushRunStack(Integer i){
        return runStack.push(i); 
    }
    
    public void newFramePointer(int offset){
        runStack.newFrameAt(offset);
    }
    
    public void popRunStackFrame(){
        runStack.popFrame();
    }
    
    public int storeRunStack(int offset){
        return runStack.store(offset); 
    }
    
    public int loadRunStack(int offset){
        return runStack.load(offset);
    }
    
    public void setPC(int i){
        pc = i; 
    }
    
    public int getPC(){
        return pc; 
    }
    
    public void addReturnAddrs(int i){
        returnAddrs.push(i); 
    }
    
    public int getReturnAddrs(){
        int i = (Integer)returnAddrs.pop(); 
        
        return i; 
    }

}
