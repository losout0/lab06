import java.util.ArrayList;
import java.util.concurrent.*;

public class Node implements Runnable {
    private ConcurrentMap<String, String> results;
    private ConcurrentMap<Integer, ArrayList<Task>> filaDeTarefas;

    public Node(ConcurrentMap<String, String> results, ConcurrentMap<Integer, ArrayList<Task>> filaDeTarefas){
      this.results = results;
      this.filaDeTarefas = filaDeTarefas;

    }

    @Override
    public void run() {
      Task task = new Task(0, null);
      while(true){
        try{
          if(!filaDeTarefas.get(0).isEmpty()){
            task = filaDeTarefas.get(0).remove(0);
          }else if(!filaDeTarefas.get(1).isEmpty()){
            task = filaDeTarefas.get(1).remove(0);
          }else{
            task.execute();
          }
          results.put(task.producer, task.id.toString());
        }catch(Exception e){
          System.err.println(e);
        }
      }
    }
}
