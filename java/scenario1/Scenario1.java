import java.util.concurrent.*;

public class Scenario1 {
    public static void main(String[] args) {
        int producerCounter = 0;

        BlockingQueue<Task> filaDeTarefas = new ArrayBlockingQueue<>(50);
    
        ConcurrentMap<String, String> results = new ConcurrentHashMap<>();

        ExecutorService nodeExecutor = Executors.newFixedThreadPool(3);
    
        ScheduledExecutorService producerExecutor = Executors.newScheduledThreadPool(5);

        ScheduledExecutorService printResults = Executors.newSingleThreadScheduledExecutor();

        producerExecutor.scheduleWithFixedDelay(new TaskProducer(filaDeTarefas), 0, 5, TimeUnit.SECONDS);

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
