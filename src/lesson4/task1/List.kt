@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumByDouble { it * it })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else (list.sum() / list.size)

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val const = mean(list)
    for (i in 0 until list.size) {
        list[i] = list[i] - const
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    if (a.isEmpty()) return 0
    for (i in a.indices) {
        result += (a[i] * b[i])
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */

fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var nun = 0
    for (i in p.indices) {
        nun += p[i] * x.toDouble().pow(i.toDouble()).toInt()
    }
    return nun
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var nun = n
    var i = 2
    val result = mutableListOf<Int>()
    while (nun != i) {
        while (nun % i == 0) {
            result.add(i)
            nun /= i
        }
        i++
    }
    if (nun != 1) result.add(nun)
    if (result.isEmpty()) result.add(n)
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */

fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */

fun convert(n: Int, base: Int): List<Int> {
    var count = n
    val result = mutableListOf<Int>()
    while (count >= base) {
        result.add(count % base)
        count /= base
    }
    result.add(count)
    return result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val letter = "0123456789abcdefghijklmnopqrstuvwxyz"
    val nun = convert(n, base)
    val result = StringBuilder()
    for (element in nun) result.append(letter[element])
    return result.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun pow(base: Int, i: Int): Int {
    var x = base
    if (i == 0) return 1
    for (t in 1 until i) x *= base
    return x
}

fun decimal(digits: List<Int>, base: Int): Int {
    var count = 0
    if ((digits.size == 1) && (digits[0] < 10)) return digits[0]
    for (i in digits.indices) {
        val u = digits.size - i - 1
        count += digits[i] * pow(base, u)
    }
    return count
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    for (i in str.indices) result += (pow(
        base,
        str.length - i - 1
    )) * if (str[i] <= '9') (str[i] - '0') else (str[i] - 'a' + 10)
    return result
}


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */

fun roman(n: Int): String {
    val list2 = listOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
    )
    var nun = n
    val result = StringBuilder()
    for ((key, value) in list2) {
        while (nun >= key) {
            result.append(value)
            nun -= key
        }
    }
    return result.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()

/*

/*
fun flat(requer: String, inputName: String): Any {
    val result = mutableListOf<String>()
    val need = mutableMapOf<String, Int>()
    val newRequer = requer.replace(",", "").split(" ")
    for (elem in 0..newRequer.size - 2 step 2) need[newRequer[elem]] =
        newRequer[elem + 1].toInt()                                                                     // преобразуем запрос в map вида "комната" to 45
    try {
        for (line in File(inputName).readLines()) {
            val roms = mutableMapOf<String, Int>()
            var adress = ""
            if (line.matches("""[\wА-Яа-я]* \d+(\-\d+)*\: ([\wА-Яа-я]* \d*, )*[\wА-Яа-я]* \d*""".toRegex())) {
                adress = line.split(":").take(1).toString()
                val part = line.split(":").last().split("""[\s(\s\,)]""".toRegex())
                    .filter { (it != "") && (it != ",") } //то же самое делаем с строкой из файла
                if (part.size >= 2) {
                    for (key in 0..part.size - 2 step 2) roms[part[key]] = part[key + 1].toInt()
                } else roms[part[0]] = part[1].toInt()
                var chekm = true
                for (room in need.keys) if (room in roms.keys) {
                    if (roms[room]!! < need[room]!!) chekm = false
                } else chekm = false
                if (chekm) result.add(adress.removePrefix("[").removeSuffix("]"))
            } else throw IllegalArgumentException()
        }
    } catch (e: IOException) {
        throw IOException()
    }
    if (result.isEmpty()) println("ничешо не найдно")
    return result
}
*/


fun flat(query: String, inputName: String): List<String> {
    try {
        File(inputName).bufferedReader()
    } catch (e: IOException) {
        throw IOException()
    }
    val qu = mutableMapOf<String, Int>()
    val cond = query.split(", ")
    for (c in cond) {
        qu[c.split(" ")[0]] = c.split(" ")[1].toInt()
    }
    val appartment = mutableListOf<String>()
    var i = 0
    var k = 0
    for (line in File(inputName).readLines()) {
        val adress = line.split(": ")
        val rooms = adress[1].split(", ")
        while (i != rooms.size) {
            var count = 0
            val room = rooms[i].split(" ")
            for ((name, size) in qu) {
                if (room[0] == name && room[1].toInt() >= size) {
                    k++
                    count++
                }
                if (count != 0) break
            }
            if (k == qu.size) {
                appartment.add(adress[0])
                k = 0
                break
            }
            i++
        }
        i = 0
    }
    return appartment.toList()
}


fun trass(inputName: String, requer: String): Double {
    val letter = listOf<Char>(
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'J',
        'K',
        'L',
        'M',
        'N',
        'O',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'U',
        'V',
        'W',
        'X',
        'Y',
        'Z'
    )
    val mainMap = mutableMapOf<Int, Map<String, Double>>()
    var string = 1
    try {
        for (line in File(inputName).readLines()) {
            if (line.matches("""(\-*\d*\.\d*\, )*\-*\d*\.\d*""".toRegex())) {
                val newLine = line.split(", ").map { it.toDouble() }
                val tree = mutableMapOf<String, Double>()
                for (i in 0 until newLine.size) tree[letter[i].toString()] = newLine[i]
                mainMap[string] = tree
                string++
            } else throw IllegalArgumentException()
        }
    } catch (e: IOException) {
        throw IOException()
    }
    val need = requer.split(":")
    val startT = need[0][0]
    val startS = need[0].drop(1).toInt()
    val endT = need[1][0]
    val endS = need[1].drop(1).toInt()
    val result = mutableListOf<Double>()
    for (s in startS..endS) {
        if (startT > endT) {
            for (t in startT downTo endT) result.add(mainMap[s]!![t.toString()]!!)
        } else
            for (t in startT..endT) result.add(mainMap[s]!![t.toString()]!!)
    }
    val resultSize = result.size
    return result.sum() / resultSize
}

fun water(inputName: String, days: String): Int {
    var max = mutableListOf<Int>()
    var waterDay = mutableMapOf<String, List<String>>()
    val needMonth = mutableMapOf<String, List<Int>>()
    val need = days.split("""\s|\.\.\.""".toRegex())
    var monthStart = ""
    var monthEnd = ""
    var dayStart = 0
    var dayEnd = 0
    if (need.size > 3) {
        monthStart = need[0]
        monthEnd = need[2]
        dayStart = need[1].toInt()
        dayEnd = need[3].toInt()
    } else {
        monthStart = need[0]
        dayStart = need[1].toInt()
        dayEnd = need[2].toInt()
    }
    try {
        File(inputName).reader()
    } catch (e: IOException) {
        throw IOException()
    }
    for (line in File(inputName).readLines()) {
        if (!line.matches("""[А-Яа-я]*( \d+)*""".toRegex())) throw IllegalArgumentException()
        var newline = line.split(" ")
        waterDay[newline[0]] = newline
    }
    if (monthStart in waterDay.keys) {
        if (need.size == 3) {
            max = mutableListOf<Int>()
            for (i in dayStart..dayEnd) max.add(waterDay[monthStart]!![i].toInt())
        } else if (monthEnd in waterDay.keys) {
            var o = 0
            for (everyMonth in waterDay.keys) {
                if (((everyMonth == monthStart) || (o == 1)) && (everyMonth != monthEnd)) {
                    if (everyMonth == monthStart) {
                        o = 1
                        for (i in dayStart until waterDay[everyMonth]!!.size) max.add(waterDay[everyMonth]!![i].toInt())
                        continue
                    }
                    val new = waterDay[everyMonth]!!.drop(1)
                    for (vall in new) max.add(vall.toInt())
                } else if (everyMonth == monthEnd) {
                    for (i in 1..dayEnd) max.add(waterDay[everyMonth]!![i].toInt())
                    break
                }
            }
        }
    }
    return max.max()!!
}


fun goods(inputName: String, requer: String): List<String> {
    if (!requer.matches("""((\d{6})|\*) \d+""".toRegex())) throw IllegalArgumentException()
    val needkod = requer.split(" ")[0]
    val needSize = requer.split(" ")[1].toInt()
    val result = mutableListOf<String>()
    try {
        File(inputName).reader()
    } catch (e: IOException) {
        throw IOException()
    }
    for (line in File(inputName).readLines()) {
        if (!line.matches("""((\d{6})|\*): [а-я]*(, \d+ [а-я]*)+""".toRegex())) throw IllegalArgumentException()
        val kod = line.split(": ")[0]
        val newline = (line.split(": ")[1].split(""", | """.toRegex()))
        var price = newline[1].toInt()
        val size = newline[3].toInt()
        val string = StringBuilder()
        if ((needkod == kod) || (needkod == "*")) {
            if (needSize <= size) {
                result.add(
                    string.append(
                        newline[0],
                        ", ",
                        "достаточно, ",
                        "общая стоимость ${(price * size)} р"
                    ).toString()
                )
            } else result.add(
                string.append(
                    newline[0],
                    ", ",
                    "недостаточно, ",
                    "общая стоимость ${(price * size)} р"
                ).toString()
            )
        }
    }
    println(result)
    return result
}


fun ter(inputName: String, expr: String): List<Int> {
    if (!expr.matches("""[A-Z] & \!*[A-Z]""".toRegex())) throw IllegalArgumentException()
    val l = expr.split(" ")
    val firs = l[0]
    val last = l.last()
    val operation = l[1]
    var ref = true
    var lastWith = ""
    if (last.contains('!')) {
        lastWith = last.replace("!", "")
        ref = false
    } else lastWith = last
    try {
        File(inputName).reader()
    } catch (r: IOException) {
        throw IOException()
    }
    var count = listOf<Int>()
    var countInLast = listOf<Int>()
    for (line in File(inputName).readLines()) {
        if (!line.matches("""[A-Z] = (\-*\d+, )+\-*\d+""".toRegex())) throw IllegalArgumentException()
        val newline = line.split(" = ")
        if (firs == newline[0]) {
            count = newline[1].split(", ").map { it.toInt() }
        } else if (lastWith == newline[0]) {
            countInLast = newline[1].split(", ").map { it.toInt() }
        }
    }
    val result = mutableListOf<Int>()
    if (ref) {
        for (elem in countInLast) if (elem in count) result.add(elem)
    } else for (elem in count) {
        println(elem)
        if (elem !in countInLast) result.add(elem)
    }
    return result
}


fun flyTime(inputName: String, src: String, dst: String): List<Pair<String, String>> {
    if ((!src.matches("""[A-Z][a-z]+""".toRegex())) || (!dst.matches("""[A-Z][a-z]+""".toRegex()))) throw IllegalArgumentException()
    try {
        File(inputName).reader()
    } catch (r: IOException) {
        throw IOException()
    }
    val mapOfFArrave = mutableMapOf<String, Int>()
    val mapOfLeave = mutableMapOf<String, Int>()
    for (line in File(inputName).readLines()) {
        if (!line.matches("""[A-Z]+\d* [A-Z][a-z]+\s(>|<)\s\d+:\d+""".toRegex())) throw IllegalArgumentException()
        val newLine = line.split(" ")
        val where = newLine[2]
        var arriveTime = 0
        var leaveTime = 0
        var arraveBort = ""
        var leaveBort = ""
        val time = newLine[3].split(":").map { it.toInt() }
        if ((newLine[1] == src) && (where == ">")) {
            if (time[0] > 12) arriveTime = (time[0] - 12) * 60 + time[1] else arriveTime = time[0] * 60 + time[1]
            arraveBort = newLine[0]
            mapOfFArrave[arraveBort] = arriveTime
        } else if ((newLine[1] == dst) && (where == "<")) {
            if (time[0] > 12) leaveTime = (time[0] - 12) * 60 + time[1] else leaveTime = time[0] * 60 + time[1]
            leaveBort = newLine[0]
            mapOfLeave[leaveBort] = leaveTime
        }
    }
    val resultMap = mutableMapOf<Pair<String, String>, Int>()
    for ((levBort, levTime) in mapOfLeave) {
        for ((arBort, arTime) in mapOfFArrave) {
            if ((levTime - arTime) >= 30) resultMap[arBort to levBort] = (levTime - arTime)
        }
    }
    val u = resultMap.toList().sortedBy { it.second }.toMap()
    return u.keys.toList()
}


fun main() {
    var o = listOf<Pair<String, String>>("kk" to "kk", "ksk" to "jj")
}




 */