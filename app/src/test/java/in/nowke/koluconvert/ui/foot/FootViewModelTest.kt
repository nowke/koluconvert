package `in`.nowke.koluconvert.ui.foot

import `in`.nowke.koluconvert.models.Units

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * FootViewModel test
 */
@RunWith(JUnit4::class)
class FootViewModelTest {
    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var model: FootViewModel

    @Before
    fun setup() {
        model = FootViewModel()
    }

    @Test
    fun test_empty_values_convert() {
        model.convert()
        assertEquals(Units.FOOT, model.footType.get())
        assertNull(model.foot.get())
        assertNull(model.inch.get())
        assertEquals(model.textMeter.get(), "0.0 m")
        assertEquals(model.textMadhur.get(), "0 mk 0.0 ang")
        assertEquals(model.textPayyannur.get(), "0 pk 0.0 ang")
    }

    @Test
    fun test_set_foot_type_foot() {
        model.setFootType(1)
        assertEquals(Units.FOOT, model.footType.get())
    }

    @Test
    fun test_set_foot_type_square_feet() {
        model.setFootType(2)
        assertEquals(Units.SQUARE_FEET, model.footType.get())
    }

    @Test
    fun test_set_foot_type_cubic_feet() {
        model.setFootType(3)
        assertEquals(Units.CUBIC_FEET, model.footType.get())
    }

    @Test
    fun test_foot_inch_value_convert() {
        model.foot.set("12")
        model.inch.set("6")
        model.convert()
        assertEquals("3.811 m", model.textMeter.get())
        assertEquals("5 mk 3.1 ang", model.textMadhur.get())
        assertEquals("5 pk 8.6 ang", model.textPayyannur.get())
    }

    @Test
    fun test_foot_with_foot_type_change() {
        // Set `Foot` Type
        model.setFootType(1)
        model.foot.set("12")
        model.convert()
        assertEquals("3.6585 m", model.textMeter.get())
        assertEquals("4 mk 22.2 ang", model.textMadhur.get())
        assertEquals("5 pk 3.4 ang", model.textPayyannur.get())

        // Set `Square Foot` type
        model.setFootType(2)
        assertEquals("1.1148 m²", model.textMeter.get())

        // Set `Cubic Foot` type
        model.setFootType(3)
        assertEquals("0.3401 m³", model.textMeter.get())

        // Set back to `Foot` type - should clear foot value
        model.setFootType(1)
        assertEquals(model.foot.get(), "")
    }
}