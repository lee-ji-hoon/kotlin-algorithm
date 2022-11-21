import java.util.*

// 실버 2가 정답 비율이 어떻게 21프로?

// Parametric Search 라는 방식을 사용해지만 통과가 가능하다.
// 참고 https://st-lab.tistory.com/269
fun main() = with(System.`in`.bufferedReader()) {
    val (k, n) = readLine().split(" ").map { it.toInt() }

    val arr = IntArray(k)
    var max = 0L

    // 입력과 동시에 해당 랜선의 길이가 최대 값인지 확인
    for (i in 0 until k) {
        arr[i] = readLine().toInt()
        if (max < arr[i]) max = arr[i].toLong()
    }

    max++

    var min = 0L
    var mid = 0L

    while (min < max) {
        mid = (max + min) / 2L
        var count = 0L
        for (i in 0 until k) {
            count += (arr[i] / mid)
        }

        // upperBound 방식
        // mid 길이로 잘랐을대 개수가 만들고자 하는 랜선의 개수보다 작을때
        // 자르고자 하는 길이를 줄이기 위해 최대 길이를 줄인다.
        // 그 외에는 자르고자 하는 길이를 늘려야 하므로 최소 길이를 늘린다.
        if (count < n) {
            max = mid
        } else {
            min = mid + 1
        }
    }
    // upperBound 에서 얻어진 값에 -1 이 최대 값이다.
    println(min - 1)
}
