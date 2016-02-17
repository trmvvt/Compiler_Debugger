package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;

/**
 * Writes teh value on top of the stack to the output. 
 */


public class WriteCode extends ByteCode{

        String byteCodeName = "WRITE"; 
       
        public WriteCode(){};
        public void init(){};
        public void execute(VirtualMachine vm){
            if (vm.isDumpOn()){
               print();
               vm.dumpRunStack();
            } 
           System.out.println(vm.peekRunStack());  //print the top value of the stack even if dump is off
            
        }
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.println(byteCodeName); 
        }

}
