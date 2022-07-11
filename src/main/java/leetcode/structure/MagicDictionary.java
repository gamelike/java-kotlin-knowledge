package leetcode.structure;

import org.junit.Test;

/**
 * leetcode 676. 前缀树匹配问题.
 *
 * @author violet
 */
public class MagicDictionary {

    final MagicDictionary[] magicDictionaries;
    // 标志结束位
    boolean flag;

    public MagicDictionary() {
        this.magicDictionaries = new MagicDictionary[26];
        this.flag = false;
    }

    /**
     * builder tire tree.
     */
    public void buildDict(String[] dictionary) {
        for (var word : dictionary) {
            insertTire(word);
        }
    }

    /**
     * insert word.
     */
    private void insertTire(String word) {
        var head = this;
        for (int i = 0; i < word.length(); i++) {
            if (head.magicDictionaries[word.charAt(i) - 'a'] == null) {
                head.magicDictionaries[word.charAt(i) - 'a'] = new MagicDictionary();
            }
            head = head.magicDictionaries[word.charAt(i) - 'a'];
        }
        head.flag = true;
    }

    public boolean search(String searchWord) {
        return dfs(this, searchWord, 0, false);
    }

    public boolean dfs(MagicDictionary root, String searchWord, int pos, boolean modified) {
        if (pos == searchWord.length()) {
            return modified && root.flag;
        }
        MagicDictionary next = root.magicDictionaries[searchWord.charAt(pos) - 'a'];
        if (next != null) {
            if (dfs(next, searchWord, pos + 1, modified)) {
                return true;
            }
        }

        if (!modified) {
            for (int i = 0; i < root.magicDictionaries.length; i++) {
                if (searchWord.charAt(pos) - 'a' == i || root.magicDictionaries[searchWord.charAt(i) - 'a'] == null) continue;
                if (dfs(root.magicDictionaries[i], searchWord, pos + 1, true)) {
                    return true;
                }
            }
        }
        return false;
    }

}
