package Interpreter.ByteCode;
import Interpreter.VirtualMachine;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Loads the value given to the top of the stack.  The second argument, 
 * if given, is just a comment and is used to indicate the variable that 
 * will be initialized to that value.  
 * i.e. Lit 2 i means i=2
 */



public class LitCode extends ByteCode{

        private String byteCodeName = "LIT"; 
    
        private int i; 
        private String j; 
    
        public LitCode(){};
        public void init(Vector s) {
            
            i = Integer.parseInt((String)s.elementAt(0)); 
            
            if(s.size() > 1){
                j = (String)s.elementAt(1);
            }
        }
        
        public int getValue(){
            return i; 
        }
        
        public String get2ndArg(){
            return j; 
        }
        public void execute(VirtualMachine vm){
            
            vm.pushRunStack(i);
            
            if (vm.isDumpOn()){
               print(); 
               vm.dumpRunStack();
            } 
        }
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
             
            
            if(j == null){
                 System.out.println(byteCodeName + " "+ i);
            } else {
                System.out.print(byteCodeName + " "+ i + " " + j + "    "); 
                System.out.println("int "+ j); 
            }
        }

}
