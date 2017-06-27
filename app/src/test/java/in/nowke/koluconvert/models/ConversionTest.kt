package `in`.nowke.koluconvert.models

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

/**
 * Created by nowke on 27/06/17.
 */
class ConversionTest {

    // **************************************************************** //
    // ************* 1. Tests for `Madhur Kolu` to others ************* //
    // **************************************************************** //

    @RunWith(Parameterized::class)
    class MadhurKoluAngulaToMeterTest(val kolu: Double, val angula: Double, val meter: Double) {

        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 0.743),
                        arrayOf(12.0, 6.0, 9.1017),
                        arrayOf(0.0, 12.0, 0.3715)
                )
            }
        }

        @Test
        fun madhur_kolu_angula_to_meter() {
            val madhur_kolu: MadhurKoluUnit = MadhurKoluUnit(kolu, angula);
            assertEquals(meter, Conversion.convert(madhur_kolu.value, Units.MADHUR_KOLU, Units.METER), 0.0001)
        }
    }

    @RunWith(Parameterized::class)
    class MadhurKoluAngulaToFootTest(val kolu: Double, val angula: Double, val foot: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 2.44),
                        arrayOf(12.0, 6.0, 29.85),
                        arrayOf(0.0, 12.0, 1.22)
                )
            }
        }

        @Test
        fun madhur_kolu_angula_to_foot() {
            val madhur_kolu: MadhurKoluUnit = MadhurKoluUnit(kolu, angula);
            assertEquals(foot, Conversion.convert(madhur_kolu.value, Units.MADHUR_KOLU, Units.FOOT), 0.01)
        }
    }

    @RunWith(Parameterized::class)
    class MadhurKoluAngulaToPayyannurKolu(val kolu: Double, val angula: Double, val payyannur_kolu: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 1.04),
                        arrayOf(12.0, 6.0, 12.79),
                        arrayOf(0.0, 12.0, 0.52)
                )
            }
        }

        @Test
        fun madhur_kolu_angula_to_foot() {
            val madhur_kolu: MadhurKoluUnit = MadhurKoluUnit(kolu, angula);
            assertEquals(payyannur_kolu, Conversion.convert(madhur_kolu.value, Units.MADHUR_KOLU, Units.PAYYANNUR_KOLU), 0.01)
        }
    }
}
