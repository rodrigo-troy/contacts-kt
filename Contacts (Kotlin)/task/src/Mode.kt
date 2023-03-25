package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 25-03-23
 * Time: 16:12
 */
enum class Mode(val options: List<Option>) {
    RECORD(
        listOf(
            Option.DELETE,
            Option.EDIT,
            Option.NUMBER
        )
    ),
    MENU(
        listOf(
            Option.ADD,
            Option.LIST,
            Option.SEARCH,
            Option.COUNT,
            Option.EXIT
        )
    ),
    SEARCH(
        listOf(
            Option.BACK,
            Option.AGAIN,
            Option.NUMBER
        )
    ),
    LIST(
        listOf(
            Option.BACK,
            Option.NUMBER
        )
    ),
}
