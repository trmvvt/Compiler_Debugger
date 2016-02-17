/**
 * Performs binary operations on the top to values of the runTimeStack. 
 * Pops the two values, performs the operation and then stores the 
 * answer at the top of the stack.  
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
 */

package Interpreter.ByteCode;
import Interpreter.VirtualMachine;
import java.util.Vector;


 

public class BopCode extends ByteCode{
        String byteCodeName = "BOP"; 
    
        String bop; 
    
        public BopCode (){};
        public void init(Vector s) {
            bop = (String)s.elementAt(0); 
        }
        public void execute(VirtualMachine vm){
            //pop first two levels of the stack for operations
            int i = vm.popRunStack();
            int j = vm.popRunStack(); 
            if(bop.equals("+")){
                vm.pushRunStack((j+i)); 
            } else if(bop.equals("-")){
                vm.pushRunStack((j-i)); 
            } else if(bop.equals("/")){
                vm.pushRunStack((j/i)); 
            } else if(bop.equals("*")){
                vm.pushRunStack((j*i)); 
            } else if(bop.equals("==")){
                if(j==i){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals("!=")){
                if(j!=i){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals("<=")){
                if(j<=i){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals("<")){
                if(j<i){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals(">=")){
                if(j>=i){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals(">")){
                if(j>i){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals("|")){
                if(j!=0 || i!=0){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            } else if(bop.equals("&")){
                if(j!=0 && i!=0){ vm.pushRunStack(1); }
                else { vm.pushRunStack(0); }
            }
            
            
            if (vm.isDumpOn()){
               print();  
               vm.dumpRunStack();
            } 
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.println(byteCodeName + " "+ bop); 
        } 

}
