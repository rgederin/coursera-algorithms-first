import edu.princeton.cs.algs4.*;
/**
 * Created by rgederin on 10/6/2016.
 */
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int count = Integer.parseInt(args[0]);
        String s = StdIn.readLine();

        for (String string : s.split(" ")) {
            randomizedQueue.enqueue(string);
        }

        if (count <= randomizedQueue.size()){
            for (int i = 0; i < count; i++) {
                System.out.println(randomizedQueue.dequeue());
            }
        }else{
            for (int i = 0; i < count; i++) {
                System.out.println(randomizedQueue.sample());
            }
        }

    }
}
