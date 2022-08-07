package leetcode.greedy;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gjd
 */
public class LC1408 {
  public List<String> stringMatching(String[] words) {
    int length = words.length;
    Set<String> result = new LinkedHashSet<>();
    boolean[] flag = new boolean[length];
    for (int i = 0; i < length; i++) {
      if (flag[i]) {
        continue;
      }
      for (int j = 0; j < length; j++) {
        if (i == j || flag[j]) {
          continue;
        }
        if (words[i].contains(words[j])) {
          flag[j] = true;
          result.add(words[j]);
        }
      }
    }
    return result.stream().collect(Collectors.toList());
  }

  @Test
  public void run() {
    stringMatching(new String[]{"mass", "as", "hero", "superhero"});
  }
}
