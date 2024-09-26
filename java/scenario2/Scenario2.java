import java.util.ArrayList;
import java.util.concurrent.*;

public class Scenario2 {
    public static void main(String[] args) {

        ConcurrentMap<Integer, ArrayList<Task>> filaDeTarefas = new ConcurrentHashMap<>();
    
        ConcurrentMap<String, String> results = new ConcurrentHashMap<>();

        ExecutorService nodeExecutor = Executors.newFixedThreadPool(3);
    
        ScheduledExecutorService producerExecutor0 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService producerExecutor1 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService producerExecutor2  = Executors.newSingleThreadScheduledExecutor();

        ScheduledExecutorService printResults = Executors.newSingleThreadScheduledExecutor();


        producerExecutor0.scheduleWithFixedDelay(new TaskProducer(filaDeTarefas, 0), 0, 5, TimeUnit.SECONDS);
        producerExecutor1.scheduleWithFixedDelay(new TaskProducer(filaDeTarefas, 1), 0, 5, TimeUnit.SECONDS);
        producerExecutor2.scheduleWithFixedDelay(new TaskProducer(filaDeTarefas, 2), 0, 5, TimeUnit.SECONDS);


        printResults.scheduleWithFixedDelay(() ->{
            for(String producer: results.keySet()){
                System.out.println(producer + ": " + results.get(producer));
            }
        }, 5, 5, TimeUnit.SECONDS);

        nodeExecutor.execute(new Node(results, filaDeTarefas));
        nodeExecutor.execute(new Node(results, filaDeTarefas));
        nodeExecutor.execute(new Node(results, filaDeTarefas));

    }
}
