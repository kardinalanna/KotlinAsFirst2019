@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.lang.StringBuilder

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    //require(height > 0 && width > 0)
    //return MatrixImpl(height, width)
    if (height < 1 || width < 1) throw IllegalArgumentException()
    val result = MatrixImpl<E>(height, width, e)
    for (i in 0 until height)
        for (j in 0 until width)
            result[i, j] = e
    return result
}


/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */

//  (1) class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> { //ЗЖЕСЬ задаем класс с параметром e
//class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {


class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    //override val height: Int = TODO()    // override val width: Int = TODO()
    // т.к. эти свойсва не меняются, их можно сразу задать в конструкторе


    private val list = MutableList(height * width) { e } //конструктор
    //внутренний контейнер(приватная часть, где мы храним элементы нашей матрицы
    //сейчас все элементы матрийцы null

    private val anyList = MutableList(height) { MutableList(width) { e } }
    //храним значкения как лист листов


    override fun get(row: Int, column: Int): E =
        if (row > height || row > column) throw IndexOutOfBoundsException() else list[row * width + column]!!
    //во-превых проверяем на корректность входных данных
    //находим элемент в листе
    //\\ (1) = anyList[row][column]

    override fun get(cell: Cell): E = get(cell.row, cell.column)
    //ваще забавно - просто переиспользуем вышесозданную get, чтобы искать не только по двум числам( row и column ), но и по типу Cell( Cell(val row: Int, val column: Int)
    //\\ = anyList[cell.row][cell.column]


    override fun set(row: Int, column: Int, value: E) {
        list[row * width + column] = value// заменяем элемент из нашего приватного списка на другой
    }                                     //\\ (1) anyList[row][column] = value


    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)// заменяем элемент при входном cell
    }                                    //\\ (1) set(cell.row, set.column) = value


    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height && width == other.width && list === other.list //\\(1) Юльчика
    /*
    if (this == other) return true //впринципе сравнили
    if (javaClass != other.javaClass) return false // сравнили типы
    other as Matrix<*>    //!!!!!!!!!
    if (height == other.height) return true //число строк
    if (width == other.width) return true //число колонок
    if (list === other.list) return true //значения
    или
    = (this == other) && (javaClass == other.javaClass) && (height == other.height) && (width == other.width) && (list === other.list)
     */


    override fun toString(): String = buildString {
        append("[")
        for (i in 0 until height) append("${list.subList(i * width, (i + 1) * width)}, ")
        delete(length - 2, length)
        append("]")
    }

    /*override fun toString(): String  {
    val st = StringBuilder()
    st.append("[")
      for (row in 0 until height) {
      st.append("[")
      for (column in 0 until wight) { st.append(this{row, column])
      st.append(", ") }
      st.append("]")
      }
      //delete(length - 2, length)
      st.append("]")
      }
      st.append(']")
      return st.toString
      }
     */


    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + list.hashCode()
        return result
    }
}

