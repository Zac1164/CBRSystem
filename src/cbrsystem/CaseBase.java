package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class CaseBase {
    private List<Case> cases;
    
    public CaseBase(){
        cases = new ArrayList<Case>();
    }
    
    public void add(Case newcase) {
        cases.add(newcase);
    }
    
    public List<Case> getCases(){
        return cases;
    }
    
     @Override
     public String toString() {
         String output = "1. CaseBase";
         for(Case c : cases){
             output += c + "";
         }
         return output;
     }
}
