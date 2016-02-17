/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.LitCode;
import Interpreter.VirtualMachine;
import Interpreter.debugger.DebuggerVM;


public class LitCodeDebug extends LitCode {

    
    
    public void execute(VirtualMachine vm){
        
            DebuggerVM v = (DebuggerVM)vm; 
            int i = this.getValue(); 
            String j = this.get2ndArg(); 
            v.pushRunStack(i);
            
            //get offset number to store into Symbol Table
            int k = v.getVarOffset();
            if (j != null){
               v.addSymbol(j, k); //enter the variable and value on to the symbol table. 
            } 
        }
    
}
