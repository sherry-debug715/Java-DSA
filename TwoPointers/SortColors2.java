package TwoPointers;

public class SortColors2 {
        // based off quick sort 
        public void sortColors2(int[] colors, int k) {
            if (colors == null || colors.length == 0) {
                return;
            }
            quickSort(0, colors.length - 1, 1, k, colors);
        }
    
        private void quickSort(int start, int end, int colorFrom, int colorTo, int[] colors) {
            if (colorFrom == colorTo) {
                return;
            }
            if (start >= end) {
                return;
            }
    
            int left = start, right = end;
            int mid = (colorFrom + colorTo) / 2;
            while (left <= right) {
                while (left <= right && colors[left] <= mid) {
                    left += 1;
                }
                while (left <= right && colors[right] > mid) {
                    right -= 1;
                }
                if (left <= right) {
                    int temp = colors[left];
                    colors[left] = colors[right];
                    colors[right] = temp;
                    left += 1;
                    right -= 1;
                }
            }
    
            quickSort(start, right, colorFrom, mid, colors);
            quickSort(left, end, mid + 1, colorTo, colors);
        }
}
