package leetcode.day

val directions = arrayOf(arrayOf(0, 1), arrayOf(1, 0), arrayOf(-1, 0), arrayOf(0, -1))
fun uniquePathsIII(grid: Array<IntArray>): Int {
    var count = 0
    var startPointX = 0
    var startPointY = 0
    for ((i, gr) in grid.withIndex()) {
        for ((j, g) in gr.withIndex()) {
            if (g == -1) {
                continue
            } else if (g == 1) {
                startPointX = i
                startPointY = j
            }
            count++
        }
    }
    fun dfsUniquePathsIII(grid: Array<IntArray>, i: Int, j: Int, n: Int): Int {
        if (grid[i][j] == 2) {
            return if (n != 0) 0 else 1
        }
        var number = 0
        val tem = grid[i][j]
        grid[i][j] = -1
        for (direction in directions) {
            val nextI = i + direction[0]
            val nextJ = j + direction[1]
            if (nextI < grid.size && nextJ < grid[0].size && nextJ >= 0 && nextI >= 0 && grid[nextI][nextJ] != -1) {
                number += dfsUniquePathsIII(grid, nextI, nextJ, n - 1)
            }
        }
        grid[i][j] = tem
        return number
    }
    return dfsUniquePathsIII(grid, startPointX, startPointY, count - 1)
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    return if (list1 == null) return list2 else if (list2 == null) list1
    else {
        if (list1.`val` <= list2.`val`) {
            list1.next = mergeTwoLists(list1.next, list2)
            list1
        } else {
            list2.next = mergeTwoLists(list1, list2.next)
            list2
        }
    }
}

fun swapPairs(head: ListNode?): ListNode? {
    if (head == null) return null
    else if (head.next == null) return head
    var temNode = head
    var returnNode = head.next
    var preNode : ListNode? = null
    while (temNode?.next != null) {
        val nextNode = temNode.next
        temNode.next = nextNode!!.next
        nextNode.next = temNode
        if (preNode != null) {
            preNode.next = nextNode
        }
        preNode = temNode
        temNode = temNode.next
    }
    return returnNode
}