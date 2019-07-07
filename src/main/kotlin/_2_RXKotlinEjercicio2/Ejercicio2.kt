package _2_RXKotlinEjercicio2

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class Ejercicio2 {
    fun ejecutar() {
        println("Initial Out put with a = 15, b = 10")

        var calculator = ReactiveCalculator(b = 10, a = 15)
        println("Enter a = <number> or b = <number> in separate lines \nexit to exit the program")

        var line: String? = null

        do {
            line = readLine()
            calculator.handleInput(line)
        } while (line != null && !line.toLowerCase().equals("exit"))

    }
}