/** This byteCode class is for testing the FER.  It adds symbols and value
 * to the SymbolTable
 */

package Interpreter.ByteCode.debuggerByteCodes;

import Interpreter.ByteCode.ByteCode;
import Interpreter.debugger.FunctionEnvironmentRecord;
import java.util.Vector;


public class EnterCode extends ByteCode{

    String byteCodeName = "Enter";
    String i; //to hold variable letter
    int j; //to hold variable value
        
       
        public EnterCode () {};
        public void init(Vector v) {
            i = (String)v.elementAt(0); 
                   
            j = Integer.parseInt((String)v.elementAt(1)); 
        }
        public void execute(FunctionEnvironmentRecord fctEnvRecord){
            fctEnvRecord.addSymbol(i, j); 
            print(); 
            fctEnvRecord.print(); 
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.print(byteCodeName + " " + i + " " + j +"  "); 
        }
}
