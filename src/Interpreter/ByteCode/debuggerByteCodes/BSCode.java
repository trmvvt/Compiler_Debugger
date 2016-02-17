/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.ByteCode;
import Interpreter.debugger.FunctionEnvironmentRecord;


public class BSCode extends ByteCode{

    String byteCodeName = "BS"; 
        
       
        public BSCode () {};
        public void init() {
            
        }
        public void execute(FunctionEnvironmentRecord fctEnvRecord){
            fctEnvRecord.beginScope();
            print(); 
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.println(byteCodeName); 
        }
    
}
