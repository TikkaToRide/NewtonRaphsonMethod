
import kotlin.math.abs
import kotlin.math.pow

fun main() {

    val principal = 154340
    val payment = 35316  //this is the repayment amount
    val numberOfYears = 5  //how many years is the loan for?
    val repayFreq = 1  //how many payments in the year eg. 1 = yearly, 12 = monthly
    val numPay = numberOfYears * repayFreq

    val error = 10.00.pow(-5.00)
    var approx = 0.05/12  //start with a guess that the APR is 5%
    var prevApprox: Double

    fun f(x: Double): Double {
        val base: Double = 1 + x
        return principal * x * base.pow(numPay) / (base.pow(numPay) - 1 ) - payment
    }

    fun fPrime(x: Double): Double{
        val base: Double = 1 + x
        return principal * (base.pow(numPay) / (-1.00 + base.pow(numPay)) -
                numPay * x * base.pow((-1.00 + 2.00 * numPay)) /
                (-1.00 + base.pow(numPay)).pow(2.00) +
                numPay * x * base.pow((-1.00 + numPay)) /
                (-1.00 + base.pow(numPay)))
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

    val interestRate = (approx * repayFreq * 10000)/100  //this way we get APRs like 7.5% or 6.55%
    println("Interest Rate is ${"%.2f".format(interestRate)}%")
    print(approx)  //used (approx) for the interest rate for the amortisaiton schedule

}
