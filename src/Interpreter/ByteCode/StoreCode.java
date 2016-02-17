package Interpreter.ByteCode;
import Interpreter.VirtualMachine;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Pops the top of the stack.  Stores that value from the top of the stack
 * into the offset n from the start of the frame.  The second variable is 
 * just used as a comment to indicated the variable name where the data is
 * stored. 
 */


public class StoreCode extends ByteCode{

        String byteCodeName = "STORE"; 
        
        int i; 
        String j; 
    
        public StoreCode(){};
        public void init(Vector s) {
            
            i = Integer.parseInt((String)s.elementAt(0)); 
            if(s.size() > 1){
                 j = (String)s.elementAt(1);
            }        
        }
        
        public void execute(VirtualMachine vm){
            
            vm.storeRunStack(i);
            int s = vm.peekRunStack(); 
            
            if (vm.isDumpOn()){
               print(s);  
               vm.dumpRunStack();
            } 
        }
        public String getName(){
            return byteCodeName; 
        }
        
        //int s is the top value of the stack which is to be stored
        public void print(int s){
            
            System.out.print(byteCodeName +" "+ i +  " " + j+ "    "); 
            System.out.println(j+ " = "+ s); 
        }

}
