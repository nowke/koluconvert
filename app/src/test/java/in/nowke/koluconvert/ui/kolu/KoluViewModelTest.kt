package `in`.nowke.koluconvert.ui.kolu

import `in`.nowke.koluconvert.models.Units

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
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

    private lateinit var koluViewModel: KoluViewModel

    @Before
    fun setup() {
        koluViewModel = KoluViewModel()
    }

    @Test
    fun test_empty_values_convert() {
        koluViewModel.convert()
        assertEquals(Units.MADHUR_KOLU, koluViewModel.koluType.get())
        assertNull(koluViewModel.kolu.get())
        assertNull(koluViewModel.angula.get())
        assertEquals(koluViewModel.textMeter.get(), "0.0 m")
        assertEquals(koluViewModel.textFoot.get(), "0.0\' = 0\' 0.0\"")
        assertEquals(koluViewModel.textKolu.get(), "0 pk 0.0 ang")
    }

    @Test
    fun test_set_kolu_type_madhur_kolu() {
        koluViewModel.setKoluType(1)
        assertEquals(Units.MADHUR_KOLU, koluViewModel.koluType.get())
    }

    @Test
    fun test_set_kolu_type_payyannur_kolu() {
        koluViewModel.setKoluType(2)
        assertEquals(Units.PAYYANNUR_KOLU, koluViewModel.koluType.get())
    }

    @Test
    fun test_kolu_angula_values_convert() {
        koluViewModel.kolu.set("12")
        koluViewModel.angula.set("6")
        koluViewModel.convert()
        assertEquals(koluViewModel.textMeter.get(), "9.1017 m")
        assertEquals(koluViewModel.textFoot.get(), "29.85\' = 29\' 10.2\"")
        assertEquals(koluViewModel.textKolu.get(), "12 pk 19.1 ang")
    }

    @Test
    fun test_kolu_angula_values_with_change_in_kolu_type() {
        // Set to Madhur Kolu first
        koluViewModel.setKoluType(1)

        // Set values
        koluViewModel.kolu.set("12")
        koluViewModel.angula.set("6")
        koluViewModel.convert()
        assertEquals(koluViewModel.textMeter.get(), "9.1017 m")
        assertEquals(koluViewModel.textFoot.get(), "29.85\' = 29\' 10.2\"")
        assertEquals(koluViewModel.textKolu.get(), "12 pk 19.1 ang")

        // Change to Payyannur Kolu
        koluViewModel.setKoluType(2)
        assertEquals(koluViewModel.textMeter.get(), "8.7147 m")
        assertEquals(koluViewModel.textFoot.get(), "28.58\' = 28\' 7.0\"")
        assertEquals(koluViewModel.textKolu.get(), "11 mk 17.5 ang")
    }
}