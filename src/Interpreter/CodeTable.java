
/* DescriptionL: This file will host a Hashmap with the 
 * name/value pairs of the ByteCode. 
 * The ByteCodeLoader will use this to look up
 * actual class name of the bytecode so that
 * it will create the correct ByteCode class.
 * Assume that all bytecode is correct, thus 
 * no error checking.
 * Author: Poushali Banerjee  
 * Last Update: May 5, 2010
 */

package Interpreter;


import java.util.HashMap;

 


public class CodeTable {
    
    protected static HashMap<String, String> map = new HashMap(); 
    
    
    public CodeTable (){ }

    public static void init(){
        map.put("HALT", "HaltCode");
        map.put("POP", "PopCode");
        map.put("FALSEBRANCH", "FalsebranchCode");
        map.put("GOTO", "GoToCode");
        map.put("STORE", "StoreCode");
        map.put("LOAD", "LoadCode");
        map.put("LIT", "LitCode");
        map.put("ARGS", "ArgsCode");
        map.put("CALL", "CallCode");
        map.put("RETURN", "ReturnCode");
        map.put("BOP", "BopCode");
        map.put("READ", "ReadCode");
        map.put("WRITE", "WriteCode");
        map.put("LABEL", "LabelCode");
        map.put("DUMP", "DumpCode"); 
        map.put("BS", "BSCode"); 
        map.put("FUNCTION", "FunctionCode"); 
        map.put("LINE", "LineCode"); 
        map.put("Enter", "EnterCode"); 
        map.put("FORMAL", "FormalCode"); 
        map.put("Pop", "PopCode"); 
    };
    
    //Need a get function that will take the hashmap key 
    //and return the value for  that key.  Used to create
    //an instance of that bytecode class. 
    public static String get(String code){
       return map.get(code);
      
    };
    
}
