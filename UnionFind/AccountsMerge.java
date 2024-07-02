package UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Lintcode 1070 
// Time: O(n)
// Space: O(n)
public class AccountsMerge {
    class UnionFind {
        Map<String, String> parents = new HashMap<>();
        Map<String, String> owners = new HashMap<>();
        
        public UnionFind(List<List<String>> accounts) {
            // build connection between each person and each emails, owners should keep track of 
            // owners of each email 
            for (List<String> account : accounts) {
                String name = account.get(0);
                for (int i = 1; i < account.size(); i++) {
                    parents.put(account.get(i), account.get(i));
                    owners.put(account.get(i), name);
                }
            }
        }

        private String find(String x) {
            String parent = parents.get(x);
            while (!parent.equals(parents.get(parent))) {
                parent = parents.get(parent);
            }

            String fa = parents.get(x);
            String temp = null;
            while (!fa.equals(parents.get(fa))) {
                temp = parents.get(fa);
                parents.put(fa, parent);
                fa = temp;
            }
            return parent;
        }

        private void union(String x, String y) {
            String p1 = find(x);
            String p2 = find(y);
            if (p1.equals(p2)) {
                return;
            }
            parents.put(p1, p2);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Step 1: initilize union find and populate parents and owners 
        UnionFind uf = new UnionFind(accounts);

        // Step 2: build connections 
        for (List<String> account : accounts) {
            for (int i = 2; i < account.size(); i++) {
                uf.union(account.get(i), account.get(i - 1));
            }
        }

        // Step 3, associate emails of the same root in union 
        Map<String, HashSet<String>> emailGroup = new HashMap<>();
        for (List<String> account : accounts) {
            String root = uf.find(account.get(1));
            emailGroup.putIfAbsent(root, new HashSet<String>());
            for (int i = 1; i < account.size(); i++) {
                emailGroup.get(root).add(account.get(i));
            }
        }

        // Step 4, forming result by changing emailGroup key to owner name 
        List<List<String>> output = new ArrayList<>();
        for (String email : emailGroup.keySet()) {
            String ownerName = uf.owners.get(email);
            List<String> emails = new ArrayList<>(emailGroup.get(email));
            Collections.sort(emails);
            emails.add(0, ownerName);
            output.add(emails);
        }

        return output;
    }
}

// father = {
// "johnsmith@mail.com":"john00@mail.com",
// "john00@mail.com":"john_newyork@mail.com",
// "johnnybravo@mail.com":"johnnybravo@mail.com",
// "john_newyork@mail.com":"john_newyork@mail.com",
// }

// owner = {
// "johnsmith@mail.com":"John",
// "john00@mail.com":"John",
// "johnnybravo@mail.com":"John",
// "john_newyork@mail.com":"John",
// "mary@mail.com":"Mary"
// }

// mp = {
// "john_newyork@mail.com": {"johnsmith@mail.com", "john00@mail.com","john_newyork@mail.com"},
//  "johnnybravo@mail.com": {"johnnybravo@mail.com"},
//  "mary@mail.com": {"mary@mail.com"}
// }
