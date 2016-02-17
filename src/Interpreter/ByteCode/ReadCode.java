package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.InputStreamReader; 

/**
 * Prompt the user for input and puts the value read on 
 * to the top of the stack. 
 */


public class ReadCode extends ByteCode{

        String byteCodeName = "READ"; 
        int s; //to hold the integer entered by the user
       
        public ReadCode(){};
        public void init(){};
        public void execute(VirtualMachine vm){
            
            //get an integer from the user
            System.out.print("Please enter in an integer: "); 
            try {
            BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
            String line = in.readLine();
            s = Integer.parseInt(line);
            } catch( java.io.IOException ex ) {
            }
            
            vm.pushRunStack(s);
            
            if (vm.isDumpOn()){
               print();  
               vm.dumpRunStack();
            } 
        }
        public String getName(){
            return byteCodeName; 
        }
        
        public void print(){
            System.out.println(byteCodeName); 
        }
}
