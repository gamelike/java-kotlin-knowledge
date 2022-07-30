package leetcode.competition;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author gjd
 */
public class SingleContent303 {
  //1
  public char repeatedCharacter(String s) {
    Set<Character> set = new HashSet<>();
    for (char ch : s.toCharArray()) {
      if (set.contains(ch)) {
        return ch;
      }
      set.add(ch);
    }
    return '0';
  }

  //2
  public int equalPairs(int[][] grid) {
    int n = grid.length;
    int result = 0;
    List<int[]> rows = new LinkedList<>();
    List<int[]> cols = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      int[] temp = new int[n];
      for (int j = 0; j < n; j++) {
        temp[j] = grid[j][i];
      }
      cols.add(temp);
      rows.add(grid[i]);
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (judge(cols.get(i), rows.get(j), n)) {
          result++;
        }
      }
    }
    return result;
  }

  boolean judge(int[] rows, int[] cols, int n) {
    for (int i = 0; i < n; i++) {
      if (rows[i] != cols[i]) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void run(){
    System.out.println(Integer.bitCount(2));
    System.out.println(Integer.bitCount(3));
  }

  //4
  public long countExcellentPairs(int[] nums, int k) {
    Long ans = 0L;
    Set<Integer> vis = new HashSet<>();
    Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
    for (int x : nums) {
      if (!vis.contains(x)) {
        vis.add(x);
        int bitNum = Integer.bitCount(x);
        cnt.put(bitNum, cnt.getOrDefault(bitNum, 0) + 1);
      }
    }
    for (Integer xKey : cnt.keySet()) {
      for (Integer yKey : cnt.keySet()) {
        if (xKey + yKey >= k) {
          ans = ans + cnt.get(xKey) * cnt.get(yKey);
        }
      }
    }
    return ans;
  }
}

//3
class FoodRatings {
  class Food {
    String name;
    int rate;
    String cuisine;

    public Food(String name, String cuisine, int rate) {
      this.name = name;
      this.cuisine = cuisine;
      this.rate = rate;
    }
  }

  Map<String, TreeSet<Food>> foodMenu = new HashMap<>();
  Map<String, Food> foodMap = new HashMap<>();

  public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
    for (int i = 0; i < foods.length; i++) {
      TreeSet<Food> foodTreeSet = foodMenu.getOrDefault(cuisines[i], new TreeSet<>((o1, o2) -> {
        if (o1.rate == o2.rate) {
          return o1.name.compareTo(o2.name);
        } else {
          return o2.rate - o1.rate;
        }
      }));
      Food food = new Food(foods[i], cuisines[i], ratings[i]);
      foodTreeSet.add(food);
      foodMenu.put(cuisines[i], foodTreeSet);
      foodMap.put(foods[i], food);
    }
  }

  public void changeRating(String food, int newRating) {
    Food currentFood = foodMap.get(food);
    TreeSet<Food> foodTreeSet = foodMenu.get(currentFood.cuisine); //获取到同一菜系下排序
    foodTreeSet.remove(currentFood);
    currentFood.rate = newRating;
    foodTreeSet.add(currentFood);
  }

  public String highestRated(String cuisine) {
    return foodMenu.get(cuisine).first().name;
  }

}
