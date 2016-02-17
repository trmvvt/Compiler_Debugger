package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;

/**
 * Branches to the <label> indicated.  
 */


public class GoToCode extends ByteCode{

        String byteCodeName = "GOTO"; 
        String label; 
        int label2; //argument or address that needs resolution, if needed
    
    
        public GoToCode (){};
        public void init(Vector s){
            label = (String)s.elementAt(0); 
        }
        public void execute(VirtualMachine vm){
            
            vm.setPC((label2-2)); //pc = line -2; 1 for the offset from the line number to the pc
                                  //and one for the fact that pc will be incremented in the vm execute method
                                  //once we return from this function 
            
            if (vm.isDumpOn()){
               print();  
               vm.dumpRunStack();
            } 
        }
        
        public int getInt(){
            return label2; 
        }
        
        public String getLabel(){
            return label; 
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        public void setLabel(int s){            
            label2 = s;            
        }

        public void print(){
            System.out.println(byteCodeName + " "+ label);
        }
        
}
