
fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()

    // plus minus를 별도로 관리
    val plus = arrayListOf<Int>()
    val minus = arrayListOf<Int>()

    for (i in 0 until n) {
        val num = readLine().toInt()
        if (num > 0) plus.add(num)
        else minus.add(num)
    }

    plus.sortDescending()
    minus.sort()

    var sum = 0
    var current = 0
    while (current < plus.size){
        sum += if (current + 1 < plus.size && plus[current] != 1 && plus[current + 1] != 1) {
            plus[current++] * plus[current++]
        } else {
            plus[current++]
        }
    }

    current = 0
    while (current < minus.size){
        sum += if (current + 1 < minus.size) {
            minus[current++] * minus[current++]
        } else {
            minus[current++]
        }
    }

    println(sum)
    // 가슴아픈 흔적
    /*var sum = 0
    for (i in 0 until n step (2)) {
        val cur = arr[i]

        sum += if (n - i >= 2) {
            val next = arr[i + 1]
            if (cur > 1 && next > 1) {
                cur * next
            } else if (cur <= -1 && next <= -1) {
                cur * next
            } else {
                cur + next
            }
        } else {
            cur
        }
    }*/

    // 반례
/*
5
-3
-2
2
3
4
*/
}