/**
 * Author: Poushali Banerjee
 * Last Update: May 5, 2010
 */

package Interpreter.debugger;

import Interpreter.CodeTable;

public class CodeTableDebug extends CodeTable {
       
     public static void init(){
        
        CodeTable.init();
         
        map.put("POP", "debuggerByteCodes.PopCodeDebug");
        map.put("LIT", "debuggerByteCodes.LitCodeDebug");
        map.put("RETURN", "debuggerByteCodes.ReturnCodeDebug");
        map.put("BS", "debuggerByteCodes.BSCodeDebug"); 
        map.put("FUNCTION", "debuggerByteCodes.FunctionCodeDebug"); 
        map.put("LINE", "debuggerByteCodes.LineCodeDebug"); 
        map.put("ENTER", "debuggerByteCodes.EnterCodeDebug"); 
        map.put("FORMAL", "debuggerByteCodes.FormalCodeDebug"); 
        map.put("CALL", "debuggerByteCodes.CallCodeDebug"); 
        
         
     }
    
}
