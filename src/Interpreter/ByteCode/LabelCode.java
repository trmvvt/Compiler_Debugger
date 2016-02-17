package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;

/**
 * Gives the target for the branches.  Used for 
 * GOTO, CALL and FALSEBRANCH
 */


public class LabelCode extends ByteCode{

        String byteCodeName = "LABEL"; 
        String label; //argument or address that needs resolution, if needed
        
        public LabelCode (){};
        public void init(Vector s){        
            label = (String)s.elementAt(0);        
        }; 
        public void execute(VirtualMachine vm){
            if (vm.isDumpOn()){
               print();  
               vm.dumpRunStack();
            } 
        }
        public String getName(){
            return byteCodeName; 
        }
        public String getLabel(){
            return label; 
        }
        
        public void print(){
            System.out.println(byteCodeName +" "+ label); 
        }

}
    
