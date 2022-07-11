@file:Suppress("NAME_SHADOWING")

package leetcode

import leetcode.Day20220618 as Day202206181

class Node(var `val`: Int) {
    var next: Node? = null
}

class Day20220618 {
    fun insert(head: Node?, insertVal: Int): Node? {
        var cur = head
        var next = head?.next
        if (cur == null || next == null) {
            val root = Node(insertVal)
            root.next = root
            return root
        }
        while (cur?.next !== head) {
            if (cur == null || next == null) continue
            if (insertVal in cur.`val`..next.`val`) {
                cur.next = Node(insertVal)
                cur.next?.next = next
                return head
            }
            if (next.`val` < cur.`val` && (insertVal >= cur.`val` || insertVal <= next.`val`)) {
                cur.next = Node(insertVal)
                cur.next?.next = next
                return head
            }
            cur = cur.next
            next = next.next
        }
        if (cur == null || next == null) {
            return head
        }
        cur.next = Node(insertVal)
        cur.next?.next = next
        return head
    }
}

fun main() {
    val cur = Day202206181()
    val root = Node(3)
    root.next = Node(5)
    root.next?.next = Node(1)
    root.next?.next?.next = root

    val insert = cur.insert(root, 0)
    insert.let {
        var cur = it
        while (cur?.next !== root) {
            println(cur?.`val`)
            cur = cur?.next
        }
        println(cur.`val`)
    }
}