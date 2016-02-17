/* Description: Will be a stack in the Debuggger VM.  Each entry will contain an instance
 * of the SymbolTable. Owns the SymbolTable so all operations performed by the
 * bytecodes on the SymbolTable occur through FunctionEnvironmentRecord
 * Author: Poushali Banerjee
 * Last Update: May 5, 2010
 */
 

package Interpreter.debugger;

import Interpreter.RunTimeStack; 
import Interpreter.ByteCode.ByteCode;
import Interpreter.CodeTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;


public class FunctionEnvironmentRecord {

    private SymbolTable table = new SymbolTable(); 
    private Vector<Symbol> symbolList = new Vector(); 
    private Symbol key;
    private String funcName = " "; //function name
    private int startLine; //starting source line number for function
    private int endLine; //ending source line number for function
    private int currentLine; //maintains the current line number being processed by VM
                     //must reset this when branching instructions are processed
    private static BufferedReader source; 
            
    public FunctionEnvironmentRecord(){beginScope(); }; //default constructor
    
    public String getFuncName(){ return funcName; };
    public int getStartLine (){ return startLine; };
    public int getEndLine() { return endLine; }; 
    public int getCurrentLine(){ return currentLine; }; 
    public Symbol getSymbolAt(int i) {
        Symbol s = symbolList.elementAt(i);
        return s; }
    public Object getSymbolValue(Symbol s) {
        return table.get(s);
    }
    public Symbol getSymbol(String s){
        return Symbol.symbol(s, 0); 
       
    }
    public void setFuncName(String s){ funcName = s; };
    public void setStartLine(int i){startLine = i; };
    public void setEndLine(int i){ endLine = i;};
    public void setCurrentLine(int i){ currentLine = i;};
    
    public void addSymbol(String s, int i){
        key = Symbol.symbol(s, i); 
        table.put(key, i);
        symbolList.add(key);        
    }
    
    public void beginScope(){
        table.beginScope();
    }
    public void removeSymbTable(){
        table.endScope();
    }
    
    public void removeSymbol(){
        table.popSymbol();
        int i = symbolList.size(); 
        i--; 
        symbolList.removeElementAt(i); //remove element from vector
    }
   
    
    public void print(){
          
        System.out.print("(");
        //print out all the symbols in my vector from the beginning until 
        //reaching in the table size 
        for(int i =0; i<table.getSize(); i++){
            Symbol s = symbolList.elementAt(i); 
            System.out.print("<"+s+ "/"+ table.get(s)+">,");
        }                 
        System.out.println(funcName + "," +startLine+","+endLine+","+currentLine+")" );   
    }
    
    public void printVarables(){
        for (int i = 0; i<table.getSize(); i++){
            Symbol s = symbolList.elementAt(i); 
            System.out.println("This is the table size: "+table.getSize()); 
            table.get(s); 
            System.out.print(s+": "+ table.get(s)+ "  "); 
        }
        System.out.println(); 
    }
    
    public int getTableSize(){
        return table.getSize();
    }
    
    //this method is used just for testing purposes for MileStone 2
    //Copied from loadCode in ByteCodeLoader, with some changes
    /*public static void main(String args[])throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        FunctionEnvironmentRecord fctEnvRecord = new FunctionEnvironmentRecord();
        source = new BufferedReader(new FileReader(args[0]));
        
        Vector t = new Vector();
        CodeTable.init(); 
        String s = source.readLine(); 
        do {
        //get the first token from the string, this will be the bytecode class 
        StringTokenizer st = new StringTokenizer(s);
        //find the codeClass name from the CodeTable
        String codeClass = CodeTable.get(st.nextToken()); 
        //create a new instance of byteCode, and add it to the Program Object
        ByteCode bytecode = (ByteCode)(Class.forName("Interpreter.ByteCode.debuggerByteCodes."+codeClass).newInstance()); 
        //while loop to check if there are more tokens.  If there are, then each token is added
        //to a String t, which will be passed in to the init() method
            while (st.hasMoreTokens()){
                t.addElement(st.nextToken()); 
            }
            if (t.size() > 0){ //then there are arguments, so call init() with arguments
                 bytecode.init(t); //one or more arguments passed
                    
            } else { //there are no arguments so call init() method with no arguments 
                bytecode.init(); //no arguments passed
            }
            //get next line of the file    
            bytecode.execute(fctEnvRecord); 
            s = source.readLine(); 
            t.clear(); 
             
        } while (s != null); //while there is more lines to process,
        
    }//end main function
   */
    
}//end class

    
