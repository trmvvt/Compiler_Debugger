/*
* Description: This class holds the instances of the ByteCode which was
* created by the ByteCodeLoader. It will walk through the bytecode
* and will resolve symbolic addresses. 
* Author: Poushali Banerjee
* Last Update: May 5, 2010
*/



package Interpreter;

import Interpreter.ByteCode.ByteCode;
import java.util.Vector;
import java.util.HashMap;


public class Program {
    
    Vector <ByteCode> byteCodeVector = new Vector(); 
    static HashMap<Integer, ByteCode> programMap = new HashMap(); //The key will be the line number. 

    public Program(){}; //default constructor
    public Program(String programFile){}; //constructor w/ 1 argument
    
    //adds an element to the vector of bytecodes from the source file
    public void addVectorElement(ByteCode s) {
           byteCodeVector.add(s);
    }
        
    //returns the requested bytecode to the VirtualMachine
    public ByteCode getCode(int i){
        return (ByteCode) (byteCodeVector.get(i)); 
    }
    
    public int getVectorSize(){
        return byteCodeVector.size(); 
    }
    
    //adds only labels to the HashMap programMap
    public void addHashMapElement(int i, ByteCode b){
        programMap.put(i, b); 
    }
    
    //resolves addresses for GOTO, CALL and FALSEBRANCH functions
    //used to determine where the control of the program branches to
    //during execution. 
    public void resolveAddress(){
         
        // cycle through each of the vector elements
        for (int j = 0; j < byteCodeVector.size(); j++){ 
            
            //if the vector element is one that needs an address resoltuion then 
            if(byteCodeVector.get(j).getName().equals("FALSEBRANCH")  || byteCodeVector.get(j).getName().equals("GOTO") || byteCodeVector.get(j).getName().equals("CALL")){
                
                //go through the HashMap that contains only the labels and find the label
                //with the string equal to the bytecode above's string
                for(int i = 0; i <=byteCodeVector.size(); i++) {
                     
                    //set the bytecodes label = to the lableByteCode's line number; 
                    if(programMap.get(i) != null && byteCodeVector.get(j).getLabel().equals(programMap.get(i).getLabel())){
                        byteCodeVector.get(j).setLabel(i); //setLabel will convert the integer in to a string
                       //Print Checking :   System.out.println(byteCodeVector.get(j).getName() + " " + byteCodeVector.get(j).getLabel());
                                              
                    }//end nested if stmt
                }//end nested for loop 
            }//end if stmt
        }//end for loop
    }//end resolveAddress
    
    
   
} //end Program class
