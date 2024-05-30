package HashMap;
// Lintcode 1035
import java.util.HashMap;
import java.util.Map;

public class RabbitInForest {
    public int numRabbits(int[] answers) {
        // use map to to store what other rabbits says, and how many times a rabbit
        // has given the same answer 
        Map<Integer, Integer> rabbitSay = new HashMap<>();
        for (int n : answers) {
            int count = rabbitSay.getOrDefault(n, 0);
            rabbitSay.put(n, count + 1);
        }

        // iterate over what rabbit said and count min rabbits in the forest 
        int rabbitNum = 0;
        for (Map.Entry<Integer, Integer> e : rabbitSay.entrySet()) {
            // numPerGroup is the number a rabbit said + the rabbit itself.
            int numPerGroup = e.getKey() + 1;
            int replyCnt = e.getValue();

            // calculate how many groups the rabbits belongs to
            int numOfGroup = replyCnt / numPerGroup;
            // if getting answer from one rabbit without other rabbit, for exmaple:
            // {2: 1}, one rabbit said there are two other rabbits with the same color.
            // they belong to the same group. but 1 / 2 == 0 
            if (replyCnt % numPerGroup != 0) {
                numOfGroup += 1;
            } 

            rabbitNum += numOfGroup * numPerGroup;
        }
        return rabbitNum;
    }
}
