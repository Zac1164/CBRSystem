package cbrsystem;

import java.util.ArrayList;

public class Fact{
    private String attribute;
    private String value;
    
    public Fact(){
    }
    
    public Fact(Fact f){
        this.attribute = f.getAttribute();
        this.value = f.getValue();
    }
    
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
        String output = "<" + attribute + "," + value + ">";
        return output;
    }
    
    @Override
    public boolean equals(Object obj){
        Fact f = (Fact)obj;
        return this.getAttribute().equals(f.getAttribute());
    }
}

