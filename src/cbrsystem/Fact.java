package cbrsystem;

import java.util.ArrayList;

public class Fact{
    private String attribute;
    private String value;
    
    public String getAttribute(){
        return attribute;
    }
    
    public void setAttribute(String attribute){
        this.attribute = attribute;
    }
    
    public String getValue(){
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    @Override
    public String toString() {
        String output = "\n4. Fact\n5. Attribute: " + attribute + "\n5. Value: " + value;
        return output;
    }
}

