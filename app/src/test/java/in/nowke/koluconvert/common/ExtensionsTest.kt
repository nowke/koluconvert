package `in`.nowke.koluconvert.common

import `in`.nowke.koluconvert.common.Extensions.toFixed

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * ExtensionsTest
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
            assertEquals(expectedValue, myNumber.toFixed(precision))
        }
    }
}