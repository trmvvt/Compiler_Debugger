/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.PopCode;
import Interpreter.VirtualMachine;
import Interpreter.debugger.DebuggerVM;
import Interpreter.debugger.FunctionEnvironmentRecord;


public class PopCodeDebug extends PopCode{

        public void execute(VirtualMachine vm){
            
            int i = this.getValue(); //number of items to delete
            
            DebuggerVM v = (DebuggerVM)vm; 
            
            for (int j=0; j<i; j++){
                v.popRunStack(); 
                v.removeSymbol();
            }
            
        }
        
        
    
}
