package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.models.Units

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * KoluViewModelTest
 */
@RunWith(JUnit4::class)
class KoluViewModelTest {
    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var model: KoluViewModel

    @Before
    fun setup() {
        model = KoluViewModel()
    }

    @Test
    fun test_empty_values_convert() {
        model.convert()
        assertEquals(Units.MADHUR_KOLU, model.koluType.get())
        assertNull(model.kolu.get())
        assertNull(model.angula.get())
        assertEquals("0.0 m", model.textMeter.get())
        assertEquals("0.0\' = 0\' 0.0\"", model.textFoot.get())
        assertEquals("0 pk 0.0 ang", model.textKolu.get())
    }

    @Test
    fun test_set_kolu_type_madhur_kolu() {
        model.setKoluType(1)
        assertEquals(Units.MADHUR_KOLU, model.koluType.get())
    }

    @Test
    fun test_set_kolu_type_payyannur_kolu() {
        model.setKoluType(2)
        assertEquals(Units.PAYYANNUR_KOLU, model.koluType.get())
    }

    @Test
    fun test_kolu_angula_values_convert() {
        model.kolu.set("12")
        model.angula.set("6")
        model.convert()
        assertEquals("9.1017 m", model.textMeter.get())
        assertEquals("29.85\' = 29\' 10.2\"", model.textFoot.get())
        assertEquals("12 pk 19.1 ang", model.textKolu.get())
    }

    @Test
    fun test_kolu_angula_values_with_change_in_kolu_type() {
        // Set to Madhur Kolu first
        model.setKoluType(1)

        // Set values
        model.kolu.set("12")
        model.angula.set("6")
        model.convert()
        assertEquals("9.1017 m", model.textMeter.get())
        assertEquals("29.85\' = 29\' 10.2\"", model.textFoot.get())
        assertEquals("12 pk 19.1 ang", model.textKolu.get())

        // Change to Payyannur Kolu
        model.setKoluType(2)
        assertEquals("8.7147 m", model.textMeter.get())
        assertEquals("28.58\' = 28\' 7.0\"", model.textFoot.get())
        assertEquals("11 mk 17.5 ang", model.textKolu.get())
    }
}