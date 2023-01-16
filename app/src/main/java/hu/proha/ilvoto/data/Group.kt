package hu.proha.ilvoto.data

data class Group(
    var name: String,
    var members: ArrayList<String>,
    var owner: String
)