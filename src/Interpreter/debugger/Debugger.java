/**
 * Author: Poushali Banerjee
 * Last Update: May 5, 2010
 */

package Interpreter.debugger;

import Interpreter.ByteCodeLoader;
import Interpreter.Interpreter;
import Interpreter.Program;
import Interpreter.debugger.UI.UI;
import java.io.IOException;

public class Debugger extends Interpreter{

    private String baseFileName = new String(); 
    public Debugger(){}
    
    public Debugger(String codeFile) {
		try {
                        baseFileName = codeFile; 
			CodeTableDebug.init();
			bcl = new ByteCodeLoaderDebug(codeFile+=".x.cod");
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}    
    
    public void run() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Program program = bcl.loadCodes();
		DebuggerVM vm = new DebuggerVM(program);
		UI userInterface = new UI(baseFileName); 
                userInterface.UImain(vm);
	}
    
    
}
