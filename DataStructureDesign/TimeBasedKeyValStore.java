package DataStructureDesign;

import java.util.List;
import java.util.Map;

// Leetcode 981
// Time: O(logN)
// Space: O(N)
public class TimeBasedKeyValStore {
    class TimeVal {
        String val;
        int timestamp;
        public TimeVal(String _val, int _timestamp) {
            val = _val;
            timestamp = _timestamp;
        }
    }

    Map<String, List<TimeVal>> timeMap;
    public TimeMap() {
        timeMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) { // O(1)
        timeMap.putIfAbsent(key, new ArrayList<TimeVal>());
        timeMap.get(key).add(new TimeVal(value, timestamp));
    }
    
    public String get(String key, int timestamp) { // O(logN)
        // check key exist 
        if (!timeMap.containsKey(key)) {
            return "";
        }
        List<TimeVal> keyVal = timeMap.get(key);
        String val = findVal(keyVal, timestamp); // O(logN)
        if (val != null) {
            return val;
        }
        return findVal2(keyVal, timestamp); // O(logN)
    }

    private String findVal(List<TimeVal> keyVal, int timestamp) {
        int left = 0, right = keyVal.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (keyVal.get(mid).timestamp == timestamp) {
                return keyVal.get(mid).val;
            }
            if (keyVal.get(mid).timestamp > timestamp) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (keyVal.get(left).timestamp == timestamp) {
            return keyVal.get(left).val;
        }
        if (keyVal.get(right).timestamp == timestamp) {
            return keyVal.get(right).val;
        }
        return null;
    }

    private String findVal2(List<TimeVal> keyVal, int timestamp) {
        int left = 0, right = keyVal.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (keyVal.get(mid).timestamp >= timestamp) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (keyVal.get(right).timestamp < timestamp) {
            return keyVal.get(right).val;
        }
        if (keyVal.get(left).timestamp < timestamp) {
            return keyVal.get(left).val;
        }
        return "";
    }
}
