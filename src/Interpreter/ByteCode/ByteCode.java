/**
 * This is the abstract class for all the bytecodes.  
 * Performs binary operations on the top to values of the runTimeStack. 
 * Pops the two values, performs the operation and then stores the 
 * answer at the top of the stack.  
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
 */

package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import Interpreter.debugger.FunctionEnvironmentRecord;
import java.util.Vector;




public abstract class ByteCode {

    public void execute(FunctionEnvironmentRecord fctEnvRecord) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void init(){};
    public void init(Vector s) {};
    public void execute(VirtualMachine vm){};
    public String getName() {return null; }; 
    public String getLabel() { return null;} //gets the label of the bytecode
    public void setLabel(int i) { };  //sets the label; used for address resolution
    public void print(){}; 
    public void setBreakPt(Boolean b){}
    
    //public void Main(){}; 
}

