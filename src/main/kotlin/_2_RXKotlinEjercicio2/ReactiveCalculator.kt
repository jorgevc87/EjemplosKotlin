package _2_RXKotlinEjercicio2

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.regex.Matcher
import java.util.regex.Pattern

class ReactiveCalculator(a: Int, b: Int) {
    internal val subjectAdd: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectSub: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectMult: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectDiv: Subject<Pair<Int, Int>> = PublishSubject.create()

    internal val subjectCalc: Subject<ReactiveCalculator> = PublishSubject.create()

    internal var nums: Pair<Int, Int> = Pair(0, 0)

    init {
        nums = Pair(a, b)

        subjectAdd.map({ it.first + it.second }).subscribe({ println("Add = $it") })
        subjectSub.map({ it.first - it.second }).subscribe({ println("Subt = $it") })
        subjectMult.map({ it.first * it.second }).subscribe({ println("Mult = $it") })
        subjectDiv.map({ it.first / it.second }).subscribe({ println("Div = $it") })

        subjectCalc.subscribe({
            with(it) {
                calculateAddition()
                calculateSubstraction()
                calculateMultiplication()
                calculateDivision()
            }
        })

        subjectCalc.onNext(this)
    }

    fun calculateAddition() {
        subjectAdd.onNext(nums)
    }

    fun calculateSubstraction() {
        subjectSub.onNext(nums)
    }

    fun calculateMultiplication() {
        subjectMult.onNext(nums)
    }

    fun calculateDivision() {
        subjectDiv.onNext(nums)
    }

    fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(this)
    }

    fun handleInput(inputLine: String?) {

        if (!inputLine.equals("exit")) {
            val pattern = Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)");

            var a: Int? = null
            var b: Int? = null

            var matcher: Matcher = pattern.matcher(inputLine)

            if (matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(1).toLowerCase().equals("a")) {
                    a = matcher.group(2).toInt()
                } else if (matcher.group(1).toLowerCase().equals("b")) {
                    b = matcher.group(2).toInt()
                }
            }

            when {
                a != null && b != null -> modifyNumbers(a = a, b = b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("invalid input")
            }
        }
    }
}