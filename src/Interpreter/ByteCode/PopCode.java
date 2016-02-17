package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;

/**
 * Pop the top n levels of the runtime stack.
 */


public class PopCode extends ByteCode{

        String byteCodeName = "POP"; 
        private int i; 
        
        public PopCode(){};
        public void init(Vector s) {        
            i = Integer.parseInt((String)s.elementAt(0));         
        }
        public void execute(VirtualMachine vm){
            
            for(int j=0; j < i; j++){
                vm.popRunStack();
            }
            
            if (vm.isDumpOn()){
               print();  
               vm.dumpRunStack();
            } 
        }
        public String getName(){
            return byteCodeName; 
        }
        
        public int getValue(){
            return i; 
        }
        
        public void print(){
            System.out.println(byteCodeName + " "+ i); 
        }

}
