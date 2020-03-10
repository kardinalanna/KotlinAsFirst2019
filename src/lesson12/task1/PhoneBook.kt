@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import java.util.*

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {
    val book = mutableMapOf<String, MutableSet<String>>()

    companion object {
        private val correctName = """[А-ЯA-Z][а-яa-z]* [А-ЯA-Z][а-яa-z]*""".toRegex()
        private val correctPhone = """[\d\+\-\*\#]*""".toRegex()
        private val listOfPhone = mutableSetOf<String>()
    }

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */

    fun addHuman(name: String): Boolean {
        if (!name.matches(correctName)) throw IllegalArgumentException()
        return if (name in book.keys) false else {
            book[name] = mutableSetOf()
            true
        }
    }

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */

    fun removeHuman(name: String): Boolean {
        if (!name.matches(correctName)) throw java.lang.IllegalArgumentException()
        book.remove(name) ?: return false
        return true
    }

    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */

    fun addPhone(name: String, phone: String): Boolean {
        if (!phone.matches(correctPhone) || !name.matches(correctName)) throw IllegalArgumentException()
        if (!(book[name]?.contains(phone) ?: return false) && !listOfPhone.contains(phone)) {
            book[name]?.add(phone) ?: return false
            return true
        }
        listOfPhone.add(phone)
        return false
    }

    init {
        listOfPhone.clear()
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */

    fun removePhone(name: String, phone: String): Boolean {
        if (!phone.matches(correctPhone) || !name.matches(correctName)) throw IllegalArgumentException()
        if (book[name]?.contains(phone) ?: return false) {
            book[name]?.remove(phone) ?: return false
            return true
        }
        return false
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> {
        if (!name.matches(correctName)) throw IllegalArgumentException()
        return book[name] ?: setOf()
    }

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        if (!phone.matches(correctPhone)) throw IllegalArgumentException()
        for ((key, value) in book) if (phone in value) return key
        return null
    }

    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */
    override fun equals(other: Any?): Boolean {
        if (other is PhoneBook)
            for ((key, value) in book) {
                if (other.book[key] != value) return false
            }
        return true
    }

    override fun hashCode(): Int {
        var hash = 0
        var t = 0
        for ((key, value) in book) {
            key.toCharArray().forEach { t += it.toInt() }
            hash += t * 31 + value.size * 31
            t = 0
        }
        return hash
    }
}
