import java.util.Random;
import java.util.concurrent.*;

public class TaskProducer implements Runnable {
    private Random ID = new Random();
    private BlockingQueue<Task> filaDeTarefas;
    
    public TaskProducer(BlockingQueue<Task> filaDeTarefas){
        this.filaDeTarefas = filaDeTarefas;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            filaDeTarefas.put(new Task(ID.nextInt(), "producer-"+Thread.currentThread().getId()));

        }catch(Exception e){
            System.err.println(e);
        }
    }
}