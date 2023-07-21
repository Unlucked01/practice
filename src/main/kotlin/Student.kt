import jdk.incubator.foreign.SymbolLookup
import java.lang.IllegalArgumentException

data class Student(val fullname: String, val school: String, val physicsScore: Int = 0,
                   val mathScore: Int = 0, val computerScore: Int = 0) {
    init {
        if (physicsScore > 100 || computerScore > 100 || mathScore > 100 ){
            throw IllegalArgumentException("Баллы не могут быть > 100!")
        }
        if (physicsScore < 0 || computerScore < 0 || mathScore < 0 ){
            throw IllegalArgumentException("Баллы не могут быть < 0!")
        }
    }

    override fun toString(): String {
        return  "ФИО: $fullname\n" +
                "Школа: $school\n" +
                "Баллы по физике: $physicsScore\n" +
                "Баллы по математике: $mathScore\n" +
                "Баллы по информатике: $computerScore\n"
    }

    infix fun hasBetterScoresThan(other: Student): Boolean =
        (physicsScore + mathScore + computerScore) > (other.physicsScore + other.mathScore + other.computerScore)

    operator fun unaryMinus(): Student =
        copy(fullname = fullname.reversed(), school = school.reversed())

    fun getAverageScore(): Double = (physicsScore + mathScore + computerScore) / 3.0

    companion object {
        fun List<Student>.findByLastName(lastName: String): List<Student> =
            filter { it.fullname.contains(lastName) }

        fun List<Student>.findBySchool(school: String): List<Student> =
            filter { it.school == school }

        fun List<Student>.findSchoolWithLowestAverageScore(): Pair<String, Double>? {
            return groupBy { it.school }
                .mapValues { (_, students) -> students.map { it.getAverageScore() }.min() }
                .minByOrNull { it.value }
                ?.let { school -> Pair(school.key, school.value) }
        }

        fun List<Student>.findSchoolWithHighestAverageScore(): Pair<String, Double>? {
            return groupBy { it.school }
                .mapValues { (_, students) -> students.map { it.getAverageScore() }.max() }
                .maxByOrNull { it.value }
                ?.let { school -> Pair(school.key, school.value) }
        }

        fun List<Student>.getFirstStudent(): Student = first()

        fun List<Student>.plus(student: Student) : List<Student> = this + student

        fun List<Student>.findStudent(con : Student.() -> Boolean) : List<Student> = filter(con)
    }
}