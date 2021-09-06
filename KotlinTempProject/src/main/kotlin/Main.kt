class Main {

}

//entry point. args: Array<String>은 생략가
fun main(args: Array<String>) {

    //standart output
    println("Hello world!")
    print(sum(1, 2))
    voidPrint(1, 2)

}

//>>>>>>>>>>>>>>Function
fun sum(a: Int, b: Int): Int{
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

//void function : Unit can be omitted.
fun voidPrint(a: Int, b: Int): Unit{
    print("sum of $a and $b is ${a+b}")
}

//>>>>>>>>>>>>>>>variable

fun variable(){
    //read-only local variable
    val pi = 3.14
    //pi = 3.141592 : 불가
    //pi += 1 : 불가

//declare variable
    var num1: Int = 1
    num1 = 3
    num1 += 1
    var num2 = 1 //컴파일러가 타입 추론 가
    //var num3: Int : 타입 선언 후 지연할당
    //num3 = 3
}


//>>>>>>>>>>>>>>>>>>Creating classes and instances

//바디가 비었다면 {} 생략가능
class Shape
// inner () : constructor
class Recrangler(h: Double, len: Double){
    //
    init {
        print("height : $h, lenght : $len")
    }
    var perimeter = (h + len) / 2
}

//>>>>>>>>>>>>>>>>>>comment
//this is comment
/*
    this is comment
 */

//>>>>>>>>>>>>>>>>>>String Template
var a = 4
var stringTemplate1 = "a is $a"
var stringTemplate2 = "a^3 is ${a * a * a}"


//>>>>>>>>>>>>>>>>>>condition
fun maxof (a: Int, b: Int): Int{
    if (a > b) {
        return a
    }
    else {
        return b
    }
}

fun maxof2 (a: Int, b: Int) = if (a > b) a else b

//>>>>>>>>>>>>>>>>>>Loop

//for loop
val items = listOf("apple, banana, mango")
fun loops(){
    for (item in items){
        print("item at $item")
    }

}




class Recrangler2{
    var h: Double
    var len: Double

    constructor(h: Double, len: Double) {
        print("height : $h, lenght : $len")
        this.h = h
        this.len = len
    }
    var perimeter = (h + len) / 2
}


