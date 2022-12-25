fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val arr = ArrayList<Int>()
    for (i in 0 until n) {
        arr.add(readLine().toInt())
    }
    var ans = 0
    // 거꾸로 시작
    for (i in n - 2 downTo 0) {
        if (arr[i] >= arr[i + 1]) { // 낮은 레벨의 점수가 높은 레벨보다 큰 경우 + 같은 경우도 생각 해야함
            val diff = arr[i] - arr[i + 1] + 1
            ans += diff
            arr[i] -= diff
        }
    }
    println(ans)
}