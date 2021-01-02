package json

case class Address(street: String, city: String)
case class Person(name: String, address: Address)