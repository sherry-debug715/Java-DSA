package DataStructureDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

public class InsertDeleteGetRandomO1 {
    Map<Integer, Integer> idxMap;
    List<Integer> numList;

    public InsertDeleteGetRandomO1() {
        idxMap = new HashMap<>();
        numList = new ArrayList<>();
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        // write your code here
        // check if val is already in set 
        if (numList.contains(val)) {
            return false;
        }
        numList.add(val);
        idxMap.put(val, numList.size() - 1);
        return true;
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        // write your code here
        if (!numList.contains(val)) {
            return false;
        }
        // if the val is not the last element from numList, swap it with the 
        // last element, update their index on map, then remove it from array 
        // and list 1
        int index = idxMap.get(val);
        int lastIdx = numList.size() - 1;
        if (index != lastIdx) {
            int temp = numList.get(lastIdx);
            idxMap.put(temp, index);
            numList.set(index, temp);
        }
        // remove the val from idxMap and list 
        numList.remove(lastIdx);
        idxMap.remove(val);
        return true;
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        // write your code here
        // generate random index 
        Random random = new Random();
        int idx = random.nextInt(numList.size());
        return numList.get(idx);
    }
}
