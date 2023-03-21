package contacts

/**
 * Created with IntelliJ IDEA.
$ Project: Contacts (Kotlin)
 * User: rodrigotroy
 * Date: 20-03-23
 * Time: 11:28
 */
class Person(
    name: String,
    phoneNumber: String = "",
    private var surname: String = "",
    private var birthDate: String = "",
    private var genre: Genre = Genre.UNKNOWN
) : Contact(name, phoneNumber, ContactType.PERSON) {

    fun setSurname(newSurname: String) {
        surname = newSurname.ifEmpty {
            "[no number]"
        }
    }

    fun getSurname(): String {
        return surname
    }

    fun getBirthDate(): String {
        return birthDate
    }

    fun setBirthDate(newBirthDate: String) {
        birthDate = newBirthDate
    }

    fun getGenre(): Genre {
        return genre
    }

    fun setGenre(newGenre: Genre) {
        genre = newGenre
    }

    override fun toString(): String {
        return "Name: ${getName()}\n" +
                "Surname: $surname\n" +
                "Birth date: $birthDate\n" +
                "Genre: ${genre.value}\n" +
                "Number: ${getPhoneNumber()}\n" +
                "Time created: ${getCreationDate()}\n" +
                "Time last edit: ${getEditDate()}"
    }
}
