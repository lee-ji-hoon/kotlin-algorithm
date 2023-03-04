import java.io.StreamTokenizer

var selected = BooleanArray(20) // 스타트팀에 속한 선수를 true, 링크팀에 속한 선수를 false로 나타내는 배열
var minDiff = Int.MAX_VALUE // 스타트팀과 링크팀의 능력치 차이의 최솟값을 저장할 변수
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    // 입력 받은 능력치를 abilities 배열에 저장
    val abilities = Array(n) { IntArray(n) { input() } }
    // 스타트 팀과 링크 팀을 나누는 함수 호출
    divideTeams(0, 0, n, abilities)
    // 스타트 팀과 링크 팀의 능력치 차이 출력
    println(minDiff)
}

fun divideTeams(startIndex: Int, selectedCnt: Int, n: Int, abilities: Array<IntArray>) {
    // 기저 조건: 선택한 선수의 수가 N/2가 되면 스타트 팀과 링크 팀의 능력치 차이를 계산한다
    if (selectedCnt == n / 2) {
        calculateDiff(abilities)
        return
    }

    // 비트마스크를 이용하여 스타트 팀과 링크 팀을 나눈다
    for (i in startIndex until n) {
        if (selected[i].not()) { // 아직 선택되지 않은 선수라면
            selected[i] = true // 스타트 팀에 해당 선수를 선택하고
            divideTeams(i + 1, selectedCnt + 1, n, abilities) // 다음 선수를 선택하러 재귀 호출
            selected[i] = false // 해당 선수를 선택하지 않았을 경우를 대비하여 선택 취소
        }
    }
}

fun calculateDiff(abilities: Array<IntArray>) {
    var start = 0 // 스타트 팀의 능력치 합계를 저장할 변수
    var link = 0 // 링크 팀의 능력치 합계를 저장할 변수
    for (i in abilities.indices) {
        for (j in abilities.indices) {
            if (selected[i] && selected[j]) { // 스타트 팀에 해당 선수가 모두 속해 있다면
                start += abilities[i][j] // 스타트 팀의 능력치에 더한다
            } else if (!selected[i] && !selected[j]) { // 링크 팀에 해당 선수가 모두 속해 있다면
                link += abilities[i][j] // 링크 팀의 능력치에 더한다
            }
        }
    }
    minDiff = minOf(minDiff, kotlin.math.abs(start - link))
}

