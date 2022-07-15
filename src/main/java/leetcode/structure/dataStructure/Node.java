package leetcode.structure.dataStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author violet
 */
@NoArgsConstructor
@AllArgsConstructor
public class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
