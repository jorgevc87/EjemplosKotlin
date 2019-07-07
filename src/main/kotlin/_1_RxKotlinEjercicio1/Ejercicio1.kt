package _1_RxKotlinEjercicio1

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

class Ejercicio1{
    fun ejecutar(){
        var list:List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f)

        var observable:Observable<Any> = list.toObservable()

        observable.subscribeBy(
            //lamed arguments for lambda Subscribers
        onNext={
            print(it)
        },

        onError = {
            it.printStackTrace()
        },

        onComplete = {
            println("Done!")
        }

        )
    }
}