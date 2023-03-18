import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input() // 정점의 개수
    val m = input() // 간선의 개수

    // 그래프 만들기
    val graph = Array(n + 1) { arrayListOf<Int>() } // 인접 리스트를 이용한 그래프
    val visited = BooleanArray(n + 1) // 방문 여부 체크 배열

    // 간선 정보 입력 받기
    repeat(m) {
        val u = input()
        val v = input()
        graph[u].add(v)
        graph[v].add(u) // 무방향 그래프이므로 양방향으로 추가
    }

    var count = 0 // 연결 요소의 개수
    // 모든 정점에 대해 DFS 탐색을 수행
    for (i in 1..n) {
        if (visited[i].not()) {
            dfs(graph, visited, i) // 방문하지 않은 정점이라면 DFS 수행
            count++ // DFS가 끝난 후 연결 요소의 개수를 1 증가
        }
    }

    println(count) // 연결 요소의 개수 출력
}

fun dfs(graph: Array<ArrayList<Int>>, visited: BooleanArray, v: Int) {
    visited[v] = true // 정점 방문 여부 체크
    for (u in graph[v]) {
        if (visited[u].not()) {
            dfs(graph, visited, u) // 방문하지 않은 인접 정점에 대해 DFS 수행
        }
    }
}