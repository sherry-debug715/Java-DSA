import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>(); 
        
        arrayList.add(10); // [10]
        System.out.println(arrayList);
        arrayList.add(0, 11); // [11, 10]
        System.out.println(arrayList);
        System.out.println(arrayList.get(0));

        for (int i = 0; i < arrayList.size(); i ++) {
            System.out.println(arrayList.get(i));
        }

        // update
        arrayList.set(1, 12);
        System.out.println(arrayList);
        
        // delete
        arrayList.remove(0);
        System.out.println(arrayList);
        arrayList.clear();
        System.out.println(arrayList);
    }
}


