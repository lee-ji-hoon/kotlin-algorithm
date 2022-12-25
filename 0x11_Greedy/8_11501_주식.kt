fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val sb = StringBuilder()

    for (i in 0 until t) {
        val days = readLine().toInt()
        val stocks = readLine().split(" ").map { it.toLong() }

        var max: Long = 0
        var answer: Long = 0

        // 뒤에서부터 시작을 해야 했다.
        // 결국에 팔기 위해서는 뒤에서부터 보는것이 맞기 때문
        for (j in days - 1 downTo 0) {
            if (stocks[j] > max) {
                max = stocks[j]
            } else {
                answer += max - stocks[j]
            }
        }
        sb.appendLine(answer)
    }
    println(sb)
}