import java.io.StreamTokenizer
import java.util.*

private lateinit var map: Array<IntArray>
private var dice = IntArray(7)
private var dx = intArrayOf(1, -1, 0, 0)
private var dy = intArrayOf(0, 0, -1, 1)
private var x = 0
private var y = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    y = input()
    x = input()
    val k = input()
    map = Array(n) { IntArray(m) { input() } }
    for (i in 0 until k) {
        move(input(), m, n)
    }
}

fun move(d: Int, m: Int, n: Int) {
    val nx = x + dx[d - 1]
    val ny = y + dy[d - 1]
    if (nx < 0 || ny < 0 || nx > m - 1 || ny > n - 1) return
    roll(d, nx, ny)
    x = nx
    y = ny
}

// 1(동) 2(서) 3(남) 4(북)
fun roll(d: Int, x: Int, y: Int) {
    val tmp = dice[3]
    when (d) {
        1 -> {
            dice[3] = dice[4]
            dice[4] = dice[6]
            dice[6] = dice[2]
            dice[2] = tmp
        }
        2 -> {
            dice[3] = dice[2]
            dice[2] = dice[6]
            dice[6] = dice[4]
            dice[4] = tmp
        }
        3 -> {
            dice[3] = dice[5]
            dice[5] = dice[6]
            dice[6] = dice[1]
            dice[1] = tmp
        }
        4 -> {
            dice[3] = dice[1]
            dice[1] = dice[6]
            dice[6] = dice[5]
            dice[5] = tmp
        }
    }
    if (map[y][x] == 0) {
        map[y][x] = dice[6]
    } else {
        dice[6] = map[y][x]
        map[y][x] = 0
    }
    println(dice[3])
}
