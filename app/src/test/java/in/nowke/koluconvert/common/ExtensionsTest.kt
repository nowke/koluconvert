package `in`.nowke.koluconvert.common

import `in`.nowke.koluconvert.common.Extensions.toFixed
import `in`.nowke.koluconvert.common.Extensions.toDoubleFixed

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Extensions test
 */
@RunWith(Enclosed::class)
class ExtensionsTest {

    @RunWith(Parameterized::class)
    class TestToFixedDouble(val doubleValue: Double, val precision: Int, val expectedValue: Double) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data(): List<Array<Any>> {
                return listOf(
                        arrayOf(5.256585, 2, 5.26),
                        arrayOf(9.14912, 4, 9.1491),
                        arrayOf(15.25004, 3, 15.25),
                        arrayOf(17.5, 0, 18.0)
                )
            }
        }

        @Test
        fun test_to_fixed_double() {
            val myNumber: Double = doubleValue
            assertEquals(expectedValue, myNumber.toFixed(precision), 0.0)
        }
    }

    @RunWith(Parameterized::class)
    class TestStringToDouble(val stringValue: String?, val expectedDoubleValue: Double) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data(): List<Array<out Any?>> {
                return listOf(
                        arrayOf("2.1", 2.1),
                        arrayOf(".12", 0.12),
                        arrayOf("", 0.0),
                        arrayOf(null, 0.0),
                        arrayOf(".", 0.0),
                        arrayOf("1.", 1.0)
                )
            }
        }

        @Test
        fun test_string_to_double() {
            val myStr: String? = stringValue
            assertEquals(expectedDoubleValue, myStr.toDoubleFixed(), 0.0)
        }
    }
}