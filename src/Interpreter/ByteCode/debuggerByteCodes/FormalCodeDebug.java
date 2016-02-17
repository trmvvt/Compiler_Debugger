/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.ByteCode;
import Interpreter.VirtualMachine;
import Interpreter.debugger.DebuggerVM;
import Interpreter.debugger.FunctionEnvironmentRecord;
import java.util.Vector;


public class FormalCodeDebug extends ByteCode{

        private String byteCodeName = "FORMAL"; 
        private String i; 
        private int j;    
       
        public FormalCodeDebug() {};
   
        
        public void init(Vector v) {
            
            i = (String)v.elementAt(0);            
            j = Integer.parseInt((String)v.elementAt(1));
        }
        
        public String getValue(){ return i; }
        
        public int get2ndArg(){ return j; }
        
        public void execute(VirtualMachine vm){
            DebuggerVM v = (DebuggerVM)vm; 
               v.addSymbol(i, j); //enter the variable and value on to the symbol table. 
               v.setTableSize();
               v.setFuncFormalFlag(true);
            
            //check if rollback flag is set. if so, stop execution
            if(v.getRollbackFlag()){
                v.setRunningFalse();
            }
         
        }
        public String getName(){
            return byteCodeName; 
        }
    
}
