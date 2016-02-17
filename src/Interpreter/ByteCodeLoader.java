/**
 * ByteCodeLoader looks up the actual class name using
 * CodeTable, then uses Java reflection to create an actual class instance.
 * The reader has more info on how this should go.
 * Author: Poushali Banerjee  
 * Last Update: May 5, 2010
 */
 
 package Interpreter;


import Interpreter.ByteCode.ByteCode;
import Interpreter.debugger.CodeTableDebug;
import Interpreter.debugger.FunctionEnvironmentRecord;
import java.io.*;
import java.util.StringTokenizer; 
import java.util.Vector;




public class ByteCodeLoader {
    
    protected Program p =new Program(); 
    protected int lineNum = 1; //to count the line number
    protected BufferedReader source;
    
    
    public ByteCodeLoader(){}; //default constructor 
    
    //constructor with one argument: the program file
    public ByteCodeLoader(String programFile) throws IOException {
        source = new BufferedReader(new FileReader(programFile));
    } 
    
    //load all the codes from the file.  Read the file line by line, tokenizing
    //each line and creating instances of the bytecode classes based on the 
    //information recevied. 
    public Program loadCodes() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Vector t = new Vector();
        
        String s = source.readLine(); 
        do {
        //get the first token from the string, this will be the bytecode class 
        StringTokenizer st = new StringTokenizer(s);
        //find the codeClass name from the CodeTable
        String codeClass = CodeTable.get(st.nextToken()); 
        //create a new instance of byteCode, and add it to the Program Object
        ByteCode bytecode = (ByteCode)(Class.forName("Interpreter.ByteCode."+codeClass).newInstance()); 
        p.addVectorElement(bytecode);
        
        //while loop to check if there are more tokens.  If there are, then each token is added
        //to a String t, which will be passed in to the init() method
            while (st.hasMoreTokens()){
                t.addElement(st.nextToken()); 
            }
            if (t.size() > 0){ //then there are arguments, so call init() with arguments
                 bytecode.init(t); //one or more arguments passed
                    //create a HashMap in the Program class that holds only the Label bytecodes
                    //will use this to resolve addresses 
                     if(bytecode.getName().equals("LABEL")){
                        p.addHashMapElement(lineNum, bytecode); //lineNumb is the address
                    }
            } else { //there are no arguments so call init() method with no arguments 
                bytecode.init(); //no arguments passed
            }
            //get next line of the file     
            s = source.readLine(); 
            lineNum++;
            t.clear(); 
        } while (s != null); //while there is more lines to process, 
        
        //resolve addresses. Go through each element of the vector in the Program class
        //and whenever an address needs to be resolved, it resolves it (further details
        //available in Program Class resolveAddress method. 
        p.resolveAddress();
        
        
        return p; 
    }//end loadCodes method
    
    
    /* public Program loadCodesDebug() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
         Vector t = new Vector();
        
        String s = source.readLine(); 
        do {
        //get the first token from the string, this will be the bytecode class 
        StringTokenizer st = new StringTokenizer(s);
        //find the codeClass name from the CodeTable
        String codeClass = CodeTable.get(st.nextToken()); 
        //create a new instance of byteCode, and add it to the Program Object
        ByteCode bytecode = (ByteCode)(Class.forName("Interpreter.ByteCode.debuggerByteCodes."+codeClass+"Debug").newInstance()); 
        p.addVectorElement(bytecode);
        
        //while loop to check if there are more tokens.  If there are, then each token is added
        //to a String t, which will be passed in to the init() method
            while (st.hasMoreTokens()){
                t.addElement(st.nextToken()); 
            }
            if (t.size() > 0){ //then there are arguments, so call init() with arguments
                 bytecode.init(t); //one or more arguments passed
                    //create a HashMap in the Program class that holds only the Label bytecodes
                    //will use this to resolve addresses 
                     if(bytecode.getName().equals("LABEL")){
                        p.addHashMapElement(lineNum, bytecode); //lineNumb is the address
                    }
            } else { //there are no arguments so call init() method with no arguments 
                bytecode.init(); //no arguments passed
            }
            //get next line of the file     
            s = source.readLine(); 
            lineNum++;
            t.clear(); 
        } while (s != null); //while there is more lines to process, 
        
        //resolve addresses. Go through each element of the vector in the Program class
        //and whenever an address needs to be resolved, it resolves it (further details
        //available in Program Class resolveAddress method. 
        p.resolveAddress();
        
        return p; 
                  
    }//end loadCodesDebug function*/
    
}
