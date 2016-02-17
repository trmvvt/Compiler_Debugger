/**
 * Description: Records and processes and maintains the stack of active frames
 * Stores the active frames in a stack.  A vector holds the data 
 * to be stored while the stack segments the data in to frames. 
 * When we call a function we'll push a new frame on the stack.  When we
 * return from a function, we'll pop the top frame. 
 * Author: Poushali Banerjee
 * Last Update: May 5, 2010
 */

package Interpreter;

import java.util.*;




public class RunTimeStack {
    
    Stack <Integer> framePointers = new Stack(); 
    Vector <Integer> runStack = new Vector(); 
    
    public RunTimeStack(){
        framePointers.add(0); 
    } //default constructor
    
    //Dumps the RunTimeStack info for debugging. 
    //Separates the runtime stacks in to frames. 
    public void dump(){
    
        //while printing out the values in the runStack vector, first check
        //that the position of that value is not a number in the framePointers stack
        System.out.print("["); //starting bracket
        
        for(int i = 0; i < runStack.size(); i++){
        
            System.out.print(runStack.elementAt(i)); //print the element at i
            
            if(framePointers.contains(i+1)){  //if there is a new frame at i+1
                System.out.print("] [");      //then print out brackets
            }
            else if ((i+1)< runStack.size()){ //else if the next value is not at the  
                System.out.print(",");        //end of the list, then print a "," 
            }
        }//end for loop
        
        System.out.println("]"); //final closing bracket
        
    } 
    
    //Returns the top item on the runtime stack. 
    public int peek(){
        int size = runStack.size(); 
        return runStack.get(size-1); 
    } 
    
    public int getValueAt(int i){
        return runStack.get(i); 
    }
    //Pops the top item from the runtime stack and returns it. 
    public int pop(){ 
        int size = runStack.size(); 
        int element = runStack.get(size-1); 
        runStack.removeElementAt(size-1); 
        return element; 
    }
    
    //Pushes an item on to the runtime stack and then returns
    //that item. 
    public int push(int i){
        runStack.addElement(i); 
        return i; 
    }
    
    // Offset indicates the number of slots down from the top 
    //of RunTimeStack for starting the new frame. 
    //starts a new frame. 
    public void newFrameAt(int offset){
        int size = runStack.size(); 
        int i = size - offset; //i is the location of the new frame pointer
        framePointers.addElement(i);
    } 
    
    //Pop the top frame when returning from a function.  Before popping, 
    //the function's return value is at the top of the stack so we'll save the 
    //value, pop the top frame and all the stack values up to that point
    //and then push the return value. 
    public void popFrame(){
        int i = runStack.lastElement(); //get the element at the top of the stack
        //need to pop elements from the stack until we reach the frame pointer
        int p = framePointers.peek(); //the location of the last element to be popped 
        int size = runStack.size(); //size of the runStack
        while(size != p){
            runStack.removeElementAt(size -1); //removes the last element
            size--;
        }
        runStack.add(i); //adds the popped element to the end of the stack
        framePointers.pop(); //removes the frame
    } 
    
    public int peekTopFrame(){
        return framePointers.peek(); 
    }
    
    //Pop the top of the stack; store the value into the frame an "offset" 
    //from the start of the frame
    public int store (int offset){
           
        int i = runStack.lastElement(); //store the top value in i
        int size = runStack.size();  
        size--; 
        runStack.removeElementAt(size);  //pops the top of the stack
        runStack.setElementAt(i, offset); //stores the value at the top, offset
                                          //positions away from the front of the stack. 
        return offset; 
    }
    
    //Used to load variables onto stack
    public int load(int offset){

        int p = framePointers.peek(); 
        int i = runStack.get(offset + p); //get the value at offset from the current frame
        runStack.addElement(i); //add that value to the top of the runstack
        return offset; 
    }
    
    //Used to load  literals onto the stack
    //eg. for lit 5, we'll call push with 5
    public Integer push(Integer i){
        runStack.addElement(i); 
        return i; 
    }
    
    public int getVectorSize(){
        return runStack.size();
    }

}
