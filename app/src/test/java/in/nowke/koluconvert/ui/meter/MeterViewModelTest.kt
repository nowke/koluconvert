package `in`.nowke.koluconvert.ui.meter

import `in`.nowke.koluconvert.models.Units

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * MeterViewModel test
 */
@RunWith(JUnit4::class)
class MeterViewModelTest {
    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var model: MeterViewModel

    @Before
    fun setup() {
        model = MeterViewModel()
    }

    @Test
    fun test_empty_values_convert() {
        model.convert()
        assertEquals(Units.METER, model.meterType.get())
        assertNull(model.meter.get())
        assertEquals(model.textFoot.get(), "0.0\' = 0\' 0.0\"")
        assertEquals(model.textMadhur.get(), "0 mk 0.0 ang")
        assertEquals(model.textPayyannur.get(), "0 pk 0.0 ang")
    }

    @Test
    fun test_set_meter_type_meter() {
        model.setMeterType(1)
        assertEquals(Units.METER, model.meterType.get())
    }

    @Test
    fun test_set_meter_type_square_meter() {
        model.setMeterType(2)
        assertEquals(Units.SQUARE_METER, model.meterType.get())
    }

    @Test
    fun test_set_meter_type_cubic_meter() {
        model.setMeterType(3)
        assertEquals(Units.CUBIC_METER, model.meterType.get())
    }

    @Test
    fun test_meter_value_convert() {
        model.meter.set("12.5")
        model.convert()
        assertEquals("41.0\' = 41\' 0.0\"", model.textFoot.get())
        assertEquals("16 mk 19.8 ang", model.textMadhur.get())
        assertEquals("17 pk 13.7 ang", model.textPayyannur.get())
    }

    @Test
    fun test_meter_with_meter_type_change() {
        // Set `meter` type
        model.setMeterType(1)
        model.meter.set("12.5")
        model.convert()

        // Set `Square meter` type
        model.setMeterType(2)
        assertEquals("134.5487 sq.ft", model.textFoot.get())

        // Set `Cubic meter` type
        model.setMeterType(3)
        assertEquals("441.0 cu.ft", model.textFoot.get())

        // Set back to `meter` type
        model.setMeterType(1)
        assertEquals("41.0\' = 41\' 0.0\"", model.textFoot.get())
        assertEquals("16 mk 19.8 ang", model.textMadhur.get())
        assertEquals("17 pk 13.7 ang", model.textPayyannur.get())
    }
}