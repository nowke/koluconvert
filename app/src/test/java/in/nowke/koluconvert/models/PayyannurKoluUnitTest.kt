package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * PayyannurKoluUnitTest
 */
@RunWith(Enclosed::class)
class PayyannurKoluUnitTest {

    @RunWith(Parameterized::class)
    class PayyannurKoluAngulaToDecimalTest(val kolu: Double, val angula: Double, val expectedKoluInDecimal: Double) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(0.0, 6.0, 0.25),
                        arrayOf(5.0, 17.0, 5.71),
                        arrayOf(12.0, 0.0, 12.0)
                )
            }
        }

        @Test
        fun payyannur_kolu_angula_to_decimal() {
            val koluUnit: PayyannurKoluUnit = PayyannurKoluUnit(kolu, angula)
            val expectedRepresentationText = "${kolu.toInt()} pk ${angula.toFixed(1)} ang"
            assertEquals(expectedKoluInDecimal, koluUnit.value.toFixed(2))
            assertEquals(expectedRepresentationText, koluUnit.toString())
        }
    }

    @RunWith(Parameterized::class)
    class PayyannurKoluDecimalToKoluAngula(val koluDecimal: Double, val expectedKolu: Double, val expectedAngula: Double) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(0.25, 0.0, 6.0),
                        arrayOf(5.71, 5.0, 17.0),
                        arrayOf(12.0, 12.0, 0.0)
                )
            }
        }

        @Test
        fun payyannur_kolu_decimal_to_kolu_angula() {
            val koluUnit: PayyannurKoluUnit = PayyannurKoluUnit(koluDecimal)
            val expectedRepresentationText = "${koluUnit.kolu.toInt()} pk ${koluUnit.angula.toFixed(1)} ang"
            assertEquals(expectedKolu, koluUnit.kolu.toFixed(2))
            assertEquals(expectedAngula, koluUnit.angula.toFixed(1))
            assertEquals(expectedRepresentationText, koluUnit.toString())
        }
    }
}