/**
 * Used prior to calling a function .  ARGS will set up a new frame n
 * down from the top of the frame, so that the new frame will 
 * include the arguments. 
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
 */

package Interpreter.ByteCode;


import Interpreter.VirtualMachine;
import java.util.StringTokenizer;
import java.util.Vector;





public class ArgsCode extends ByteCode{
    
        String byteCodeName = "ARGS"; 
        
        int i; 
    
        public ArgsCode () {};
        public void init(Vector s) {
            i = Integer.parseInt((String)s.elementAt(0)); 
        }
        public void execute(VirtualMachine vm){
            
           vm.newFramePointer(i);
            
            if (vm.isDumpOn()){
               print();  
               vm.dumpRunStack();
            }
            
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.println(byteCodeName + " "+ i); 
        }

       

}
