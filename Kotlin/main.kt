
import kotlin.math.abs
import kotlin.math.pow

fun main() {

    print("Loan Amount $")
    val principal = readLine()!!.toDouble()
    print("Repayment Amount $")
    val payment = readLine()!!.toDouble()
    print("How Many Years of Loan: ")
    val numberOfYears = readLine()!!.toDouble()
    print("Payment Frequency: ")
    val repayFreq = readLine()!!.toDouble()
    val numPay = numberOfYears * repayFreq

fun calculateInterest(): Double{
    val tolerableError = 10.0.pow(-5.0)
    var guess = 0.05/12 //let's start with a guess that the APR is 5%
    var updateGuess: Double

    fun f(x: Double): Double {
        return principal * x * (1 + x).pow(numPay) / ((1 + x).pow(numPay) - 1 ) - payment
    }

    fun fPrime(x: Double): Double{
        return principal * ((1 + x).pow(numPay) / (-1 + (1 + x).pow(numPay)) -
                numPay * x * (1 + x).pow((-1 + 2 * numPay)) /
                (-1 + (1 + x).pow(numPay)).pow(2) +
                numPay * x * (1 + x).pow((-1 + numPay)) /
                (-1 + (1 + x).pow(numPay)))
    }

    var k = 0
    while (k < 20){
        updateGuess = guess
        guess = updateGuess - f(updateGuess) / fPrime(updateGuess)
        val diff = abs(guess - updateGuess)
        if (diff < tolerableError)
            break
        k += 1
    }
return guess
}
    val interestPerPayment = calculateInterest()
    val interestRate = (interestPerPayment * repayFreq * 10000)/100 // this way we get APRs like 7.5% or 6.55%
    println("Interest Rate is ${"%.2f".format(interestRate)}%")
    println(interestPerPayment) // this figure is what is used for the iterest rate for the amortisation schedue

}
