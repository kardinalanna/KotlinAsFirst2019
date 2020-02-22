package lesson4.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun sqRoots() {
        assertEquals(listOf<Double>(), sqRoots(-1.0))
        assertArrayEquals(listOf(0.0).toDoubleArray(), sqRoots(0.0).toDoubleArray(), 1e-5)
        assertArrayEquals(listOf(-5.0, 5.0).toDoubleArray(), sqRoots(25.0).toDoubleArray(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun biRoots() {
        assertEquals(listOf<Double>(), biRoots(0.0, 0.0, 1.0))
        assertEquals(listOf<Double>(), biRoots(0.0, 1.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, 2.0).toDoubleArray(),
            biRoots(0.0, 1.0, -4.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, -2.0, 4.0))
        assertArrayEquals(
            listOf(-1.0, 1.0).toDoubleArray(),
            biRoots(1.0, -2.0, 1.0).toDoubleArray(),
            1e-5
        )
        assertEquals(listOf<Double>(), biRoots(1.0, 3.0, 2.0))
        assertArrayEquals(
            listOf(-2.0, -1.0, 1.0, 2.0).toDoubleArray(),
            biRoots(1.0, -5.0, 4.0).sorted().toDoubleArray(),
            1e-5
        )
    }

    @Test
    @Tag("Example")
    fun negativeList() {
        assertEquals(listOf<Int>(), negativeList(listOf(1, 2, 3)))
        assertEquals(listOf(-1, -5), negativeList(listOf(-1, 2, 4, -5)))
    }

    @Test
    @Tag("Example")
    fun invertPositives() {
        val list1 = mutableListOf(1, 2, 3)
        invertPositives(list1)
        assertEquals(listOf(-1, -2, -3), list1)
        val list2 = mutableListOf(-1, 2, 4, -5)
        invertPositives(list2)
        assertEquals(listOf(-1, -2, -4, -5), list2)
    }

    @Test
    @Tag("Example")
    fun squares() {
        assertEquals(listOf(0), squares(listOf(0)))
        assertEquals(listOf(1, 4, 9), squares(listOf(1, 2, -3)))
    }

    @Test
    @Tag("Example")
    fun squaresVararg() {
        assertArrayEquals(arrayOf(0), squares(0))
        assertArrayEquals(arrayOf(1, 4, 9), squares(1, 2, -3))
    }

    @Test
    @Tag("Example")
    fun isPalindrome() {
        assertFalse(isPalindrome("Барабан"))
        assertTrue(isPalindrome("А роза упала на лапу Азора"))
        assertTrue(isPalindrome("Шалаш"))
    }

    @Test
    @Tag("Example")
    fun buildSumExample() {
        assertEquals("42 = 42", buildSumExample(listOf(42)))
        assertEquals("3 + 6 + 5 + 4 + 9 = 27", buildSumExample(listOf(3, 6, 5, 4, 9)))
    }

    @Test
    @Tag("Easy")
    fun abs() {
        assertEquals(0.0, abs(listOf()), 1e-5)
        assertEquals(3.0, abs(listOf(3.0)), 1e-5)
        assertEquals(5.0, abs(listOf(3.0, -4.0)), 1e-5)
        assertEquals(8.774964, abs(listOf(4.0, -5.0, 6.0)), 1e-5)
    }

    @Test
    @Tag("Easy")
    fun mean() {
        assertEquals(0.0, mean(listOf()), 1e-5)
        assertEquals(1.0, mean(listOf(1.0)), 1e-5)
        assertEquals(2.0, mean(listOf(3.0, 1.0, 2.0)), 1e-5)
        assertEquals(3.0, mean(listOf(0.0, 2.0, 7.0, 8.0, -2.0)), 1e-5)
    }

    @Test
    @Tag("Normal")
    fun center() {
        assertEquals(listOf<Double>(), center(mutableListOf()))
        assertArrayEquals(
            listOf(0.0).toDoubleArray(),
            center(mutableListOf(3.14)).toDoubleArray(),
            1e-5
        )
        assertArrayEquals(
            listOf(1.0, -1.0, 0.0).toDoubleArray(),
            center(mutableListOf(3.0, 1.0, 2.0)).toDoubleArray(),
            1e-5
        )
        assertArrayEquals(
            listOf(-3.0, -1.0, 4.0, 5.0, -5.0).toDoubleArray(),
            center(mutableListOf(0.0, 2.0, 7.0, 8.0, -2.0)).toDoubleArray(),
            1e-5
        )
        val toMutate = mutableListOf(-3.0, -1.0, 4.0, 5.0, -5.0)
        assertTrue(toMutate === center(toMutate)) { "You should mutate an input list, not create a copy" }
    }

    @Test
    @Tag("Normal")
    fun times() {
        assertEquals(0, times(listOf(), listOf()))
        assertEquals(-5, times(listOf(1, -4), listOf(3, 2)))
        assertEquals(-19, times(listOf(-1, 2, -3), listOf(3, -2, 4)))
    }

    @Test
    @Tag("Normal")
    fun polynom() {
        assertEquals(0, polynom(listOf(), 1000))
        assertEquals(42, polynom(listOf(42), -1000))
        assertEquals(13, polynom(listOf(3, 2), 5))
        assertEquals(0, polynom(listOf(2, -3, 1), 1))
        assertEquals(45, polynom(listOf(-7, 6, 4, -4, 1), -2))
    }

    @Test
    @Tag("Normal")
    fun accumulate() {
        assertEquals(listOf<Double>(), accumulate(arrayListOf()))
        assertArrayEquals(
            listOf(3).toIntArray(),
            accumulate(arrayListOf(3)).toIntArray()
        )
        assertArrayEquals(
            listOf(1, 3, 6, 10).toIntArray(),
            accumulate(arrayListOf(1, 2, 3, 4)).toIntArray()
        )
        val toMutate = mutableListOf(-3, -1, 4, 5, -5)
        assertTrue(toMutate === accumulate(toMutate)) { "You should mutate an input list, not create a copy" }
    }

    @Test
    @Tag("Normal")
    fun factorize() {
        assertEquals(listOf(2), factorize(2))
        assertEquals(listOf(3, 5, 5), factorize(75))
        assertEquals(listOf(2, 3, 3, 19), factorize(342))
    }

    @Test
    @Tag("Hard")
    fun factorizeToString() {
        assertEquals("2*209717", factorizeToString(419434))
        assertEquals("2", factorizeToString(2))
        assertEquals("3*5*5", factorizeToString(75))
        assertEquals("2*3*3*19", factorizeToString(342))
        assertEquals("7*7*31*31*151*151", factorizeToString(1073676289))
        assertEquals("1073676287", factorizeToString(1073676287))
        assertEquals(Int.MAX_VALUE.toString(), factorizeToString(Int.MAX_VALUE))
    }

    @Test
    @Tag("Normal")
    fun convert() {
        assertEquals(listOf(1), convert(1, 2))
        assertEquals(listOf(1, 2, 1, 0), convert(100, 4))
        assertEquals(listOf(1, 3, 12), convert(250, 14))
        assertEquals(listOf(2, 14, 12), convert(1000, 19))
    }

    @Test
    @Tag("Hard")
    fun convertToString() {
        assertEquals("1", convertToString(1, 2))
        assertEquals("1210", convertToString(100, 4))
        assertEquals("13c", convertToString(250, 14))
        assertEquals("2ec", convertToString(1000, 19))
        assertEquals("z", convertToString(35, 36))
        assertEquals("a02220281", convertToString(Int.MAX_VALUE, 11))
    }


    @Test
    @Tag("Normal")
    fun decimal() {
        assertEquals(1, decimal(listOf(1), 2))
        assertEquals(100, decimal(listOf(1, 2, 1, 0), 4))
        assertEquals(250, decimal(listOf(1, 3, 12), 14))
        assertEquals(1000, decimal(listOf(2, 14, 12), 19))
    }

    @Test
    @Tag("Hard")
    fun decimalFromString() {
        assertEquals(1, decimalFromString("1", 2))
        assertEquals(100, decimalFromString("1210", 4))
        assertEquals(250, decimalFromString("13c", 14))
        assertEquals(1000, decimalFromString("2ec", 19))
        assertEquals(35, decimalFromString("z", 36))
        assertEquals(Int.MAX_VALUE, decimalFromString("a02220281", 11))
    }


    @Test
    @Tag("Hard")
    fun roman() {
        assertEquals("I", roman(1))
        assertEquals("MMM", roman(3000))
        assertEquals("MCMLXXVIII", roman(1978))
        assertEquals("DCXCIV", roman(694))
        assertEquals("XLIX", roman(49))
    }


    @Test
    @Tag("Impossible")
    fun russian() {
        assertEquals("триста семьдесят пять", russian(375))
        assertEquals("двадцать две тысячи девятьсот шестьдесят четыре", russian(22964))
        assertEquals("сто девятнадцать тысяч пятьсот восемь", russian(119508))
        assertEquals("две тысячи три", russian(2003))
        assertEquals("двести тысяч два", russian(200002))
        assertEquals("девятьсот тысяч", russian(900000))
        assertEquals("двенадцать", russian(12))
    }
}

/*
 @Test
    fun flat() {
        assertEquals(listOf("Tаврическая 54-67", "Московская 34-7"), flat("кухня 31", "input/sizeOfFlat4.txt"))
         assertEquals(listOf("Tаврическая 54-67", "Московская 34-7"), flat("кухня 6, комната 9", "input/sizeOfFlat.txt"))
        // assertEquals(listOf<String>(), flat("кухня 60, комната 9", "input/sizeOfFlat.txt"))
      //  assertThrows(IllegalArgumentException::class.java) { flat("кухня 31", "input/sizeOfFla 3t.txt") }
        assertThrows(IOException::class.java) { flat("кухня 31", "input/sizeOfFla 4 t.txt") }
    }

//"Таврическая 54-67", "Московская 34-7"

    @Test
    fun trass() {
        assertEquals(5.975, trass("input/trass.txt", "B2:C3"), 1e-5)
        // assertEquals(listOf("Таврическая 54-67", "Московская 34-7"), flat("кухня 6, комната 9", "input/sizeOfFlat.txt"))
        // assertEquals(listOf<String>(), flat("кухня 60, комната 9", "input/sizeOfFlat.txt"))
        //assertThrows(IllegalArgumentException::class.java) {trass("input/trass.txt", "A1:B2") }
        //assertThrows(IOException::class.java) { trass("input/tras s.txt", "A1:B2") }
    }


    @Test
    fun water() {
        //  assertEquals(88, water("input/water.txt", "Май 2...7"))
        //assertEquals(34, water("input/water.txt", "Май 9...Июль 4"))
        assertThrows(IllegalArgumentException::class.java) { water("input/water1.txt", "Май 2...7") }
        assertThrows(IOException::class.java) { water("input/tras s.txt", "Май 2...7") }
    }


    @Test
    fun goods() {
        //assertThrows(IllegalArgumentException::class.java) { goods("input/goods.txt", "000000 90") }
        //assertThrows(IOException::class.java) { goods("input/tras s.txt", "000000 9") }
        assertEquals(
            listOf(
                "сметана, достаточно, общая стоимость 60 р",
                "хлеб, недостаточно, общая стоимость 5 р",
                "тыква, недостаточно, общая стоимость 17 р"
            ),
            goods("input/goods.txt", "* 6")
        )
    }

    @Test
    fun ter() {
        assertEquals(listOf(3, 4, 5), ter("input/ter.txt", "Y & C"))
        assertEquals(listOf(1, 2, 4, 5), ter("input/ter.txt", "Y & !A"))
        assertThrows(IllegalArgumentException::class.java) { ter("input/ter1.txt", "Y & C") }
        assertThrows(IOException::class.java) { ter("input/tras s.txt", "Y & C") }

    }

    @Test
    fun flyTime() {
        assertEquals(
            listOf<Pair<String, String>>("LO210" to "LV2210"),
            flyTime("input/fly.txt", "Petersburg", "Frunkfurt")
        )
        assertEquals(
            listOf<Pair<String, String>>("LD220" to "JA909", "FV1234" to "JA909", "LO210" to "JA909"),
            flyTime("input/fly.txt", "Petersburg", "Tokio")
        )
        assertThrows(IllegalArgumentException::class.java) { flyTime("input/ter1.txt", "Y & C", "llkl") }
        assertThrows(IOException::class.java) { flyTime("input/tras s.txt", "Lkfl", "London") }
    }
 */