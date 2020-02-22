@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import kotlinx.html.I
import lesson2.task2.daysInMonth
import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException
import kotlin.math.max

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val resultMap = mutableMapOf<String, Int>()
    val set = substrings.toSet()
    for (element in set) resultMap[element] = 0
    if (inputName == "") return mapOf<String, Int>("" to 0)
    val split = File(inputName).readText().map { it.toLowerCase() }
    for (element in set) {
        for (word in split.windowed(element.length)) {
            if (word == element.toLowerCase().toList()) {
                val count = resultMap[element]!!
                resultMap[element] = 1 + count
            }
        }
    }
    return resultMap
}


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    val regex1 = """[жчшщ]""".toRegex(RegexOption.IGNORE_CASE)
    val regex2 = """(ы|я|ю)""".toRegex(RegexOption.IGNORE_CASE)
    val resultString = StringBuilder()
    var start = 1
    for (line in File(inputName).readLines()) {
        start = 0
        for (char in 1 until line.length) {
            val str1 = line[char - 1]
            val str2 = line[char]
            if (start == 0) {
                start++
                resultString.append(str1)
            }
            if ((regex1.matches(str1.toString())) && (regex2.matches(str2.toString()))) {
                when (str2) {
                    'Ы' -> resultString.append("И")
                    'ы' -> resultString.append('и')
                    'Я' -> resultString.append('А')
                    'я' -> resultString.append('а')
                    'Ю' -> resultString.append('У')
                    'ю' -> resultString.append('у')
                }
            } else resultString.append(str2)
        }
        outputStream.write(resultString.toString())
        println(outputStream)
        resultString.clear()
        outputStream.newLine()
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    val regex = """\s+""".toRegex()
    var maxLenght = 0
    var u = 0
    var countOfSpase = 0
    for (line in File(inputName).readLines()) {   //нахпоим длиннейщую строку
        val lenght = line.trim().length
        if (lenght > maxLenght) maxLenght = lenght
    }
    for (line in File(inputName).readLines()) {
        val length = line.trim().length
        countOfSpase = (maxLenght - length) / 2
        for (i in 0..countOfSpase) outputStream.write(" ")
        outputStream.write(line.trim())
        outputStream.newLine()
        println(outputStream)
    }
    outputStream.close()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */

fun theLongestLine(inputLines: List<String>, wantToCenter: Boolean): List<String> {
    var count = 0
    val outputLines = mutableListOf<String>()
    for (i in inputLines.indices) {
        if (wantToCenter) {
            val lineWithOutSpaces = inputLines[i].trim()
            outputLines.add(lineWithOutSpaces)
            val currentLength = lineWithOutSpaces.length
            if (currentLength > count) count = currentLength
        } else {
            val splitted = inputLines[i].split(" ").filter { it != "" }
            val currentLength = inputLines[i].count { it != ' ' } + splitted.size - 1
            if (currentLength > count) count = currentLength
            outputLines.add(splitted.joinToString(" "))
        }
    }
    outputLines.add("$count")
    return outputLines
}

fun alignFileByWidth(inputName: String, outputName: String) {
    val inputStream = theLongestLine(File(inputName).readLines(), false)
    val outputStream = File(outputName).bufferedWriter()
    val largestLength = inputStream.last().toInt()
    for (line in inputStream) {
        if (line.toIntOrNull() != null) continue
        if (line.isNotBlank()) {
            var currentLength = 0
            var countOfSpaces = 0
            val splitted = line.split(" ")
            val length = line.count { it != ' ' }
            val numberOfSpace =
                (largestLength - length) / if (splitted.size != 1) splitted.size - 1 else 1
            for (index in splitted.indices) {
                val word = splitted[index]
                currentLength += word.length
                if (index == splitted.lastIndex) {
                    outputStream.write(splitted[index])
                    continue
                }
                if (length + countOfSpaces + (splitted.size - 1 - index) * numberOfSpace != largestLength) {
                    outputStream.write(word.padEnd(word.length + numberOfSpace + 1, ' '))
                    countOfSpaces += 1
                } else outputStream.write(word.padEnd(word.length + numberOfSpace, ' '))
                countOfSpaces += numberOfSpace
            }
        }
        outputStream.newLine()
    }
    outputStream.close()
}


/*
fun countOfSpace(listOfWord: List<String>, maxLenght: Int, lenghtOfWord: Int, outputName: String ):Unit {
    val countOfSplit = (maxLenght - lenghtOfWord) / listOfWord.size - 1
    var addSplit = (maxLenght - lenghtOfWord) % listOfWord.size - 1
    val outputStream = File(outputName).bufferedWriter()
    for (value in 0..(listOfWord.size - 2)) {
        if (addSplit != 0) {
            outputStream.write(listOfWord[value].padEnd(countOfSplit + 1))
            addSplit -= 1
        } else outputStream.write(listOfWord[value].padEnd(countOfSplit))
    }
    outputStream.write(listOfWord.last())
    outputStream.newLine()
    }


val dhhd = """jfj""".to
val line.mathes("""jhdjgdfj""".toRegex











fun alignFileByWidth(inputName: String, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    val regex = """\s+""".toRegex()
    var lenghtOfWord = 0
    var maxLenght = 0
    var countOfSplit = 0
    var addSplit = -1
    val bildString = java.lang.StringBuilder()
    for (line in File(inputName).readLines()) {
        val lenght = regex.replace(line, " ").trim().length
        if (lenght > maxLenght) maxLenght = lenght
    }
    for (line in File(inputName).readLines()) {
        if (regex.matches(line) || (line == "")) {
            outputStream.newLine()
            continue
        }
        if (line.length == maxLenght) {
            outputStream.write(line)
            outputStream.newLine()
            continue
        }
        val listOfWord = line.trim().split(regex)
        lenghtOfWord = 0
        listOfWord.forEach { lenghtOfWord += it.length }
        if (listOfWord.size == 1) {
            outputStream.write(listOfWord[0])
            outputStream.newLine()
            continue
        }
        countOfSplit = (maxLenght - lenghtOfWord) / listOfWord.size - 1
        if (countOfSplit == 0) {
            var residue = mutableListOf<String>()
            while (countOfSplit < 1) {
                residue.add(listOfWord.last())
                listOfWord.dropLast(1)
                countOfSplit = (maxLenght - lenghtOfWord) / listOfWord.size - 1
                addSplit =
                    (maxLenght - lenghtOfWord) % listOfWord.size - 1                    //перенос на новую строеку
            }
            countOfSpace(listOfWord,maxLenght, lenghtOfWord, outputName)
            countOfSpace(residue,maxLenght, lenghtOfWord, outputName)
            continue
        } else countOfSpace(listOfWord,maxLenght, lenghtOfWord, outputName)
        continue
    }
    outputStream.close()
}

fun main() {
    var t = 0
    val k = ("Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно".split(" "))
   k.forEach{t += it.length}
    println(k)
println(t)
}
*/

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    var result = mutableMapOf<String, Int>()
    val map = mutableMapOf<String, Int>()
    for (line in File(inputName).readLines()) {
        val reg = """[ ?\—\»\«]""".toRegex()
        val newline = line.toLowerCase().split(reg).filter { it != "" }
        if (newline.isEmpty()) continue
        for (word in newline) if (word in result.keys) {
            val y = result[word]!!
            result[word] = y + 1
        } else result[word] = 1
    }
    result = result.toList().sortedByDescending { it.second }.toMap().toMutableMap()
    return if (result.size < 20) result else {
        for ((key, vall) in result) if (map.size < 20) map[key] = vall
        map
    }
}

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    val mapP = mutableMapOf<Char, String>()
    for ((key, value) in dictionary) mapP[key.toLowerCase()] = value.toLowerCase()
    val outputStream = File(outputName).bufferedWriter()
    for (leter in File(inputName).readText()) {
        if (leter in mapP.keys || leter.toLowerCase() in mapP.keys) {
            if (leter.toString().matches("""[A-ZА-Я]""".toRegex())) {
                val string = mapP[leter.toLowerCase()]!!
                if (string.toCharArray().size != 1) {
                    outputStream.write(string[0].toString().toUpperCase())
                    for (i in 1..string.toCharArray().size - 1) outputStream.write(string[i].toString())
                }
            } else outputStream.write(mapP[leter]!!)
        } else outputStream.write(leter.toString())
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Фрукты
<ol>
<li>Бананы</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

//daysInMonth
fun calendar(inputName: String, tStart: String, tEnd: String): List<String> {
    val weekDay = mapOf<Int, Int>(
        1 to 3,
        2 to 6,
        3 to 7,
        4 to 3,
        5 to 5,
        6 to 1,
        7 to 3,
        8 to 6,
        9 to 2,
        10 to 4,
        11 to 7,
        12 to 2
    )
    val reg = """\d\d.\d\d""".toRegex()
    if (!tStart.matches(reg) || !tEnd.matches(reg)) throw IllegalArgumentException()
    val startMonth = tStart.split(".")[1].toInt()
    val endMonth = tEnd.split(".")[1].toInt()
    var starDay = tStart.split(".")[0].toInt()
    val endDay = tEnd.split(".")[0].toInt()
    val listOfMonth = mutableMapOf<Int, Int>()
    val inp = File(inputName).readText().split(", ")
    val result = mutableListOf<String>()
    var week = weekDay[startMonth]!!
    for (i in 1 until starDay) if (week != 7) week++ else week = 1

    for (i in startMonth..endMonth) listOfMonth[i] = daysInMonth(i, 2020)
    for (mon in listOfMonth.keys) {
        if (mon != startMonth) week == weekDay[mon]!!
        for (i in starDay..listOfMonth[mon]!!) {
            var now = ""
            now = if (mon > 9) "$i.$mon" else "$i.0$mon"
            if ((now !in inp) && ((week != 6) && (week != 7))) {
                result.add(now)
                week++
            } else if (week == 7) week = 1 else week++
            if (i == endDay && mon == endMonth) return result
        }
        starDay = 1
    }
    return result
}


// tStart = "15:00"


fun ggg (inputName: String, analis:String, tStart: String, tEnd: String): List<String> {
    val result = mutableListOf<String>()
    val newreg = """[А-Яа-я]* \d+\.\d+\.\d+\-\d+\:\d+\s(\"([А-Яа-я]+\s*)+")(, (\"([А-Яа-я]+\s*)+\"))*""".toRegex()
    val startTime = tStart.split(":")[0].toInt() * 60 + tStart.split(":")[1].toInt()
    val endTime = tEnd.split(":")[0].toInt() * 60 + tEnd.split(":")[1].toInt()
    val reg = analis.toRegex()
    for (line in File(inputName).readLines()) {
        if (!line.matches(newreg)) throw IllegalArgumentException()
        val newLine = line.split("-")
        val name = newLine[0].split(" ")[0]
        val time = newLine[1].split(""" \"""".toRegex())[0]
        val ana = newLine[1].split(""" \"""".toRegex()).drop(0)
        var analisTrue = false
        for (i in 0 until ana.size) if (ana[i].contains(reg)) analisTrue = true
        var needTime = time.split(":")[0].toInt() * 60 + time.split(":")[1].toInt()
        if ((needTime in startTime..endTime) && (analisTrue)) result.add(name)
    }
    return result
}

fun main() {
    val st ="Когда солнце накрыло раскаленными лучами только счто проснувщийся лес, влага, таящаясяя под покровом резной листвы стала испаряться и роща вновь наполнилась невыносимыми духотой  и жап и ж"
    val u = "sun rises at 3.40, sun shining at 1.45, sin is our happy"
    val reg = """sun\s\w*""".toRegex()
    val  list = mutableListOf<String>()
    val uu = reg.findAll((u)).groupByTo(list).toList().toString()
    print(uu)
}

private fun <T> Sequence<T>.groupByTo(destination: MutableList<String>): MutableList<String> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
















