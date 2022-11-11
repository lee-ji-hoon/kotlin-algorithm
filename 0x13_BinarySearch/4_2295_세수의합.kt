import java.util.*
import kotlin.math.max

// O(n^4) -> 처음 풀이 시간 복잡도가 이렇게 나왔다 ㅎㅎ;

// x + y + z = u
// x + y = u - z
// x + y -> list 같은 걸로 미리 찾고 u-z를 이분 탐색으로 찾자!
// O(n^2) * O(nlogn) 으로 보이는데 맞나?
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = IntArray(n)
    for (i in 0 until n) {
        arr[i] = readLine().toInt()
    }
    solution(arr)
}

private fun solution(arr: IntArray) {
    arr.sort()
    val twoSums = arrayListOf<Int>()
    for (i in 0 until arr.size) {
        for (j in 0 until arr.size) {
            twoSums.add(arr[i] + arr[j]) // 이게 가능한 이유는 중복이 허용되기 때문
        }
    }

    twoSums.sort()

    var answer = 0
    for (i in 0 until arr.size) {
        for (j in i + 1 until arr.size) {
            val binarySearch = twoSums.binarySearch(arr[j] - arr[i])
            if (binarySearch >= 0) {
                answer = max(answer, arr[j])
            }
        }
    }
    println(answer)
}