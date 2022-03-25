import javax.swing.SwingWorker;
import java.util.List;
import java.util.ArrayList;
 
public class SwingWorker {
  
   public SwingWorker() {

   }
   
   private void todo(){
   
     SwingWorker<String, Integer> worker = new SwingWorker<String, Integer> () {
     
        @Override // note that the return type matches the SwingWorker first type, 
                  // can be Void (capital V)
                  
        protected String doInBackground() {
        
    //    publish((Integer) 34);
        return null;
        }
        
        
       @Override  // executes on publish, but not for EVERY publish
                  // Note that the List datatype matches the SwingWorker second type
                  
        protected void process(List<Integer> digits) {
        }
     
        @Override  // executes when finished
        protected void done() {
        
        }
        
     };
     
     worker.execute();
   }
                  
   public static void main(String[] args) {
      new SwingWorker();
   }
}