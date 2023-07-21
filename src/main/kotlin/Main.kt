import Student.Companion.findByLastName
import Student.Companion.findBySchool
import Student.Companion.findSchoolWithHighestAverageScore
import Student.Companion.findSchoolWithLowestAverageScore
import Student.Companion.findStudent
import Student.Companion.getFirstStudent
import java.lang.IllegalArgumentException

fun main() {
    printHeader()
    try{
        val students = mutableListOf(
            Student("Иванов И.И.", "Школа №1", 30, 50, 90),
            Student("Петров П.П.", "Школа №2", 11, 44, 75),
            Student("Сидоров С.С.", "Школа №1", 88, 77, 80),
            Student("Дмитриев Д.Д.", "Школа №3", 71, 80, 99)
        )
        val searchingArguments = listOf("Иванов", "Школа №2")
        executeOperations(students, searchingArguments)
    } catch (e : IllegalArgumentException){
        println("Вызвано исключение: ${e.message}\nПоверьте данные всех студентов!")
    }

}

fun printHeader(){
    println("\n┌" + "─".repeat(78) + "┐")
    println(String.format("%1s %55s %22s","│", "Практическая работа ст.гр. 21ВП2", "│"))
    println(String.format("%1s %45s %32s","│", "Копылова Егора", "│"))
    println(String.format("%1s %50s %27s","│","Предметная область ЕГЭ", "│"))
    println("└" + "─".repeat(78) + "┘\n")
}

fun executeOperations(students: List<Student>, arguments : List<String>){
    students.forEach{ student -> println(student) }
    val searchingSurname = arguments[0]
    val searchingSchool = arguments[1]

    println("Пример ипользования инфиксного метода: ${if (students[0] hasBetterScoresThan students[1]) "Верно" else "Неверно"}\n")

    println("Пример использования унарного минуса:\n${-students[0]}")

    val updatedStudents = students + Student("Алексеев А.А.", "Школа №3", 33, 67, 96)
    println("Пример использования плюса:\n${updatedStudents.last()}")

    println("Пример использования метода расширения вне базового класса:\n${students.getFirstStudent()}")

    val findedStudents = students.findStudent { physicsScore > 80 && mathScore > 70 && computerScore > 70  }
    println("Пример использования передачи лямбда функции:\n$findedStudents\n")

    println("Найдено по фамилии '$searchingSurname':")
    students.findByLastName(searchingSurname).forEach { println("${it.fullname}, ${it.school}.")}

    println("\nНайдено по школе '$searchingSchool':")
    students.findBySchool(searchingSchool).forEach { println("${it.fullname}, ${it.school}.")}

    val lowestSchoolScore = students.findSchoolWithLowestAverageScore()
    println("\nНайдена школа с минимальным средним баллом: '${lowestSchoolScore?.first}' (${String.format("%.2f", lowestSchoolScore?.second)})")

    val highestSchoolScore = students.findSchoolWithHighestAverageScore()
    println("\nНайдена школа с минимальным средним баллом: '${highestSchoolScore?.first}' (${String.format("%.2f", highestSchoolScore?.second)})")

}