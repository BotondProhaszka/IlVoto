package hu.proha.ilvoto.data

data class Group(
    var id: String = "",
    var name: String,
    var members: ArrayList<String>,
    var owner: String
)