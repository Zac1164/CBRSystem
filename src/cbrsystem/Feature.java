/*Data structure for a feature (a definition of a fact)*/

package cbrsystem;
import java.util.List;
import java.util.ArrayList;


public class Feature implements Comparable{
    private String name; //attribute name
    private String type; //is attribute symbolic or numeric?
    private List<Property> properties; //collection of properties that define attribute
    private double informationGain; //used for sorting attributes
    private double weight;
    
    public Feature(){
        properties = new ArrayList<Property>();
    }
    
    public Feature(Feature f){
        this.properties = f.getPropertiesCopy();
        this.name = f.getName();
        this.type = f.getType();
        this.informationGain = f.getInformationGain();
        this.weight = f.getWeight();
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
    
    public List<Property> getPropertiesCopy(){
        return new ArrayList<Property>(properties);
    }
    
    public void setInformationGain(double informationGain){
        this.informationGain = informationGain;
    }
    
    public double getInformationGain(){
        return informationGain;
    }
    
    //Get number of properties
    public int getSize(){
        return properties.size();
    }
    
    @Override
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
