
class main {
    fun solution(s: String): Boolean {
        val length = s.filter { it.isDigit() }.length
        return (length == 4 || length == 6) && length == s.length
    }
}