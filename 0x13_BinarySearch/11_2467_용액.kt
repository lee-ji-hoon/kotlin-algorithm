import java.lang.Math.*
import java.lang.StringBuilder
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())

    val arr = IntArray(n) { st.nextToken().toInt() }
    Arrays.sort(arr)

    var left = 0
    var right = n - 1

    // Int.MAX_VALUE 하니까 계속 5프로에서 터져서 질문검색 쪽을 보니 2_100_000_000 로 바꾸면 된다는데 왜지?..
    var test = 2_147_483_647
    var min = 2_100_000_000
    var tempLeft = 0
    var tempRight = n - 1

    while (left <= right) {
        val temp = arr[right] + arr[left]
        // arr[l] + arr[r] 이 두 용액을 섞은 값이 될 것이고
        // 절대값이 작을때 0에 가까운게 되는 것이다.
        if (left != right && abs(arr[right] + arr[left]) < min) {
            tempLeft = left
            tempRight = right
            min = abs(temp)
        }
        // 즉 두 용액을 섞은게 0 이상이라면 r을 줄여간다.
        if (temp >= 0) {
            right--
        // 두 용액을 섞은게 음수라면 l을 눌려가면서 0과 더 가깝게 만들어준다.
        } else {
            left++
        }
    }

    System.out.println(arr[tempLeft].toString() + " " + arr[tempRight]);
}
