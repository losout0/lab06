import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class TaskProducer implements Runnable {
    private Random ID = new Random();
    private ConcurrentMap<Integer, ArrayList<Task>> filaDeTarefas;
    Integer priority;

    public TaskProducer(ConcurrentMap<Integer, ArrayList<Task>> filaDeTarefas, Integer priority) {
        this.filaDeTarefas = filaDeTarefas;
        this.priority = priority;
    }

    @Override
    public void run() {
        try {
            switch (priority) {
                case 0:
                    Thread.sleep(13000);
                    break;
                case 1:
                    Thread.sleep(7000);
                    break;
                case 2:
                    Thread.sleep(3000);
                    break;
            }
            filaDeTarefas.get(priority).add(new Task(ID.nextInt(), "producer-" + Thread.currentThread().getId()));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}