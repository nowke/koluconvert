package `in`.nowke.koluconvert.models

/**
 * Units
 */
enum class Units(var factor: Double, var suffix: String, var precision: Int) {
    METER(1.0, " m", 4),
    MADHUR_KOLU(0.743, " mk", 2),
    PAYYANNUR_KOLU(0.7114, " pk", 2),
    FOOT(1 / 3.28, "\'", 2),

    SQUARE_METER(10.7639, " m²", 4),
    SQUARE_FEET(1.0, " sq.ft", 4),

    CUBIC_METER(35.28, " m³", 4),
    CUBIC_FEET(1.0, " cu.ft", 4)
}