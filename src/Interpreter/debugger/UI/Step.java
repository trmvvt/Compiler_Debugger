/** 
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
*/

package Interpreter.debugger.UI;
import Interpreter.VirtualMachine;
import Interpreter.debugger.DebuggerVM;

public class Step {

   
    
    Step () {}; 
    
    public void stepOver(DebuggerVM vm){ }
    
    //if user calls stepOut, then need to record the current EnvironmentStack size
    //and use it to check when the stepOut occurs. Also set the isSet flag to true.
    public void stepOut(DebuggerVM vm){        
        vm.setIsSet(true); 
        vm.setEnvSize();
    }
    
    //if user calls stepInto, then need to record the current EnvironmentSTack size
    //and when it changes, we break.  
    public void stepInto(DebuggerVM vm){
        vm.setIsSet(true); 
        vm.setEnvSize();
    }
    
}
