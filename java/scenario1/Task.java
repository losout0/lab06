import java.util.Random;

public class Task {
    Long id;
    String producer;

    public Task(long id, String producer) {
        this.id = id;
        this.producer = producer;
    }

    public void execute() {
        try {
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
