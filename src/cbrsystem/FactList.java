package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class FactList {
   private List<Fact> facts;
   
   public FactList(){
       facts = new ArrayList<Fact>();
   }
   
   public List<Fact> getFactList(){
       return facts;
   }
   
   public void add(Fact fact){
       facts.add(fact);
   }
   
}

