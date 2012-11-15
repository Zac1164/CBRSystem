/*Data structure for attribute property*/

package cbrsystem;

public class Property {
    private String name;
    private String value;
    
    public Property(){
    }
    
    public Property(Property p){
        this.name = p.getName();
        this.value = p.getValue();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getValue(){
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public String toString() {
        String output = " Prop Name: " + name + ", Prop Value: " + value + "\n";
        return output;
    }
    
}
