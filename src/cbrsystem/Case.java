
package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class Case {
    private String name;
    private List<Fact> factList;
    private Fact output;
    private double similarity;
    
    public Case(){
        factList = new ArrayList<Fact>();
    }
    
    public Case(Case c){
        this.name = c.getName();
        this.output = c.getOutputCopy();
        this.similarity = c.getSimilarity();
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
    
    public Fact getOutputCopy(){
        return new Fact(output);
    }
    
    public void add(Fact fact){
        factList.add(fact);
    }
    
    public void remove(Fact fact){
        if(factList.contains(fact)){
            factList.remove(fact);
        }
    }
    
    public void setSimilarity(double similarity){
        this.similarity = similarity;
    }
    
    public double getSimilarity(){
        return similarity;
    }
    
    public List<Fact> getFactList(){
        return factList;
    }
    
    public List<Fact> getFactListCopy(){
        return new ArrayList<Fact>(factList);
    }
    
    public Boolean contains(String fact){
        for(Fact f: factList){
            if(f.getAttribute().equals(fact)){
                return true;
            }
        }
        return false;
    }
    
    public Fact find(String fact){
        for(Fact f: factList){
            if(f.getAttribute().equals(fact)){
                return f;
            }
        }
        return new Fact();
    }
    
    @Override
    public String toString() {
        String output2 = " [";
        for(int  i=0; i < factList.size(); i++){
            output2 += factList.get(i);
            if(i != (factList.size() - 1)){
                output2 += ",";
            }
        }
        output2 += "]";
        return output2;
    }
    
}
