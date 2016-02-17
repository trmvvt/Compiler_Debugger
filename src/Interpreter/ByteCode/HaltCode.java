package Interpreter.ByteCode;

import Interpreter.VirtualMachine;

/**
 * Halt Execution
 */


public class HaltCode extends ByteCode{

        String byteCodeName = "HALT"; 
    
        public HaltCode (){};
        public void init(){};
        public void execute(VirtualMachine vm){
            
            if (vm.isDumpOn()){
               print(); 
               vm.dumpRunStack();
            } 
            vm.setRunningFalse(); 
            
        }
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.println(byteCodeName); 
        }

}
