@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import javax.swing.text.Segment

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { null }

    /**
     * Число элементов в хеш-таблице
     */
    var size: Int = 0

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean = (size == 0)

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */

    private fun hash(element: T): Int {
        var h = 0
        val elem = element.toString()
        for (i in elem) h = 31 * h + i.toInt()
        return h % capacity
    }

    fun add(element: T): Boolean {
        val hash = hash(element)
        if (elements[hash] == null) {
            size++
            elements[hash] = element
            return true
        } else {

            for (hahKey in hash until capacity) {
                if (elements[hahKey] == element) return false
                if (elements[hahKey] == null) {
                    size++
                    elements[hahKey] = element
                    return true
                }

            }
            for (beforHs in 0..hash) {
                if (elements[beforHs] == null) {
                    size++
                    elements[beforHs] = element
                    return true
                }
            }
        }
        return false
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean {
        val hash = hash(element)
        var i = hash
        while (i != elements.size) {
            if (elements[i] == element) return true
            i++
        }
        i = 0
        while (i != hash) {
            if (elements[i] == element) return true
            i++
        }
        return false
    }

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (other is OpenHashSet<*> && elements.size == other.size) {
            for ((key, value) in elements.withIndex()) if (!other.elements.contains(value)) return true
        }
        return false
    }

    override fun hashCode(): Int {
        var t = 0
        for (key in 0 until this.size) {
            key.toString().toCharArray().forEach { t += it.toInt() }
            t += t * 31

        }
        return t
    }
}
