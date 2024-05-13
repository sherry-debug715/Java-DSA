package HashMap;
// Leetcode problem 1169: https://leetcode.com/problems/invalid-transactions/
// Time: O(N * M) where N is the length of transactions and m is the longest length of recordList
// Spaze: O(N) where n is the length of recordMap;
public class InvalidTransactions {
    class Record {
        String name;
        int time;
        int amount;
        String city;
        public Record(String transactions) {
            String[] record = transactions.split(",");
            name = record[0];
            time = Integer.parseInt(record[1]);
            amount = Integer.parseInt(record[2]);
            city = record[3];
        }
    }
    
    public List<String> invalidTransactions(String[] transactions) {
        List<String> output = new ArrayList<>();
        if (transactions.length == 0) {
            return output;
        }
        
        Map<String, List<Record>> recordMap = new HashMap<>();
        // popoulate recordMap and build all record of each person.
        for (String transaction : transactions) {
            Record t = new Record(transaction);
            recordMap.putIfAbsent(t.name, new ArrayList<Record>());
            recordMap.get(t.name).add(t);
        }
        
        // now iterate over transactions again, for each transaction check is validality
        for (String transaction : transactions) {
            Record t = new Record(transaction);
            if (!inValid(t, recordMap.get(t.name))) {
                output.add(transaction);
            }
        }
        return output;
    }
    
    private boolean inValid(Record transaction, List<Record> recordList) {
        if (transaction.amount > 1000) {
            return false;
        }
        for (Record record : recordList) {
            // if coming across the same record, city equals.
            if (Math.abs(record.time - transaction.time) <= 60 && !record.city.equals(transaction.city)) {
                return false;
            }
        }
        return true;
    }
}
