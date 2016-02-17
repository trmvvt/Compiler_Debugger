
/** 
 *  Author: Poushali Banerjee
 *  Last Update: May 5, 2010
*/

package Interpreter.debugger.UI;


public class Entries {

    private String sourceLine; //contains the source program line i for Vector slot i
    private Boolean isBreakptSet = false; 
    
    public Entries(){
        sourceLine = new String(); 
        isBreakptSet = false; 
    }
    public Entries(String s, Boolean b){
        sourceLine = s; 
        isBreakptSet = b; 
    }
   
    
    public void setSourceLine(String s){ sourceLine = s; }
    public void setBreakpt(Boolean b){ isBreakptSet = b; }
    public String getSourceLine (){ return sourceLine; }
    public Boolean getIsBreakptSet(){return isBreakptSet; } 
    
}
