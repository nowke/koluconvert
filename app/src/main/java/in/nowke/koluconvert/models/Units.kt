package `in`.nowke.koluconvert.models

/**
 * Created by nowke on 27/06/17.
 */
enum class Units(var factor: Double) {
    METER(1.0),
    MADHUR_KOLU(0.743),
    PAYYANNUR_KOLU(0.7114),
    FOOT(1 / 3.28),

    SQUARE_METER(1.0),
    SQUARE_FEET(10.7639),

    CUBIC_METER(1.0),
    CUBIC_FEET(35.28)
}