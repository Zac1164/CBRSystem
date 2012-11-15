/*Data structure for case base (a collection of cases)*/

package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class CaseBase {
    private List<Case> cases; //list of cases embodied in case base
    
    public CaseBase(){
        cases = new ArrayList<Case>();
    }
    
    public CaseBase(CaseBase cb){
        this.cases = cb.getCasesCopy();
    }
    
    public void add(Case newcase) {
        cases.add(newcase);
    }
    
    public List<Case> getCases(){
        return cases;
    }
    
    public List<Case> getCasesCopy(){
        return new ArrayList<Case>(cases);
    }
    
    //Returns number of cases in case base*/
    public int getSize(){
        return cases.size();
    }
    
    /* Find a case in the case base given the case's name*/
    public Case find(String inputCase){
        for(Case c: cases){
            if(c.getName().equals(inputCase)){
                return c;
            }
        }
        return new Case();
    }
    
    @Override
    public String toString() {
        String output = "Case Base: \n{\n";
        for(int  i=0; i < cases.size(); i++){
            output += cases.get(i);
            if(i != (cases.size() - 1)){
                output += ",\n";
            }
        }
        output += "\n}";
        return output;
    }
}
