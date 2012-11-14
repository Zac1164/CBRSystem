package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class CaseBase {
    private List<Case> cases;
    
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
    
    public int getSize(){
        return cases.size();
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
