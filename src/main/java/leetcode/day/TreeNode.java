package leetcode.day;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode {
    public final Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer val) {
        this.val = val;
    }
}
