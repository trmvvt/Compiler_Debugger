/* Description: 
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 * Author: Poushali Banerjee  
 * Last Update: May 5, 2010
*/

package Interpreter;

import Interpreter.debugger.Debugger;
import Interpreter.debugger.DebuggerVM;
import Interpreter.debugger.UI.UI;
import java.io.*;


public class Interpreter {

	protected ByteCodeLoader bcl;
        private static String baseFileName; 

        public Interpreter(){}
        //constructor with one argument
	public Interpreter(String codeFile) {
		try {
			CodeTable.init();
			bcl = new ByteCodeLoader(codeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}
        
        //run method for regular interpreter execution
	void run() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Program program = bcl.loadCodes();
		VirtualMachine vm = new VirtualMachine(program);
		vm.executeProgram();
	}
        
        
	public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (args.length == 0) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
		}
                if(args[0].equals("-d")){ //run the debugger
                    /*String temp; 
                    baseFileName = args[1]; 
                    temp = baseFileName + ".x.cod";  
                    (new Interpreter(temp)).runDebug(); */
                    (new Debugger(args[1])).run(); 
                } else {    //run interpreter w/o debugger
                    (new Interpreter(args[0])).run();
                }
		
	}
}