package Interpreter.ByteCode;

import Interpreter.VirtualMachine;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * DumpCode turns the runtime dumping on and off.  Done with an interpreter 
 * switch that will cause dumping AFTER exeution of each bytecode when 
 * turned ON.  OFF will reset the switch to end dumping.  
 */


public class DumpCode extends ByteCode{
    
        String byteCodeName = "DUMP"; 
        String i; 
        
        public DumpCode () {};
        public void init(Vector s) {
            i = (String)s.elementAt(0); 
        }
        public void execute(VirtualMachine vm){
            if(i.equals("ON")){
                vm.turnDumpOn(); 
            }
            else if(i.equals("OFF")){
                vm.turnDumpOff(); 
            }
        }
        
        public String getName(){
            return byteCodeName; 
        }
        
        
        public void print(){
            System.out.println(byteCodeName + i); 
        } 

}
