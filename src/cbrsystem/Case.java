/*Purpose: Data structure for a case*/

package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class Case implements Comparable{
    private String name; //name of case
    private List<Fact> factList; //facts embodied in case
    private Fact output; //output fact
    private double similarity; //similarity measure
    
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
    
    public double getSize(){
        return factList.size();
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
    
    //Check whether case contains fact given attribute name
    public Boolean contains(String fact){
        for(Fact f: factList){
            if(f.getAttribute().equals(fact)){
                return true;
            }
        }
        return false;
    }
    
    //Get fact from case using attribute name
    public Fact find(String fact){
        for(Fact f: factList){
            if(f.getAttribute().equals(fact)){
                return f;
            }
        }
        return new Fact();
    }
    
    @Override
    public int compareTo(Object c) {
        Case tmp = (Case)c;
		double aScore = this.getSimilarity();
		double bScore = tmp.getSimilarity();
		if(aScore > bScore){
			return -1;
		} 
		else if(aScore < bScore)
		{
			return 1;
		}
		else{
			return 0; 
		}
    }
    
    @Override
    public String toString() {
        String output2 = " [";
        for(int  i=0; i < factList.size(); i++){
            output2 += factList.get(i);
            if(i != (factList.size())){
                output2 += ",";
            }
        }
        output2 += output;
        output2 += "]";
        return output2;
    }
    
}
