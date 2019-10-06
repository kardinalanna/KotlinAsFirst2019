@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    if ((age / 10 != 1) && (age / 10 != 11))
        when (age % 10) {
            1 -> "$age год"
            2, 3, 4 -> "$age года"
            else -> "$age лет"
        }
    else "$age лет"

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val way1 = t1 * v1
    val way2 = t2 * v2
    val way3 = t3 * v3
    val halfway = (way1 + way2 + way3) / 2
    if (halfway <= way1) return halfway / v1
    else {
        if (halfway < (way1 + way2)) return t1 + (t2 - ((way1 + way2 - halfway) / v2))
        if (halfway >= way1 + way2) return t1 + t2 + ((halfway - way1 - way2) / v3)
    }
    return halfway / v1
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return if ((kingX == rookX1) || (kingY == rookY1)) {
        if ((kingX == rookX2) || (kingY == rookY2)) 3
        else 1
    } else if ((kingY == rookY2) || (kingX == rookX2)) {
        if ((kingX == rookX1) || (kingY == rookY1)) 3
        else 2
    } else 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val moduleY = abs(kingY - bishopY)
    val moduleX = abs(bishopX - kingX)
    return if ((rookX == kingX) || (rookY == kingY)) {
        if ((moduleY == moduleX)) 3 else 1
    } else if ((moduleY == moduleX)) {
        if ((rookX == kingX) || (rookY == kingY)) 3 else 2
    } else 0
}

/**
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */

fun triangleKind(a: Double, b: Double, c: Double) {
    var max = a
    var sr = b
    var min = c
    if (b > a) max = b else if (c > max) max = c
    if (a < c) min = a else if (b < min) min = b
    sr = a + b + c - min - max
    when {
        max + min > sr -> -1
        sqr(max) == sqr(min) + sqr(sr) -> 1
        sqr(max) > sqr(min) + sqr(sr) -> 2
        else -> 0

    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int =
    when {
        (b < c) || (d < a) -> -1
        (c > a) && (d > b) && (c <= b) -> abs(b - c)
        (a > c) && (b > d) && (a <= b) -> abs(d - a)
        (a <= c) && (b >= d) -> abs(d - c)
        (a >= b) || (b <= d) -> abs(b - a)
        else -> -1
    }

















