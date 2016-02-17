/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.ReturnCode;
import Interpreter.VirtualMachine;
import Interpreter.debugger.DebuggerVM;

public class ReturnCodeDebug extends ReturnCode{

    public void execute(VirtualMachine vm){
            DebuggerVM v = (DebuggerVM)vm; 

            v.popFER(); 
            
            //need to check if the Step Class boolean isSet is set to true.  If so, then 
            //need to check if we are stepping out of the function.  If so, then 
            //execution should stop.  
            if(v.getIsSet()){ //are we stepping out?
                if(v.isEnvSizeDifferent()){ //if so, have we stepped out yet?
                     v.setRunningFalse(); //if so, stop esxecution
                }
                
            }
            
            super.execute(vm); //this will call the execute method for the super class
        }
    
}
