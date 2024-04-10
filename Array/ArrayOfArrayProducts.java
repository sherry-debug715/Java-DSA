package Array;

public class ArrayOfArrayProducts {
    static int[] arrayOfArrayProducts(int[] arr) {
        if (arr == null || arr.length <= 1) return new int[0];
        
        int arrSize = arr.length;
        int[] prefix = new int[arrSize];
        int[] suffix = new int[arrSize];
        int[] output = new int[arrSize];
        prefix[0] = 1;
        suffix[arrSize - 1] = 1;
        
        // fill prefix[i] with the product of Arrays.copyOfRange(arr, 0, i)
        for (int i = 1; i < arrSize; i++) {
          prefix[i] = prefix[i - 1] * arr[i - 1];
        }
        
        // fill suffix[i] with the product of Arrays.copyOfRange(arr, i + 1, arrSize)
        for (int i = arrSize - 2; i >= 0; i--) {
          suffix[i] = suffix[i + 1] * arr[i + 1];
        }
        
        for (int i = 0; i < arrSize; i++) {
          output[i] = prefix[i] * suffix[i];
        }
        
        return output;
      }
}
