package leetcode.array.slideWindows;

import java.util.LinkedHashMap;

/**
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 * <p>
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 * <p>
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果
 * 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。 给你一个整数数组 fruits
 * ，返回你可以收集的水果的 最大 数目。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/fruit-into-baskets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC904 {

  //确定边界的滑动窗口
  public int totalFruit(int[] fruits) {
    int slowIndex = 0;
    int subLength = 0;
    Count count = new Count();
    for (int quickIndex = 0; quickIndex < fruits.length; quickIndex++) {
      count.add(fruits[quickIndex], 1);  //叠加一次
      while (count.size() > 2) { //达到最大篮子
        count.add(fruits[slowIndex], -1);
        if (count.get(fruits[slowIndex]) == 0) {  //此篮子可以被使用了
          count.remove(fruits[slowIndex]);
        }
        slowIndex++;
      }
      subLength = Math.max((quickIndex - slowIndex + 1), subLength);
    }
    return subLength;
  }

  class Count extends LinkedHashMap<Integer, Integer> {

    public void add(int k, int v) {
      put(k, get(k) + v);
    }

    public int get(int k) {
      if (containsKey(k)) {
        return super.get(k);
      }
      return 0;
    }
  }
}
