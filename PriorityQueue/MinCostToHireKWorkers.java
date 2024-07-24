package PriorityQueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;

// lintcode 1512
// Time: O(n log(n + k));
// Space: O(n + k);
public class MinCostToHireKWorkers {
    class Pair {
        double unitCost;
        int quality;
        public Pair(double _unitCost, int _quality) {
            unitCost = _unitCost;
            quality = _quality;
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Pair[] perQualityWage = new Pair[n];
        for (int i = 0; i < n; i++) { // O(n)
            double qualityCost = (double) wage[i] / quality[i];
            perQualityWage[i] = new Pair(qualityCost, quality[i]);
        }

        Arrays.sort(perQualityWage, (a, b) -> { // O(nlogn)
            return Double.compare(a.unitCost, b.unitCost);
        });

        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        double price = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) { // O(nlogk)
            maxHeap.offer(perQualityWage[i].quality);
            sum += perQualityWage[i].quality;
            if (maxHeap.size() > k) {
                sum -= maxHeap.poll();
            }
            if (maxHeap.size() == k) {
                price = Math.min(price, sum * perQualityWage[i].unitCost);
            }
        }

        return price;
    }
}
