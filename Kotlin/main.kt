
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

    val error = 10.0.pow(-5.0)
    var approx = 0.05/12 //let's start with a guess that the APR is 5%
    var prevApprox: Double

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
        prevApprox = approx
        approx = prevApprox - f(prevApprox) / fPrime(prevApprox)
        val diff = abs(approx - prevApprox)
        if (diff < error)
            break
        k += 1
    }

    val interestRate = (approx * repayFreq * 10000)/100 // this way we get APRs like 7.5% or 6.55%
    println("Interest Rate is ${"%.2f".format(interestRate)}%")
    println(approx) // this figure is what is used for the iterest rate for the amortisation schedue

}
