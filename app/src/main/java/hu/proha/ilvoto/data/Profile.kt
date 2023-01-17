package hu.proha.ilvoto.data

data class Profile(
    var id: String = "",
    var groups: ArrayList<Group>,
    var email: String
)