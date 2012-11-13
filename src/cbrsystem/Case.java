
package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class Case {
    private String name;
    private List<Fact> factList;
    private Fact output;
    
    public Case(){
        factList = new ArrayList<Fact>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setOutput(Fact output){
        this.output = output;
    }
    
    public Fact getOutput(){
        return output;
    }
    
    public void add(Fact fact){
        factList.add(fact);
    }
    
    @Override
    public String toString() {
        String output2 = "\n2. Case\n3. Case Name: " + name + "\n3. Output: " + output;
        for(Fact f : factList){
            output2 += f;
        }
        return output2;
    }
}
