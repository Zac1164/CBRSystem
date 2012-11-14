package cbrsystem;
import java.util.ArrayList;
import java.util.List;

public class FactList {
   private List<Fact> facts;
   
   public FactList(){
       facts = new ArrayList<Fact>();
   }
   
   public FactList(FactList fl){
       this.facts = fl.getFactListCopy();
   }
   
   public List<Fact> getFactList(){
       return facts;
   }
   
   public List<Fact> getFactListCopy(){
       return new ArrayList<Fact>(facts);
   }
   
   public void add(Fact fact){
       facts.add(fact);
   }
   
}

