# scala-cats-implicit-workshop
* references
    * [Programming Scala, 2nd Edition - Dean Wampler](https://www.oreilly.com/library/view/programming-scala-2nd/9781491950135/)
    * [Scala with Cats Book - Noel Welsh](https://underscore.io/books/scala-with-cats/)
    * https://blog.knoldus.com/sorting-in-scala-using-sortedsortby-and-sortwith-function/
    
## preface

## introduction
* implicits are a powerful, if controversial feature in Scala
    * they are "nonlocal" in the source code - it’s not obvious when an implicit value or method 
      is being used, which can be confusing to the reader
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
* user does not have to provide argument explicitly
    * when an implicit argument is omitted, a type-compatible `implicit` value will be used 
      from the enclosing scope, if available
    * otherwise, a compiler error occurs 
    * only the last argument can be implicit
    
## methods
```
case class Data(...)

object DataOps {
    implicit def op(implicit data: Data): Type2 = ...
}

class SomeClass {
    import DataOps.op
    implicit val data = Data(...)
    
    def method(arg1: Type1)(implicit context: Type2) = ...
    
    def anotherMethod() {
        val value = method(...)
    }
}

```
* implicit function takes only implicit arguments

## classes

## implicitly
* `Predef` defines a method called implicitly
* syntactic sugar fo defining implicit parameterized argument
    ```
    // idiom is so common that Scala provides a shorthand syntax
    def sortBy[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
        list.sortBy(f)(ord)
    ```
    is equivalent to      
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
            * `<:<(A, (T, U))` equivalent to `A <:< (T, U)`
            * `A <:< B` means A must be a subtype of B
            * we only used existence of implicit as confirmation that we operate on a sequence of pairs
                * no sense in calling `toMap` if the sequence is not a sequence of pairs
        * example: limit the allowed types that can be used with certain methods with parameterized types
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
    * constraints reducing bugs
        * example: limit the allowed types that can be used with certain methods with parameterized types

## conversions
```
implicit final class ArrowAssoc[A](val self: A) {
    def -> [B](y: B): Tuple2[A, B] = Tuple2(self, y)
}
```
1. No conversion will be attempted if the object and method combination type check
   successfully.
2. Only classes and methods with the implicit keyword are considered.
3. Only implicit classes and methods in the current scope are considered, as well as
   implicit methods defined in the companion object of the target type (see the fol‐
   lowing discussion).
4. Implicit methods aren’t chained to get from the available type, through intermediate
   types, to the target type. Only a method that takes a single available type instance
   and returns a target type instance will be considered.
5. No conversion is attempted if more than one possible conversion method could be
   applied. There must be one and only one, unambiguous possibility.

* Because ArrowAssoc is
  declared implicit, the compiler goes through the following logical steps:
1. It sees that we’re trying to call a -> method on String (e.g., "one" -> 1 ).
2. Because String has no -> method, it looks for an implicit conversion in scope to a
   type that has this method.
3. It finds ArrowAssoc .
4. It constructs an ArrowAssoc , passing it the "one" string.
5. It then resolves the -> 1 part of the expression and confirms the whole expression’s
   type matches the expectation of Map.apply , which is a pair instance.
   
## type classes
* Note that Java’s default toString is of little value, because it just shows the type
  name and its address in the JVM’s heap
  
## performance
* a project that uses implicits heavily is a project that is slow to build
* Implicit conversions also incur extra runtime overhead, due to the extra layers of indi‐
  rection from the wrapper types
  
## Built-in Implicits
* Most of them are methods, and most of those are used to convert from one
  type to another