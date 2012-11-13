package cbrsystem;
import java.util.*;


public class Feature {
    private String name;
    private String type;
    private Map properties;
    private double weight;
    
    public Feature(){
        properties = new HashMap<String,Property>();
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
       properties.put(property.getName(), property);
   }
    
}
