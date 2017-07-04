package `in`.nowke.koluconvert.models

import `in`.nowke.koluconvert.common.Extensions.toFixed

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

/**
 * ConversionTest
 */
@RunWith(Enclosed::class)
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
            val madhur_kolu: MadhurKoluUnit = MadhurKoluUnit(kolu, angula)
            val convertedValue: Double = Conversion.convert(madhur_kolu.value, Units.MADHUR_KOLU, Units.METER)
            val convertedValueText: String = Conversion.convertToText(madhur_kolu.value, Units.MADHUR_KOLU, Units.METER)
            assertEquals(meter, convertedValue, 0.0001)
            assertEquals("${meter.toFixed(4)} m", convertedValueText)

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
            val madhur_kolu: MadhurKoluUnit = MadhurKoluUnit(kolu, angula)
            val convertedValue: Double = Conversion.convert(madhur_kolu.value, Units.MADHUR_KOLU, Units.FOOT)
            val convertedValueText: String = Conversion.convertToText(madhur_kolu.value, Units.MADHUR_KOLU, Units.FOOT)
            assertEquals(foot, convertedValue, 0.01)
            assertEquals("${foot.toFixed(2)}\'", convertedValueText)
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
            val madhur_kolu: MadhurKoluUnit = MadhurKoluUnit(kolu, angula)
            val convertedValue: Double = Conversion.convert(madhur_kolu.value, Units.MADHUR_KOLU, Units.PAYYANNUR_KOLU)
            val convertedValueText: String = Conversion.convertToText(madhur_kolu.value, Units.MADHUR_KOLU, Units.PAYYANNUR_KOLU)
            assertEquals(payyannur_kolu, convertedValue, 0.01)
            assertEquals("${payyannur_kolu.toFixed(2)} pk", convertedValueText)
        }
    }

    // ******************************************************************* //
    // ************* 2. Tests for `Payyannur Kolu` to others ************* //
    // ******************************************************************* //

    @RunWith(Parameterized::class)
    class PayyannurKoluAngulaToMeterTest(val kolu: Double, val angula: Double, val meter: Double) {

        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 0.7114),
                        arrayOf(12.0, 6.0, 8.7147),
                        arrayOf(0.0, 12.0, 0.3557)
                )
            }
        }

        @Test
        fun payyannur_kolu_angula_to_meter() {
            val payyannur_kolu: PayyannurKoluUnit = PayyannurKoluUnit(kolu, angula)
            val convertedValue: Double = Conversion.convert(payyannur_kolu.value, Units.PAYYANNUR_KOLU, Units.METER)
            val convertedValueText: String = Conversion.convertToText(payyannur_kolu.value, Units.PAYYANNUR_KOLU, Units.METER)
            assertEquals(meter, convertedValue, 0.0001)
            assertEquals("${meter.toFixed(4)} m", convertedValueText)
        }
    }

    @RunWith(Parameterized::class)
    class PayyannurKoluAngulaToFootTest(val kolu: Double, val angula: Double, val foot: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 2.33),
                        arrayOf(12.0, 6.0, 28.58),
                        arrayOf(0.0, 12.0, 1.17)
                )
            }
        }

        @Test
        fun payyannur_kolu_angula_to_foot() {
            val payyannur_kolu: PayyannurKoluUnit = PayyannurKoluUnit(kolu, angula)
            val convertedValue: Double = Conversion.convert(payyannur_kolu.value, Units.PAYYANNUR_KOLU, Units.FOOT)
            val convertedValueText: String = Conversion.convertToText(payyannur_kolu.value, Units.PAYYANNUR_KOLU, Units.FOOT)
            assertEquals(foot, convertedValue, 0.01)
            assertEquals("${foot.toFixed(2)}\'", convertedValueText)
        }
    }

    @RunWith(Parameterized::class)
    class PayyannurKoluAngulaToMadhurKolu(val kolu: Double, val angula: Double, val madhur_kolu: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 0.96),
                        arrayOf(12.0, 6.0, 11.73),
                        arrayOf(0.0, 12.0, 0.48)
                )
            }
        }

        @Test
        fun payyannur_kolu_angula_to_foot() {
            val payyannur_kolu: PayyannurKoluUnit = PayyannurKoluUnit(kolu, angula)
            val convertedValue: Double = Conversion.convert(payyannur_kolu.value, Units.PAYYANNUR_KOLU, Units.MADHUR_KOLU)
            val convertedValueText: String = Conversion.convertToText(payyannur_kolu.value, Units.PAYYANNUR_KOLU, Units.MADHUR_KOLU)
            assertEquals(madhur_kolu, convertedValue, 0.01)
            assertEquals("${madhur_kolu.toFixed(2)} mk", convertedValueText)
        }
    }

    // ********************************************************* //
    // ************* 3. Tests for `Foot` to others ************* //
    // ********************************************************* //

    @RunWith(Parameterized::class)
    class FootToMeter(val foot: Double, val inch: Double, val meter: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 0.3049),
                        arrayOf(12.0, 6.0, 3.811),
                        arrayOf(0.0, 6.0, 0.1524)
                )
            }
        }

        @Test
        fun foot_to_meter() {
            val foot: FootUnit = FootUnit(foot, inch)
            val convertedValue: Double = Conversion.convert(foot.value, Units.FOOT, Units.METER)
            val convertedValueText: String = Conversion.convertToText(foot.value, Units.FOOT, Units.METER)
            assertEquals(meter, convertedValue, 0.0001)
            assertEquals("${meter.toFixed(4)} m", convertedValueText)
        }
    }

    @RunWith(Parameterized::class)
    class FootToMadhurKolu(val foot: Double, val inch: Double, val madhur_kolu: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 0.41),
                        arrayOf(12.0, 6.0, 5.13),
                        arrayOf(0.0, 6.0, 0.21)
                )
            }
        }

        @Test
        fun foot_to_madhur_kolu() {
            val foot: FootUnit = FootUnit(foot, inch)
            val convertedValue: Double = Conversion.convert(foot.value, Units.FOOT, Units.MADHUR_KOLU)
            val convertedValueText: String = Conversion.convertToText(foot.value, Units.FOOT, Units.MADHUR_KOLU)
            assertEquals(madhur_kolu, convertedValue, 0.01)
            assertEquals("${madhur_kolu.toFixed(2)} mk", convertedValueText)
        }
    }

    @RunWith(Parameterized::class)
    class FootToPayyannurKolu(val foot: Double, val inch: Double, val payyannur_kolu: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0, 0.43),
                        arrayOf(12.0, 6.0, 5.36),
                        arrayOf(0.0, 6.0, 0.21)
                )
            }
        }

        @Test
        fun foot_to_payyannur_kolu() {
            val foot: FootUnit = FootUnit(foot, inch)
            val convertedValue: Double = Conversion.convert(foot.value, Units.FOOT, Units.PAYYANNUR_KOLU)
            val convertedValueText: String = Conversion.convertToText(foot.value, Units.FOOT, Units.PAYYANNUR_KOLU)
            assertEquals(payyannur_kolu, convertedValue, 0.01)
            assertEquals("${payyannur_kolu.toFixed(2)} pk", convertedValueText)
        }
    }

    // **************************************************************** //
    // ************* 4. Tests for `Square Feet` to others ************* //
    // **************************************************************** //

    @RunWith(Parameterized::class)
    class SquareFeetToSquareMeter(val square_feet: Double, val square_meter: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0929),
                        arrayOf(20.0, 1.8581),
                        arrayOf(105.5, 9.8013)
                )
            }
        }

        @Test
        fun square_feet_to_square_meter() {
            val convertedValue: Double = Conversion.convert(square_feet, Units.SQUARE_FEET, Units.SQUARE_METER)
            val convertedValueText: String = Conversion.convertToText(square_feet, Units.SQUARE_FEET, Units.SQUARE_METER)
            assertEquals(square_meter, convertedValue, 0.0001)
            assertEquals("${square_meter.toFixed(4)} m²", convertedValueText)
        }
    }

    // *************************************************************** //
    // ************* 5. Tests for `Cubic Feet` to others ************* //
    // *************************************************************** //

    @RunWith(Parameterized::class)
    class CubicFeetToCubicMeter(val cubic_feet: Double, val cubic_meter: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 0.0283),
                        arrayOf(20.0, 0.5669),
                        arrayOf(105.5, 2.9904)
                )
            }
        }

        @Test
        fun cubic_feet_to_cubic_meter() {
            val convertedValue: Double = Conversion.convert(cubic_feet, Units.CUBIC_FEET, Units.CUBIC_METER)
            val convertedValueText: String = Conversion.convertToText(cubic_feet, Units.CUBIC_FEET, Units.CUBIC_METER)
            assertEquals(cubic_meter, convertedValue, 0.0001)
            assertEquals("${cubic_meter.toFixed(4)} m³", convertedValueText)
        }
    }

    // ***************************************************************** //
    // ************* 6. Tests for `Square Meter` to others ************* //
    // ***************************************************************** //

    @RunWith(Parameterized::class)
    class SquareMeterToSquareFeet(val square_meter: Double, val square_feet: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 10.7639),
                        arrayOf(20.0, 215.278),
                        arrayOf(105.5, 1135.5914)
                )
            }
        }

        @Test
        fun square_meter_to_square_feet() {
            val convertedValue: Double = Conversion.convert(square_meter, Units.SQUARE_METER, Units.SQUARE_FEET)
            val convertedValueText: String = Conversion.convertToText(square_meter, Units.SQUARE_METER, Units.SQUARE_FEET)
            assertEquals(square_feet, convertedValue, 0.0001)
            assertEquals("${square_feet.toFixed(4)} sq.ft", convertedValueText)
        }
    }

    // **************************************************************** //
    // ************* 7. Tests for `Cubic Meter` to others ************* //
    // **************************************************************** //

    @RunWith(Parameterized::class)
    class CubicMeterToCubicFeet(val cubic_meter: Double, val cubic_feet: Double) {
        companion object {
            @JvmStatic
            @Parameters
            fun data(): List<Array<Double>> {
                return listOf(
                        arrayOf(1.0, 35.28),
                        arrayOf(20.0, 705.6),
                        arrayOf(105.5, 3722.04)
                )
            }
        }

        @Test
        fun cubic_meter_to_cubic_feet() {
            val convertedValue: Double = Conversion.convert(cubic_meter, Units.CUBIC_METER, Units.CUBIC_FEET)
            val convertedValueText: String = Conversion.convertToText(cubic_meter, Units.CUBIC_METER, Units.CUBIC_FEET)
            assertEquals(cubic_feet, convertedValue, 0.0001)
            assertEquals("${cubic_feet.toFixed(4)} cu.ft", convertedValueText)
        }
    }
}
