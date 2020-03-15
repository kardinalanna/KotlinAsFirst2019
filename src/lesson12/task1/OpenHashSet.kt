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
        val elem = element.hashCode()
        return elem % capacity
    }

    private var flag = 0

    fun add(element: T): Boolean {
        var hash = hash(element)
        var ending = capacity
        if (flag == 1) {
            ending = hash
            hash = 0
        }
        if (elements[hash] == null) {
            size++
            elements[hash] = element
            return true
        } else {
            for (hahKey in hash until ending) {
                if (elements[hahKey] == element) return false
                if (elements[hahKey] == null) {
                    size++
                    elements[hahKey] = element
                    return true
                }
            }
            if (flag == 1) return false
            flag = 1
            add(element)
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
        if (other is OpenHashSet<*> && this.size == other.size) {
            for ((key, value) in elements.withIndex()) if (!other.elements.contains(value)) return false
            return true
        } else return false
    }


    /*  override fun hashCode(): Int {
        var result = elements.contentHashCode()
        result = 31 * result + size
        return result
    }*/


    override fun hashCode(): Int {
        var t = 0
        for ((key, vall) in this.elements.withIndex()) {
            vall?.toString()?.toCharArray()?.forEach { t += it.toInt() * 31 }
        }
        return t
    }
}
