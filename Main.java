       // Create an array to store the maximum probability for each node
       double[] maxProb = new double[n];
       Arrays.fill(maxProb, 0.0);  // Initialize all probabilities to 0.0
       maxProb[start_node] = 1.0;  // Probability of starting node is 1.0

       // Iterate 9 times (or you can use any number of iterations as needed)
       for (int i = 0; i < 9; ++i) {
           // Traverse all edges
           for (int j = 0; j < edges.length; ++j) {
               int u = edges[j][0];  // First node in the edge
               int v = edges[j][1];  // Second node in the edge
               double prob = succProb[j];  // Success probability for this edge

               // Update maxProb[v] if a better probability is found via u
               if (maxProb[u] * prob > maxProb[v]) {
                   maxProb[v] = maxProb[u] * prob;
               }

               // Update maxProb[u] if a better probability is found via v
               if (maxProb[v] * prob > maxProb[u]) {
                   maxProb[u] = maxProb[v] * prob;
               }
           }
       }

       // Return the maximum probability to reach the end node
       return maxProb[end_node];