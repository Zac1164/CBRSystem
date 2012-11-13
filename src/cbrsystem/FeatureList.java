package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class FeatureList {
   private List<Feature> features;
   
   public FeatureList(){
       features = new ArrayList<Feature>();
   }
   
   public List<Feature> getFeatureList(){
       return features;
   }
   
   public void add(Feature feature){
       features.add(feature);
   }
   
}
