import java.io.StreamTokenizer

// https://www.acmicpc.net/problem/2531

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val dishies = input()
    val kind = input()
    val maxDishies = input()
    val coupon = input()

    val table = IntArray(dishies)

    for (i in 0 until dishies) {
        table[i] = input()
    }

    val sushi = IntArray(kind + 1)
    var count = 0
    var max = 0

    for (i in 0 until maxDishies) {
        val a = table[i]
        if (sushi[a] == 0) {
            count++
        }
        sushi[a]++
    }

    for (i in 0 until dishies - 1) {
        if (count >= max) {
            max = if (sushi[coupon] == 0) {
                count + 1
            } else {
                count
            }
        }

        // 첫 번째 스시와 중복되는 스시가 없다면 count-1
        if (--sushi[table[i]] == 0) count--

        // 마지막 스시 추가 전 중복되는 스시가 있는지 확인(중복x -> count+1)
        if (sushi[table[(i + maxDishies) % dishies]]++ == 0) count++
    }
    println(max)
}
