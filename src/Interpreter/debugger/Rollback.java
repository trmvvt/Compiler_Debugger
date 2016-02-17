/**
 * Author: Poushali Banerjee
 * Last Update: May 5, 2010
 */

package Interpreter.debugger;


public class Rollback {

    private int functionPC; 
    private int functionCurrentLine; 
    boolean funcHasFormal = false; 
    private int funcTableSize; //keeps track of the number of variables
    //record the table size when the function is called.  If a formal is called, then 
    //change the table size to what it is after the formal
    //then when the rollback is loaded, we need to keep checking after each 
    //bytecode whether the table size is the same
    //once it's the same, stop
    //so I have to have all the bytecodes check if the flag is set and if it is, check the
    //bytecodes? that seems rediculous
    
    //wait, so when a function is loaded, it is going to either have formals loaded or not
    //if formals are loaded, then we need to continue execution of the bytecode until after the formal
    //if it doesn't have formals, then we just stop right after the function
    
    
    
    public void setFuncPC(int i){ functionPC = i-2; }
    public void setFuncCurLine(int i){ functionCurrentLine = i; }
    public void setTableSize(int i){ funcTableSize = i;}
    public void setFuncHasFormal(boolean b){ funcHasFormal = b; }
    public void resetCurrentLine(DebuggerVM vm) {        
        vm.setCurrentLine(functionCurrentLine);
        vm.environmentStack.peek().setCurrentLine(functionCurrentLine);
    }
    public void resetPC(DebuggerVM vm){ vm.setPC(functionPC); }
    
    public int getFuncPC(){ return functionPC; }
    public int getFuncCurLine(){ return functionCurrentLine; }
    public int getFuncTableSize(){return funcTableSize; }
    public boolean getFuncHasFormal(){return funcHasFormal; }
    
    public void rollingBack(DebuggerVM vm){
        resetCurrentLine(vm); 
        resetPC(vm); 
    }


}
