/**
 * Author: Poushali Banerjee
 * Last Update: May 5, 2010
 */

package Interpreter.debugger;

import Interpreter.ByteCode.ByteCode;
import Interpreter.Program;
import Interpreter.RunTimeStack;
import Interpreter.VirtualMachine;
import java.util.Stack; 
import java.util.Vector;

public class DebuggerVM extends VirtualMachine{

    Vector<String> entries = new Vector(); 
    Stack<FunctionEnvironmentRecord> environmentStack = new Stack(); 
    private Boolean breakPtFlag; 
    private boolean isSet = false; //for stepcommands
    private int envSize; //set when stepcommands are encountered
    private boolean rollbackFlag = false;
    private Rollback rollback = new Rollback(); 
    
    
    public DebuggerVM(){}; //default constructor
    
    public DebuggerVM(Program p){
       program = p; //program is part of the VirtualMachine class
       FunctionEnvironmentRecord temp = new FunctionEnvironmentRecord(); 
       environmentStack.push(temp);  
       setRollback(); 
    }
    
    public void executeProgram(){ 
        if(pc == 0){ //only performs these steps the first time we enter the execution
            runStack = new RunTimeStack(); 
            returnAddrs = new Stack(); 
            returnAddrs.push(0);             
        }
        isRunning = true;
        breakPtFlag = false; 
        while(isRunning && !breakPtFlag){
            ByteCode code = program.getCode(pc); 
            code.execute(this); //calls the bytecode's execute method 
            pc++;
        }//end while loop
    }
    
    //these methods are used to access the Symbol Table through the FER
    public String getFuncName(){ return environmentStack.peek().getFuncName();  }
    public int getStartLine (){ return environmentStack.peek().getStartLine();  }
    public int getEndLine() { return environmentStack.peek().getEndLine();  }
    public int getCurrentLine(){ return environmentStack.peek().getCurrentLine(); }
    public int getVarOffset(){
        int i = runStack.getVectorSize()-1; 
        int j = runStack.peekTopFrame(); 
        i = i - j; 
        return i; 
    }
    public void setFuncName(String s){ environmentStack.peek().setFuncName(s); }
    public void setStartLine(int i){environmentStack.peek().setStartLine(i); }
    public void setEndLine(int i){ environmentStack.peek().setEndLine(i);}
    public void setCurrentLine(int i){ environmentStack.peek().setCurrentLine(i);}
    
    
    
    public void addSymbol(String s, int i){ environmentStack.peek().addSymbol(s, i); }
    
    public int getTableSize(){ return environmentStack.peek().getTableSize(); }
    
    public void beginScope(){ environmentStack.peek().beginScope(); }
    public void removeSymbTable(){ environmentStack.peek().removeSymbTable();}
    public void removeSymbol(){ environmentStack.peek().removeSymbol(); }
    public void addFER(){environmentStack.push(new FunctionEnvironmentRecord());}
    public void popFER(){
        removeSymbTable(); 
        environmentStack.pop();
        } 
    public void setBreakPtFlag(Boolean b){ breakPtFlag = b; }
    public int getEnvStackSize(){ return environmentStack.size(); }
    public void setIsSet(boolean b){ isSet = b; }
    public void setEnvSize(){ envSize = environmentStack.size(); }
    public boolean getIsSet(){ return isSet; }
    public int getEnvSize() {return envSize;}
    public boolean isEnvSizeDifferent(){
        if(envSize != environmentStack.size()){
            return true; 
        } else {
            return false; 
        }
    }
    
    public void changeVarValue(String s, int i){
        
        Symbol symb = environmentStack.peek().getSymbol(s); 
        int j = (Integer)environmentStack.peek().getSymbolValue(symb); //this gives the variables offset
        j += runStack.peekTopFrame();
        runStack.push(i); 
        runStack.store(j);
        
    }
    
   
    public void printVars(){ 
      
      for (int i = 0; i<environmentStack.peek().getTableSize(); i++){
          Symbol s = environmentStack.peek().getSymbolAt(i); 
          System.out.print(s+ ": "); 
          //variable offset, which is stored in the symbol table
          int j = (Integer)environmentStack.peek().getSymbolValue(s);
                    
          //get the offset of the framepointer to add to the offset of the variable
          j += runStack.peekTopFrame();
          
          int k = runStack.getValueAt(j); 
          System.out.print(k+"  "); 
      }
          System.out.println();
    }
    
    public ByteCode getByteCode(int i){
        return program.getCode(i); 
    }
    //check that the Line code with indicated line number exists.  If so, call the 
    //function to set the breakpoint at that LineCode ByteCode. 
    public Boolean isBreakPtValid(int i){
        ByteCode temp; 
        for(int j = 0; j < program.getVectorSize(); j++){
            temp = program.getCode(j); //this gets the bytecode
            //this should check that the name of the bytecode is LINE and that the label == the line number requested for a breakpoint
            if(temp.getName().equals("LINE") && temp.getLabel().equals(Integer.toString(i))){
                temp.setBreakPt(true); //sets the breakpoint to true for this bytecode
                return true; 
            }  
        }//end for loop
        return false; 
    }//end isLineCode method
    
    
    public void clearBreakPt(int i){
        ByteCode temp; 
        //cycle through the bytecodes
        for(int j = 0; j < program.getVectorSize(); j++){
            temp = program.getCode(j); //this gets the bytecode
            //this should check that the name of the bytecode is LINE and that the label == the line number requested for a breakpoint
            if(temp.getName().equals("LINE") && temp.getLabel().equals(Integer.toString(i))){
                temp.setBreakPt(false); //sets the breakpoint to false for this bytecode
            }  
        }//end for loop
    }
    
    public void setRollback(){
        rollback.setFuncCurLine(getCurrentLine());
        rollback.setFuncPC(pc);
        rollback.setTableSize(getTableSize());
    }
    
    public int getFuncTableSize(){ return rollback.getFuncTableSize();}
    public void setTableSize() { 
        int i = this.getTableSize();
        rollback.setTableSize(i); //set rollback table size to current table size
    }
    
    public void loadRollback(){ rollback.rollingBack(this); }
    public boolean getRollbackFlag() { return rollbackFlag; }
    public void setRollbackFlag(boolean b) { rollbackFlag = b; }
    public void setFuncFormalFlag(boolean b) { rollback.setFuncHasFormal(b);}
    public boolean getFuncFormalFlag () { return rollback.getFuncHasFormal();}
    
}
