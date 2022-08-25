package leetcode.competition;

import org.junit.Test;

import java.util.*;

/**
 * @author violet
 * @since 2022/7/24
 */
public class Single303 {

    public int equalPairs(int[][] grid) {
        int ans = 0;
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            int j = 0;
            for (int k = 0; k < n; k++) {
                while (j < n && grid[j][i] == grid[k][j]) {
                    j++;
                }
                if (j == n) {
                    ans++;
                    j = 0;
                }
            }
        }

        return ans;
    }

    static class FoodRatings {
        Map<String, Integer> rate = new HashMap<>();
        Map<String, String> toC = new HashMap<>();


        Map<String, TreeSet<String>> map = new HashMap<>();

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

            int n = foods.length;

            for (int i = 0; i < n; i++){
                rate.put(foods[i], ratings[i]);
                toC.put(foods[i], cuisines[i]);
            }
            for (int i = 0; i < n; i++){
                if (!map.containsKey(cuisines[i])){
                    map.put(cuisines[i], new TreeSet<>((o1, o2) -> {
                        int c1 = rate.get(o1),  c2 = rate.get(o2);
                        if (c1 != c2){
                            return c2 - c1;
                        }
                        return o1.compareTo(o2);
                    }));
                }
                map.get(cuisines[i]).add(foods[i]);
            }
        }

        public void changeRating(String food, int newRating) {
            String cus = toC.get(food);
            TreeSet<String> strings = map.get(cus);
            strings.remove(food);
            rate.put(food, newRating);
            strings.add(food);

        }

        public String highestRated(String cuisine) {
            TreeSet<String> strings = map.get(cuisine);
            return strings.first();
        }
    }

}
