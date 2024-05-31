package PrefixSum;
// lintcode 560
public class FriendshipService {
    Map<Integer, Set<Integer>> following; // use TreeSet for maintaining order 
    Map<Integer, Set<Integer>> follower;
    public FriendshipService() {
        // do initialization if necessary
        following = new HashMap<>();
        follower = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) { // O(N)
        if (!follower.containsKey(user_id)) {
            return new ArrayList<Integer>();
        }

        return new ArrayList<Integer>(follower.get(user_id));
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) { // O(N)
        // write your code here
        if (!following.containsKey(user_id)) {
            return new ArrayList<Integer>();
        }
        return  new ArrayList<Integer>(following.get(user_id));
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) { // O(logN) for add to TreeSet
        following.putIfAbsent(from_user_id, new TreeSet<Integer>());
        following.get(from_user_id).add(to_user_id);
        follower.putIfAbsent(to_user_id, new TreeSet<Integer>());
        follower.get(to_user_id).add(from_user_id);
    }
    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {// O(logN) for remove from TreeSet
        // remove from following 
        if (following.containsKey(from_user_id)) {
            following.get(from_user_id).remove(to_user_id);
        }
        // remove from follower 
        if (follower.containsKey(to_user_id)) {
            follower.get(to_user_id).remove(from_user_id);
        }
    }
}
