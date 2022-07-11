package leetcode

import kotlin.math.max

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Day20220619 {
    val result = mutableMapOf<Int, Int>()
    private var maxCnt : Int = 0
    fun findFrequentTreeSum(root: TreeNode?): IntArray {
        calc(root)
        val list = mutableListOf<Int>()
        for ((c, v) in result) {
            if (v == maxCnt) {
                list.add(c)
            }
        }
        return list.toIntArray()
    }

    private fun calc(root: TreeNode?): Int {
        if (root == null) return 0
        val sum = root.`val` + calc(root.left) + calc(root.right)
        result[sum] = result.getOrDefault(sum, 0) + 1
        maxCnt = maxCnt.coerceAtLeast(result[sum]!!)
        return sum
    }

}