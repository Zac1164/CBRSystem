package cbrsystem;
import java.util.List;
import java.util.ArrayList;


public class Feature implements Comparable{
    private String name;
    private String type;
    private List properties;
    private double informationGain;
    private double weight;
    
    public Feature(){
        properties = new ArrayList<Property>();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public void addProperty(Property property){
        properties.add(property);
    }
    
    public List<Property> getProperties(){
        return properties;
    }
    
    public void setInformationGain(double informationGain){
        this.informationGain = informationGain;
    }
    
    public double getInformationGain(){
        return informationGain;
    }
    
    public int compareTo(Object feature) {
        Feature tmp = (Feature)feature;
		double aScore = this.getInformationGain();
		double bScore = tmp.getInformationGain();
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
        String output = "{\n";
        output += "Name: " + name + "\n";
        output += "Type: " + type + "\n";
        output += "Properties: \n";
        for(int  i=0; i < properties.size(); i++){
            output += properties.get(i);
        }
        output += "}";
        return output;
    }

}
