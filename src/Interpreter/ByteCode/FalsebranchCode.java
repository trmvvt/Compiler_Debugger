package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;

/**
 * Pops the top of the stack; if it's false (0), then it 
 * branches to the <label>, otherwise it just moves
 * to the next  code. 
 */


public class FalsebranchCode extends ByteCode{

        String byteCodeName = "FALSEBRANCH"; 
        String label;  //original label
        int label2; //argument or address that needs resolution, if needed
    
        public FalsebranchCode(){};
        public void init(Vector s) {        
            label = (String)s.elementAt(0);          
        }; 
        public void execute(VirtualMachine vm){

            int i = vm.popRunStack();
            if(i == 0){
                vm.setPC((label2-2));
            } else {
                //do nothing; branch to next code
            }
            
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
