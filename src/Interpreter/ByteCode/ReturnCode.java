package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Return from the current function.  Stores the value at the top of the stack, 
 * pops all the values from the top to the frame, pops the top value from 
 * the frameStack and then adds the value stored to the top of the stack. 
 */


public class ReturnCode extends ByteCode{

        String byteCodeName = "RETURN"; 
        String label = null; //argument or address that needs resolution, if needed
    
        public ReturnCode(){};
        public void init(){};
        public void init(Vector s) {        
            label = (String)s.elementAt(0); 
        }
        public void execute(VirtualMachine vm){
            vm.popRunStackFrame();
            int i = vm.getReturnAddrs();
            vm.setPC(i);
            
            if (vm.isDumpOn()){
               print(vm.peekRunStack());
               vm.dumpRunStack();
            } 
        }
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(int i){
            System.out.print(byteCodeName +" "); 
                    
                if(label == null){
                 System.out.println(label + "    exit: " +  i);
                } else {
                    System.out.println(label + "    exit " + label + ":" +  i); 
                }
        }

}
