@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import lesson4.task1.roman

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val month = mapOf<String, Int>(
        "января" to 1,
        "февраля" to 2,
        "марта" to 3,
        "апреля" to 4,
        "мая" to 5,
        "июня" to 6,
        "июля" to 7,
        "августа" to 8,
        "сентября" to 9,
        "октября" to 10,
        "ноября" to 11,
        "декабря" to 12
    )
    val drop = str.split(" ")
    if (drop.size == 3) {
        val mon = drop[1]
        val day = drop[0].toIntOrNull()
        val year = drop[2].toIntOrNull()
        return if ((mon !in month.keys) || (day == null) || (year == null) || (day <= 0)) "" else {
            val numberOfMon = month[drop[1]]
            val numberOfDay = daysInMonth(numberOfMon!!, year)
            if ((day <= numberOfDay)) {
                String.format("%02d.%02d.%s", day, numberOfMon, year)
            } else ""
        }
    } else return ""
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val newMonth = listOf<String>(
        "месяцы",
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    val drop = digital.split(".")
    if (drop.size == 3) {
        val mon = drop[1].toIntOrNull()
        val day = drop[0].toIntOrNull()
        val year = drop[2].toIntOrNull()
        return if ((mon == null) || (mon !in 1..12) || (day == null) || (year == null) || (day <= 0)) "" else {
            if ((day <= daysInMonth(mon, year))) {
                val numberOfMon = newMonth[mon]
                String.format("%d %s %d", day, numberOfMon, year)
            } else ""
        }
    } else return ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val check = """\((\D+)\)""".toRegex()
    if (phone.contains("(") && (check.matches(phone))) return ""
    val newHpone = phone.filter { (it != ' ') && (it != '-') && (it != ')') && (it != '(') }
    val regex = """(\+\d*|\d*)""".toRegex()
    val result = regex.matchEntire(newHpone)
    return if ((result == null) || (phone.contains("()")) || (check.containsMatchIn(phone))) "" else newHpone
}


/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку  b и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val newJu = jumps.filter { (it != '-') && (it != '%') }.split(" ").filter { it != "" }
    val op = """(\d+\s((\-\s)+|(\%\s))+)+""".toRegex()
    return if (op.containsMatchIn(jumps)) newJu.map { it.toInt() }.max()!! else -1

}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val regex = """[^ \+\%\-\d\s]""".toRegex()
    val chekcPluss = """((\+\s){2,})""".toRegex().containsMatchIn(jumps)
    val jumpIsRight = regex.containsMatchIn(jumps)
    val check = """\d*""".toRegex().containsMatchIn(jumps)
    val justResult = mutableListOf<Int>()
    return if (('+' !in jumps) || jumpIsRight || !check || chekcPluss) -1 else {
        val almostResult = jumps.split(" ")
        for (key in almostResult.indices step 2) if ((almostResult[key + 1].contains('+')) && ("""\d*""".toRegex().matches(
                almostResult[key]
            ))
        ) justResult.add(
            almostResult[key].toInt()
        )
        justResult.max()!!
    }
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val chek = """(\d+)(\s(\+|\-)\s\d+)*""".toRegex()
    if (!requireNotNull(chek.matches(expression))) throw IllegalArgumentException()
    val result = expression.split(" ")
    var count = 0
    if (result.size < 3) return result[0].toInt()
    for (key in 2 until result.size step 2) if ((result[key - 1] == "+")) count += result[key].toInt() else count -= result[key].toInt()
    return count + result[0].toInt()
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val list = str.split(" ").map { it.toLowerCase() }
    var lenght = 0
    if (list.size < 2) return -1 else
        for (word in 0..list.size - 2) {
            lenght += list[word].length + 1
            if (list[word] == list[word + 1]) {
                return lenght - list[word].length - 1
            }
        }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от  пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.цены
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val map = mutableMapOf<Double, String>()
   // if (!"""([а-яА-Я]+\s(\d+\.\d)(;\s|\s))+""".toRegex().matches(description)) return ""
    var max = 0.0
    var nameOfGood = ""
    if (description.isEmpty()) return ""
    val list = description.split("; ")
    for (key in list) {
        val element = key.split(" ")
        var prise = element[1].toDouble()
        if ((element.size != 2) || (prise < 0)) return "" else {
            if (prise >= max) {
                max = prise
                nameOfGood = element[0]
            }
        }
    }
    return nameOfGood
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской      счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()


/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
