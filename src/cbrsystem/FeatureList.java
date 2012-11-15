/*Datastructure for keeping track of feature definitions*/

package cbrsystem;
import java.util.HashMap;
import java.util.Map;

public class FeatureList {
    private Map<String, Feature> features; //map used for quicker lookup
    
    public FeatureList(){
        features = new HashMap<String,Feature>();
    }
    
    public FeatureList(FeatureList fl){
        features = fl.getFeatureListCopy(); 
    }
        
    public Map<String,Feature> getFeatureList(){
        return features;
    }
    
    public Map<String,Feature> getFeatureListCopy(){
        return new HashMap<String,Feature>(features);
    }
    
    public void add(Feature feature){
        features.put(feature.getName(),feature);
    }
        
    //Remove feature definition based on attribute name
    public void remove(String featureName){
        if(features.containsKey(featureName)){
            features.remove(featureName);
        }
    }
    
    //Retrieve feature definition based on attribute name
    public Feature find(String featureName){
        return features.get(featureName);
    }
    
    
    //Returns number of feature definitions
    public int getSize(){
        return features.size();
    }
    
    @Override
    public String toString() {
        Feature[] featureArray = new Feature[features.size()];
        featureArray = features.values().toArray(new Feature[0]);
        String output = "Feature Definitions: \n";
        for(int  i=0; i < featureArray.length; i++){
            output += featureArray[i];
            if(i != (featureArray.length - 1)){
                output += ",\n";
            }
        }
        output += "";
        return output;
    }

}
