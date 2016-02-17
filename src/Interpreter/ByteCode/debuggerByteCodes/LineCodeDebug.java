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


public class LineCodeDebug extends ByteCode{

    String byteCodeName = "LINE"; 
    String label; 
    Boolean breakPt = false; 
        
       
        public LineCodeDebug () {};
        public void init(Vector v) {
            label = (String)v.elementAt(0);             
        }
        public void execute(VirtualMachine vm){
            int i = Integer.parseInt((String)label); 
            DebuggerVM v = (DebuggerVM)vm; 
            
            v.setCurrentLine(i);  
            if(breakPt){ //if a breakpoint is set for this line, then stop executing code and return to UI
                v.setBreakPtFlag(breakPt); //set the DebuggerVM breakPtFlag to true
                v.setIsSet(false); //clear the step flag
            }
            
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public String getLabel(){
            return label; 
        }
        
        public void print(){
            System.out.println(byteCodeName + " " + label); 
        }
        
        public void setBreakPt(Boolean b){
            breakPt = b; 
        }
}
