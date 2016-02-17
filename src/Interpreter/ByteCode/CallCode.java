/**
 * Transfers control to the indicated function.  
 * Stores the current address  in the returnAddrs stack for
 * when returning from the indicated function. 
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
 */

package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;


 


public class CallCode extends ByteCode{

        String byteCodeName = "CALL"; 
        String label;  //original label
        int label2; //argument or address that needs resolution, if needed
    
    
        public CallCode(){};
        public void init(Vector s) {
        
            label = (String)s.elementAt(0);
        
        }; 
        public void execute(VirtualMachine vm){
            int i = vm.getPC(); //get the current address (i.e. pc)
            vm.addReturnAddrs(i); //add the current address to the return address stack
            int branchPC = label2-2; 
            vm.setPC(branchPC); //branch to the address desired which is the pc
                                  //the pc = line number - 2 b/c the VM execute will increment the pc when we return from this function. 
            
            if (vm.isDumpOn()){
               print(vm.peekRunStack()); 
               vm.dumpRunStack();
            } 
        }
        
        public String getLabel(){
            return label; 
        }
        
        public int getInt(){
            return label2; 
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void setLabel(int s){            
            label2 = s;            
        }
        
        public void print(int i){
            System.out.print(byteCodeName + " "+ label + "    "); 
            System.out.println(label+ "(" + i + ")");
        } 


}
