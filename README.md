[![Build Status](https://travis-ci.com/mtumilowicz/scala-cats-implicit-workshop.svg?branch=master)](https://travis-ci.com/mtumilowicz/scala-cats-implicit-workshop)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# scala-cats-implicit-workshop
* references
    * [Programming Scala, 2nd Edition - Dean Wampler](https://www.oreilly.com/library/view/programming-scala-2nd/9781491950135/)
    * [Scala with Cats Book - Noel Welsh](https://underscore.io/books/scala-with-cats/)
    * https://blog.knoldus.com/sorting-in-scala-using-sortedsortby-and-sortwith-function/
    * https://stackoverflow.com/questions/36432376/implicit-class-vs-implicit-conversion-to-trait
    * https://typelevel.org/cats/
    
## preface
* goals of this workshop:
    * introduction to implicits
    * practicing basic use-cases of implicits
    * basics of cats  
* workshop: `workshop` package, answers: `answers` package

## introduction
* implicits are a powerful, if controversial feature in Scala
    * they are "nonlocal" in the source code
        * it’s not obvious when an implicit value or method  is being used, which can be 
          confusing to the reader
* some are imported automatically through `Predef`
* used to 
    * reduce boilerplate
    * simulate adding new methods to existing types
    * support the creation of domain-specific languages (DSLs)
    
## arguments
```
def method(arg1: Type1)(implicit context: Type2) = ...

def anotherMethod() {
    implicit val defaultContext: Type2 = ...
    val value = method(...) // implicit value in the local scope will be used as a context
}
```
* only the last arguments can be implicit
* user does not have to provide argument explicitly
    * when an implicit argument is omitted, a type-compatible `implicit` value will be used 
      from the enclosing scope, if available
        * otherwise, a compiler error occurs 
* compiler searches for candidate instances in the implicit scope at the call site:
    * local or inherited definitions
    * imported definitions
    * definitions in the companion object of the type class or the parameter type

## methods
```
case class Data(...)

object DataOps {
    implicit def op(implicit data: Data): Type2 = ... // implicit function takes only implicit arguments
}

class SomeClass {
    import DataOps.op // imports implicit method to the scope
    
    implicit val data = Data(...) // to be used as implicit value 
    
    def method(arg1: Type1)(implicit context: Type2) = ...
    
    def anotherMethod() {
        val value = method(...)
    }
}

```
* aren’t chained to get from the available type, through intermediate types, to the target type

## classes
```
implicit class RichInt(n: Int) extends Ordered[Int] {
   def min(m: Int): Int = if (n <= m) n else m
   ...
}
```
will desugar into:
```
class RichInt(n: Int) extends Ordered[Int] {
  def min(m: Int): Int = if (n <= m) n else m
  ...
}
implicit final def RichInt(n: Int): RichInt = new RichInt(n)
```
* ease the creation of classes which provide extension methods or conversions to another type
* example
    ```
    implicit final class ArrowAssoc[A](val self: A) {
        def -> [B](y: B): Tuple2[A, B] = Tuple2(self, y)
    }
  
    Map(1 -> 2)
    ```
    * when we call `"a" -> 1`compiler goes through the following logical steps:
        1. sees a call `->` method on `String`
        2. String has no `->` method
           * looks for an implicit conversion in scope to a type that has this method
        3. finds `ArrowAssoc`
        4. constructs an `ArrowAssoc` with `"a"`
        5. resolves the `-> 1` part of the expression

## implicitly
* `Predef` defines a method called implicitly
    * `def implicitly[A](implicit value: A): A = value`
* summons any value from implicit scope
    ```
    import JsonWriterInstances._
  
    val jw = implicitly[JsonWriter[String]]
    ```
* syntactic sugar fo defining implicit parameterized argument
    ```
    def sortBy[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
        list.sortBy(f)(ord)
    ```
    idiom is so common that Scala provides a shorthand syntax 
    ```
    def sortBy[B : Ordering](f: A => B): List[A] = // 'B : Ordering' is called a context bound
        list.sortBy(f)(implicitly[Ordering[B]]) // way of obtaining implicit parameter parameter
    ```

## case study
* common idioms
    * boilerplate elimination: providing context information implicitly rather than explicitly
        * example: `apply[T](body: => T)(implicit executor: ExecutionContext): Future[T]`
        * also: transactions, database connections, thread pools, and user sessions
    * implicit evidence
        * constrains the allowed types, but doesn’t require them to conform to a common supertype
        * example
            ```
            trait TraversableOnce[+A] ... {
                ...
                def toMap[T, U](implicit ev: <:<[A, (T, U)]): immutable.Map[T, U]
                ...
            }
            ```
            * `A <:< B` means `A` must be a subtype of `B`
                * `<:<(A, (T, U))` equivalent to `A <:< (T, U)`
            * we only used existence of implicit as confirmation that we operate on a sequence of pairs
                * no sense in calling `toMap` if the sequence is not a sequence of pairs
    * working around limitations due to type erasure
        ```
        object M { // compile time error - type erasure
            def m(seq: Seq[Int]): Unit = ...
            def m(seq: Seq[String]): Unit = ...
        }
        
        object M { // OK
            implicit object IntMarker
            implicit object StringMarker
        
            def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit = ...
        
            def m(seq: Seq[String])(implicit s: StringMarker.type): Unit = ...
        }
        ```

## cats
* provides abstractions for functional programming in the Scala
* name is a playful shortening of the word category
* goal: provide a foundation for an ecosystem of pure, typeful libraries to support functional programming 
  in Scala applications

## type classes
* is an interface or API that represents some functionality we want to implement
* in Cats a type class is represented by a trait with at least one type parameter
    ```
    trait JsonWriter[A] {
        def write(value: A): Json
    }
    ```
* instances of a type class provide implementations for the types
    * concrete implementations + implicit tag
    ```
    object JsonWriterInstances {
        implicit val stringWriter: JsonWriter[String] = (value: String) => JsString(value) // single abstract method
        implicit val personWriter: JsonWriter[Person] = // creating anonymous class explicitly
            new JsonWriter[Person] {
                def write(value: Person): Json =
                    JsObject(Map(
                        "name" -> JsString(value.name),
                        "email" -> JsString(value.email)
                    ))
        }
        // etc...
    }
    ```
    * two common ways of specifying an interface
        * interface objects
            ```
            object Json {
                def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
                    w.write(value)
            }
            ```
            and then
            ```
            import JsonWriterInstances._ // import any type class instances we care about
            
            Json.toJson(Person("Dave", "dave@example.com") // Json.toJson(Person("Dave", "dave@example.com"))(personWriter)
            ```
        * interface syntax
            ```
            object JsonSyntax {
                implicit class JsonWriterOps[A](value: A) { // extension methods to extend existing types with interface methods
                    def toJson(implicit w: JsonWriter[A]): Json =
                        w.write(value)
                }
            }
            ```
            and then
            ```
            import JsonWriterInstances._
            import JsonSyntax._
          
            Person("Dave", "dave@example.com").toJson // Person("Dave", "dave@example.com").toJson(personWriter)
            ```

## recursive resolution
* consider `JsonWriter[Option[A]]`
* we need instance for every `A`
    ```
    implicit val optionIntWriter: JsonWriter[Option[Int]] = ???
    implicit val optionPersonWriter: JsonWriter[Option[Person]] = ???
    // and so on...
    ```
    * it doesn't scale
* we can abstract the code for handling `Option[A]` into a common constructor based on the instance for `A`
    ```
    implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = 
        option: Option[A] => option match {
            case Some(aValue) => writer.write(aValue)
            case None => JsNull
        }
    ```
    then
    ```
    Json.toJson(Option("A string")) // Json.toJson(Option("A string"))(optionWriter(stringWriter))
    ```

## performance
* compile-time overhead: project is slow to build
* runtime overhead: due to the extra layers of indirection from the wrapper types
