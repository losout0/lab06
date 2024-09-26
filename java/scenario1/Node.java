import java.util.concurrent.*;

public class Node implements Runnable {
    private ConcurrentMap<String, String> results;
    private BlockingQueue<Task> filaDeTarefas;

    public Node(ConcurrentMap<String, String> results, BlockingQueue<Task> filaDeTarefas){
      this.results = results;
      this.filaDeTarefas = filaDeTarefas;

    }

    @Override
    public void run() {
      Task task;
      while(true){
        try{
          task = filaDeTarefas.take();
          task.execute();
          results.put(task.producer, task.id.toString());
        }catch(Exception e){
          System.err.println(e);
        }
      }
    }
}
