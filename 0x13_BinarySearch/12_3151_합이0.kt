import java.util.*

// 실패한 풀이입니다...
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    // 2 -5 2 3 -4 7 -4 0 1 -6
    val arr = IntArray(N)
    // -7 -6 -5 -4
    val minus = ArrayList<Int>()
    // 1 2 2 3 7
    val plus = ArrayList<Int>()

    // 0은 따로
    var zero: Long = 0
    var st = StringTokenizer(readLine(), " ")
    for (i in 0 until N) {
        arr[i] = st.nextToken().toInt()
        if (arr[i] == 0) {
            zero++
        } else if (arr[i] > 0) {
            plus.add(arr[i])
        } else {
            minus.add(-arr[i])
        }
    }
    var result: Long = 0

    // 최대 합이 20000이므로, 20000이상은 넘어가지 않는다.
    val plusSum = LongArray(20001)
    val minusSum = LongArray(20001)

    // 조합
    for (i in plus.indices) {
        // plus의 i번째와 j번째를 더했을 때의 개수
        for (j in i + 1 until plus.size) {
            plusSum[plus[i] + plus[j]]++
        }
    }

    for (i in minus.indices) {
        for (j in i + 1 until minus.size) {
            minusSum[minus[i] + minus[j]]++
        }
    }

    println(plusSum.contentToString())
    println(minusSum.contentToString())

    // minus에 plus의 두 개의 값을 합한 값과 같은 것이 있는 지 판단
    for (i in minus.indices) {
        result += plusSum[minus[i]]
    }

    for (i in plus.indices) {
        result += minusSum[plus[i]]
    }

    // zeroC3 조합 공식
    // zero만 택했을 경우
    if (zero >= 3) {
        // zero!/(zero-3)! * 3!
        // zero * zero-1 * zero-2 / 6
        result += zero * (zero - 1) * (zero - 2) / 6
    }

    // 0 + plus + minus = 0인 경우
    if (zero < 1) return
    for (i in plus.indices) {
        for (j in minus.indices) {
            if (plus[i] == minus[j]) {
                result += zero
            }
        }
    }
    println(result)
}
