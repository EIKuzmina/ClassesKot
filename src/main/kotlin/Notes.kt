data class Notes(val title: String, val text: String, val privacy: Int,
                 val commentPrivacy: Int, val privacyView: String, val privacyComment: String,
                 val noteId: Int?, var coments: List<Comment> = mutableListOf()
)

val noteService = mutableListOf(Notes("Summer", "Hello!", 2, 1,
        "Yes", "No",5))
