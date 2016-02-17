/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.CallCode;
import Interpreter.VirtualMachine;
import Interpreter.debugger.DebuggerVM;

public class CallCodeDebug extends CallCode{

    public void execute(VirtualMachine vm){
         DebuggerVM v = (DebuggerVM)vm;
         
         v.addFER(); 
         
         super.execute(vm); //this will call the execute method for the super class
         
    }
    
}
