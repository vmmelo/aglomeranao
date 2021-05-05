package br.ufpe.cin.aglomerano

data class Occurrence(
        val userId: String = "",
        val email: String? = "",
        val time: String = "",
        val date: String? = "",
        val description: String? = "",
        val latitude: Double? = 0.0,
        val longitude: Double? = 0.0,
        val placeName: String? = "",
) {

}
