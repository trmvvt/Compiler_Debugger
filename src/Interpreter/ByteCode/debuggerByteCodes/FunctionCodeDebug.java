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


public class FunctionCodeDebug extends ByteCode{

    String byteCodeName = "FUNCTION"; 
    String funcName; 
    int startLine; 
    int endLine; 
        
        public FunctionCodeDebug () {};
        public void init(Vector v) {
            funcName = (String)v.elementAt(0); 
            startLine = Integer.parseInt((String)v.elementAt(1)); 
            endLine = Integer.parseInt((String)v.elementAt(2)); 
        }
        public void execute(VirtualMachine vm){
            DebuggerVM v = (DebuggerVM)vm; 
            //v.addFER(); 
            v.setFuncName(funcName); 
            v.setStartLine(startLine); 
            v.setEndLine(endLine);
    
            //sets the rollback information in case user calls rollback
            v.setRollback();
            
            //need to check if we are stepping into or out of a function
            if(v.getIsSet()){ 
                //if the environmentalStack size has changed, then we have stepped out/into
                if(v.isEnvSizeDifferent()){ 
                     v.setRunningFalse(); //so stop execution
                }                
            }
            
            //check if rollback flag is set, then remove the symbol talbe
            if(v.getRollbackFlag()){
                v.removeSymbTable();
                //check if the function has a formal code, if not then stop
                if (!v.getFuncFormalFlag()){
                    v.setRunningFalse();
                }
            }
             
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
       
        
        public void print(){
            System.out.println(byteCodeName + " " + funcName + " "+ startLine + " " + endLine); 
        }
}
