package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * MadhurKoluUnitTest
 */
@RunWith(Enclosed::class)
class MadhurKoluUnitTest {

    @RunWith(Parameterized::class)
    class MadhurKoluAngulaToDecimalTest(val kolu: Double, val angula: Double, val expectedKoluInDecimal: Double) {

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
        fun madhur_kolu_angula_to_decimal() {
            val koluUnit: MadhurKoluUnit = MadhurKoluUnit(kolu, angula)
            val expectedRepresentationText = "${kolu.toInt()} mk ${angula.toFixed(1)} ang"
            assertEquals(expectedKoluInDecimal, koluUnit.value.toFixed(2), 0.0)
            assertEquals(expectedRepresentationText, koluUnit.toString())
        }
    }

    @RunWith(Parameterized::class)
    class MadhurKoluDecimalToKoluAngula(val koluDecimal: Double, val expectedKolu: Double, val expectedAngula: Double) {

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
        fun madhur_kolu_decimal_to_kolu_angula() {
            val koluUnit: MadhurKoluUnit = MadhurKoluUnit(koluDecimal)
            val expectedRepresentationText = "${koluUnit.kolu.toInt()} mk ${koluUnit.angula.toFixed(1)} ang"
            assertEquals(expectedKolu, koluUnit.kolu.toFixed(2), 0.0)
            assertEquals(expectedAngula, koluUnit.angula.toFixed(1), 0.0)
            assertEquals(expectedRepresentationText, koluUnit.toString())
        }
    }
}