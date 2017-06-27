package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * FootUnitTest
 */
@RunWith(Enclosed::class)
class FootUnitTest {

    @RunWith(Parameterized::class)
    class FootInchToFootDecimalTest(val foot: Double, val inch: Double, val expectedFootInDecimal: Double) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(0.0, 6.0, 0.5),
                        arrayOf(5.0, 7.0, 5.58),
                        arrayOf(12.0, 0.0, 12.0)
                )
            }
        }

        @Test
        fun foot_inch_to_foot_decimal() {
            val footUnit: FootUnit = FootUnit(foot, inch)
            val expectedRepresentationText = "${expectedFootInDecimal.toFixed(2)}\' = ${foot.toInt()}\' ${inch.toFixed(1)}\""
            assertEquals(expectedFootInDecimal, footUnit.value.toFixed(2))
            assertEquals(expectedRepresentationText, footUnit.toString())
        }
    }

    @RunWith(Parameterized::class)
    class FootDecimalToFootInch(val footDecimal: Double, val expectedFoot: Double, val expectedInch: Double) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(0.5, 0.0, 6.0),
                        arrayOf(5.58, 5.0, 6.96),
                        arrayOf(12.0, 12.0, 0.0)
                )
            }
        }

        @Test
        fun foot_decimal_to_foot_inch() {
            val footUnit: FootUnit = FootUnit(footDecimal)
            val expectedRepresentationText = "${footDecimal.toFixed(2)}\' = ${expectedFoot.toInt()}\' ${expectedInch.toFixed(1)}\""
            assertEquals(expectedFoot, footUnit.foot.toFixed(2))
            assertEquals(expectedInch, footUnit.inch.toFixed(2))
            assertEquals(expectedRepresentationText, footUnit.toString())
        }
    }

}