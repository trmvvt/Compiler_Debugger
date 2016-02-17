/* Called by the Interpreter if the user sets the debugger to ON. 
 * This object will print the source program followed by a prompt 
 * to wait for user commands.  Will continue until user is finished 
 * with interaction.  At that point, UI calls the execute method of the 
 * DebuggerVM.   
 * Also holds the help menu to display to the user.  
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
 */

package Interpreter.debugger.UI;

import Interpreter.ByteCodeLoader;
import Interpreter.Program;
import Interpreter.debugger.DebuggerVM;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;



public class UI {

    private BufferedReader source;
    private BufferedReader input; 
    //private ByteCodeLoader bcl; 
    private String temp; 
    private DebuggerVM vm = new DebuggerVM(); 
    private int PC = 0; 
    private Vector<Entries> entries = new Vector (); 
    //private Entries key = new Entries (); 
    private Boolean runningFlag = true; 
    int start = 0; 
    //Vector v = new Vector (); //to hold the source program
    
    
    
    public UI(){}; //default constructor 
    
    //constructor with one argument: the program file
    public UI(String programFile) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        programFile += ".x";  
        source = new BufferedReader(new FileReader(programFile));
        //add the source program to the vector of Entries
        String s = source.readLine(); 
        
        while (s != null){
            Entries key = new Entries(); 
            key.setSourceLine(s); 
            key.setBreakpt(false);
            entries.add(key); //add this entry to the vector of entries
            s = source.readLine();            
        }
    } 
        
    public void UImain(DebuggerVM v) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
            vm = v; 
            
            this.UIcommands();
            
            while (runningFlag){
                vm.executeProgram();
                this.UIcommands();
            }        
    }
    
    public void UIcommands() throws IOException{
        Boolean flag = true; 
        vm.setIsSet(false); //sets the isSet bool flag to false
        vm.setRollbackFlag(false); 
        //if it's the first time we're calling the UI, then print entire source code
        if (start ==0){ printSrcFile(); 
        } else { printCurrentFunc(); }
        start++; 
        do {
        System.out.println("Please enter a command.  (? for Help Menu): ");
        input = new BufferedReader(new InputStreamReader(System.in)); 
        String s = input.readLine(); 
        StringTokenizer st = new StringTokenizer(s); 
        temp = st.nextToken(); 
            if(temp.equals("?")){
                helpMenu(); 
            } else if (temp.equals("Set") || temp.equals("set")){
                 while(st.hasMoreTokens()){
                     temp = st.nextToken(); 
                     int i = Integer.parseInt((String)temp);
                     //check if this is a valid spot for a breakpoint, if it is, then
                     //it sets the LineCode BreakPt == to true
                     if(vm.isBreakPtValid(i)){
                         Entries key = new Entries(); 
                         key = entries.get(i-1); 
                         key.setBreakpt(true); //sets isBreakptSt == to true in Entries
                         System.out.println("Breakpoint set at Line "+i);
                     } else {
                         System.out.println("Line "+i+ " is not a valid breakpoint.  No breakpoint was set here."); 
                     }//end if else stmt
                }//end while loop
            } else if (temp.equals("Clear") || temp.equals("clear")){
                temp = st.nextToken(); 
                //clears all the breakpoints
                if(temp.equals("ALL") || temp.equals("All") || temp.equals("all")){
                    for (int i = 0; i < entries.size(); i++){
                        Entries key = new Entries();
                        key = entries.elementAt(i); 
                        key.setBreakpt(false);
                        vm.clearBreakPt(i);
                    }
                    System.out.println("All Breakpoints have been cleared"); 
                } else { //clear selected breakpoints
                    int i = Integer.parseInt((String)temp);
                    Entries key = new Entries(); 
                    key = entries.get(i); 
                    key.setBreakpt(false);
                    while(temp!=null){
                        i = Integer.parseInt((String)temp);
                        vm.clearBreakPt(i);
                        key = entries.get(i-1); 
                        key.setBreakpt(false);
                        System.out.println("Breakpoint cleared at "+ i);
                        if(st.hasMoreTokens()){
                            temp = st.nextToken();
                        } else { temp = null;}
                    }//end while statement
                } //end imbeded if else statement  
            } else if (temp.equals("Disp") || temp.equals("disp")){
                temp = st.nextToken(); 
                if (temp.equals("V") || temp.equals("v")){
                    //display current variable(s)
                    System.out.print("current varable(s):  "); 
                    vm.printVars();
                } else if( temp.equals("F") || temp.equals("f")){
                    //display the current function
                    printCurrentFunc();  
                } else if (temp.equals("src")|| temp.equals("Src")){
                        printSrcFile(); 
                    }
            } else if (temp.equals("Continue") || temp.equals("continue")){
                System.out.println("Continuing execution"); 
                return; 
            } else if (temp.equals("Halt") || temp.equals("halt")){
                System.out.println("Quiting the debugger");  
                setFlag(false); 
                return;  
            } else if (temp.equals("Step")|| temp.equals("step")){
                Step step = new Step(); 
                temp = st.nextToken(); 
                if(temp.equals("Out")|| temp.equals("out")){ 
                    step.stepOut(vm);
                    return; 
                }else if(temp.equals("Into")||temp.equals("into")){
                    step.stepInto(vm);
                    return; 
                } else if(temp.equals("Over")|| temp.equals("over")){
                    //step.stepOver(vm); 
                }
            } else if(temp.equals("ChangeV")|| temp.equals("changev") || temp.equals("Changev")) {
                //need to implement this.  Have to tokenize to get the symbol and 
                //the value to change it to
                while(st.hasMoreTokens()){
                    temp = st.nextToken();
                    int temp2; 
                    temp2 = Integer.parseInt(st.nextToken()); 
                    vm.changeVarValue(temp, temp2);
                }
            } else if (temp.equals("rollback")){
                vm.setRollbackFlag(true); 
                vm.loadRollback();
                return; 
            } else {
                System.out.println("Command not recognized.  Please try again."); 
            }
        } while (flag); 
    }
    
    //Print the source file with
    public void printSrcFile() throws IOException{
        int j = vm.getCurrentLine(); 
        
        if (j<0){
            System.out.print("**********");
            System.out.print(vm.getFuncName());
            System.out.println("**********");
        } else {
             for(int i = 0; i<entries.size(); i++){            
                if(entries.elementAt(i).getIsBreakptSet()){
                    System.out.print("*"); 
                } else {
                    System.out.print(" ");
                }
                System.out.print((i+1) + ". ");  //print line number  
                int k = i; 
                if ((i+1) == j ){
                    System.out.print(entries.elementAt(i).getSourceLine());
                    System.out.println("  <--------"); 
                }else {
                    System.out.println(entries.elementAt(i).getSourceLine()); 
                }
            }//end for loop
        }//end if else stmt
       
    }
    
    public void printCurrentFunc(){
        int x = vm.getStartLine(); 
        int y = vm.getEndLine(); 
        int j = vm.getCurrentLine(); 
        if (j<0){
            System.out.print("**********");
            System.out.print(vm.getFuncName());
            System.out.println("**********");
        } else {
            for(int i = x-1; i< y; i++){            
              if(entries.elementAt(i).getIsBreakptSet()){
                  System.out.print("*"); 
              } else {
                  System.out.print(" ");
              }//end if else
              System.out.print((i+1) + ". ");  //print line number  
              if((i+1)==j){
                  System.out.print(entries.elementAt(i).getSourceLine());
                  System.out.println("  <--------");
              }else {
                 System.out.println(entries.elementAt(i).getSourceLine()); 
              }//end if else
            }//end for
        }//end if else
    }//end print function
    
    public void helpMenu(){
         
         System.out.println("COMMAND    ARGUMENT      DESCRIPTION"); 
         System.out.println("------------------------------------"); 
         System.out.println("H                        Help Menu "); 
         System.out.println("Set         #            Set a Breakpoint at Line #. Separate multiple line number with a space."); 
         System.out.println("           NOTE: # must be a valid line number for a breakpoint");
         System.out.println("Clear       #            Clear a Breakpoint at Line #. Separate multiple line number with a space.");
         System.out.println("           NOTE: # must be a valid line number for a breakpoint");
         System.out.println("Clear      ALL           Clear all Breakpoints"); 
         System.out.println("ChangeV    n #           Change variable n to the value #"); 
         System.out.println("Disp        F            Display the current function ");
         System.out.println("Disp        V            Display Variable(s)");
         System.out.println("Disp       Src           Display the source file"); 
         System.out.println("Step       Out           Step out of the current function"); 
         System.out.println("Step       Into          Step into the function on the current line"); 
         System.out.println("Step       Over          Step over the next line"); 
         System.out.println("Continue                 Continue execution ");
         System.out.println("Halt                     Halt execution ");
         System.out.println("Q                        Quit execution of the program"); 
         System.out.println(" "); 
                  
    }
    
    public void setFlag(Boolean b){
        runningFlag = b; 
    }
    
}
