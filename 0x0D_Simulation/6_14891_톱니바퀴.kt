import java.util.*

lateinit var gear: Array<IntArray>
lateinit var rotationInformation: IntArray // 기어의 회전 정보를 담고있음

fun main() = with(System.`in`.bufferedReader()) {
    var st: StringTokenizer
    gear = Array(4) { IntArray(8) }
    for (i in 0..3) {
        val s = readLine()
        for (j in 0..7) {
            gear[i][j] = s[j] - '0'
        }
    }
    var k = readLine().toInt()
    repeat(k) {
        st = StringTokenizer(readLine())
        // 배열에 담긴 gear는 1부터 시작이아닌 0부터라 -1을 해줌
        val gearN = st.nextToken().toInt() - 1
        val turn = st.nextToken().toInt()

        // 톱니의 회전방향 정보를 담음
        rotationInformation = IntArray(4)
        rotationInformation[gearN] = turn
        checkGear(gearN)
        spinningGears()
    }

    var ans = 0
    if (gear[0][0] == 1) ans += 1 // 1번 1점
    if (gear[1][0] == 1) ans += 2 // 2번 2점
    if (gear[2][0] == 1) ans += 4 // 3번 4점
    if (gear[3][0] == 1) ans += 8 // 4번 8점
    println(ans)
}

fun checkGear(gearN: Int) {
    // 좌측 톱니 회전 방향 검사
    for (i in gearN - 1 downTo 0) {
        // 회전을 하지않으면 다음 톱니도 회전을 하지 않게 된다.
        if (gear[i][2] == gear[i + 1][6]) {
            break
        }

        rotationInformation[i] = -rotationInformation[i + 1]
    }
    // 우측 톱니 회전 방향 검사
    for (i in gearN + 1..3) {
        // 회전을 하지않으면 다음 톱니도 회전을 하지 않게 된다.
        if (gear[i][6] == gear[i - 1][2]) {
            break
        }
        rotationInformation[i] = -rotationInformation[i - 1]
    }
}

fun spinningGears() {
    var temp = 0
    // 모든 톱니 검사
    for (i in 0..3) {
        // 시계회전
        if (rotationInformation[i] == 1) {
            temp = gear[i][7]
            for (j in 7 downTo 1) {
                gear[i][j] = gear[i][j - 1]
            }
            gear[i][0] = temp
        }
        // 반시계 회전
        if (rotationInformation[i] == -1) {
            temp = gear[i][0]
            for (j in 0..6) {
                gear[i][j] = gear[i][j + 1]
            }
            gear[i][7] = temp
        }
    }
}
