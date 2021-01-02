# scala-cats-implicit-workshop
* references
    * [Programming Scala, 2nd Edition - Dean Wampler](https://www.oreilly.com/library/view/programming-scala-2nd/9781491950135/)
    
## preface

## introduction
* implicits are a powerful, if controversial feature in Scala
    * because they are "nonlocal" in the source code
        * when reading the source code, it’s not obvious when an implicit
          value or method is being used, which can be confusing to the reader
* some are imported automatically through Predef
* used to 
    * reduce boilerplate
    * simulate adding new methods to existing types
    * support the creation of domain-specific languages (DSLs)

## arguments
```
def method(arg1: Type1)(implicit context: Type2) = ...

implicit val defaultContext: Type2 = ...
val tax = method(...) // implicit value in the local scope will be used as a context
```
* label method arguments that the user does not have to provide explicitly
    * when an implicit argument is omitted, a type-compatible value will be used 
      from the enclosing scope, if available
    * otherwise, a compiler error occurs
    
## methods
```
case class Data(...)

object DataOps {
    implicit def method(implicit data: Data) = ...
}



```
* function as an implicit value, it must not take arguments itself, unless the arguments are also implicit