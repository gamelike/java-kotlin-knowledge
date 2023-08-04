package leetcode.day

/**
 *
 */
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
    return dfsUniquePathsIII(grid, startPointX, startPointY, count - 1)
}

val directions = arrayOf(arrayOf(0, 1), arrayOf(1, 0), arrayOf(-1, 0), arrayOf(0, -1))
fun dfsUniquePathsIII(grid: Array<IntArray>, i: Int, j: Int, n: Int): Int {
    if (grid[i][j] == 2) {
        return if (n != 0) 0 else 1
    }
    var count = 0
    val tem = grid[i][j]
    grid[i][j] = -1
    for (direction in directions) {
        val nextI = i + direction[0]
        val nextJ = j + direction[1]
        if (nextI < grid.size && nextJ < grid[0].size && nextJ >= 0 && nextI >= 0 && grid[nextI][nextJ] != -1) {
            count += dfsUniquePathsIII(grid, nextI, nextJ, n - 1)
        }
    }
    grid[i][j] = tem
    return count
}

fun main() {
    println(uniquePathsIII(arrayOf(intArrayOf(1, 0, 0, 0), intArrayOf(0, 0, 0, 0), intArrayOf(0, 0, 2, -1))))
}