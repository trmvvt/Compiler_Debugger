package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Push the value in the slot which is offset n from the start 
 * of the frame to the top of the frame.  The second variable is 
 * just a comment indicating the variable that will be initialized 
 * to that value.  
 * i.e. Store 2 i will take the value located at position 2 on the stack
 * and will store it on the top.  It ultimately means i=2; 
 */



public class LoadCode extends ByteCode{

        String byteCodeName = "LOAD"; 
    
        protected int i; 
        protected String j;     
    
        public LoadCode(){};
        public void init(Vector s) {        
            
            i = Integer.parseInt((String)s.elementAt(0)); 
            j = (String)s.elementAt(1);     
        }
        public void execute(VirtualMachine vm){
            
            vm.loadRunStack(i);
            
            if (vm.isDumpOn()){
               print(); 
               vm.dumpRunStack();
            } 
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.print(byteCodeName + " "+ i + " " + j + "    "); 
            System.out.println("<load " + j + ">");
        }
}
