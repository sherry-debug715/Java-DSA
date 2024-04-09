package Array;
import java.util.ArrayList;

public class ArrayListManager {
    /**
     * @param n: You should generate an array list of n elements.
     * @return: The array list your just created.
     */
    public ArrayList<Integer> create(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }
    
    /**
     * @param list: The list you need to clone
     * @return: A deep copyed array list from the given list
     */
    public ArrayList<Integer> clone(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        // for every number n of input list
        for (Integer n : list) newList.add(n);
        return newList;
    }
    
    /**
     * @param list: The array list to find the kth element
     * @param k: Find the kth element
     * @return: The kth element
     */
    public int get(ArrayList<Integer> list, int k) {
        return list.get(k);
    }
    
    /**
     * @param list: The array list
     * @param k: Find the kth element, set it to val
     * @param val: Find the kth element, set it to val
     */
    public void set(ArrayList<Integer> list, int k, int val) {
        list.set(k, val);
    }
    
    /**
     * @param list: The array list to remove the kth element
     * @param k: Remove the kth element
     */
    public void remove(ArrayList<Integer> list, int k) {
        list.remove(k);
    }
    
    /**
     * @param list: The array list.
     * @param val: Get the index of the first element that equals to val
     * @return: Return the index of that element
     */
    public int indexOf(ArrayList<Integer> list, int val) {
        if (list == null) return -1;
        return list.indexOf(val);
    }
}
