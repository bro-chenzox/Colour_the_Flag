fun main() {
    repeat(readLine()!!.toInt()) {
        val field = (1..readFirstInt()).map { readLine()!! }
        val etalon = field.getColored()
        if (field.compare(etalon)) {
            println("YES")
            etalon.forEach { println(it) }
        } else println("NO")
    }
}

private fun readFirstInt() = readLine()!!.substringBefore(" ").toInt()

private fun Char.getOppositeColor() = if (equals('R')) 'W' else 'R'

private fun List<String>.getFirstCellColor(): Char {
    var curChar = 'R'
    for ((i, s) in withIndex()) {
        val indOfFirst = s.indexOfFirst { it != '.' }
        if (indOfFirst == -1) continue
        else {
            curChar = this[i][indOfFirst]
            if ((i + indOfFirst) % 2 != 0) curChar = curChar.getOppositeColor()
        }
    }
    return curChar
}

private fun List<String>.getColored(): List<String> {
    val firstChar = getFirstCellColor()
    val oppositeColor = firstChar.getOppositeColor()
    return indices.map { string ->
        this[string].indices.map { char ->
            if ((string + char) % 2 == 0) firstChar else oppositeColor
        }.joinToString("")
    }
}

private fun List<String>.compare(other: List<String>): Boolean {
    return indices.all { string ->
        this[string].indices.all { char ->
            this[string][char] == other[string][char] || this[string][char] == '.'
        }
    }
}
