package cbrsystem;
import java.util.HashMap;
import java.util.Map;

public class FeatureList {
    private Map<String, Feature> features;
    
    public FeatureList(){
        features = new HashMap<String,Feature>();
    }
    
    public FeatureList(FeatureList fl){
        features = fl.getFeatureList();
        
    }
        
    public Map<String,Feature> getFeatureList(){
        return new HashMap<String, Feature>(features);
    }
    
    public void add(Feature feature){
        features.put(feature.getName(),feature);
    }
    
    public void remove(String featureName){
        if(features.containsKey(featureName)){
            features.remove(featureName);
        }
    }
    
    public Feature find(String featureName){
        return features.get(featureName);
    }
    
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
