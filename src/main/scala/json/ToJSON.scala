  package json

trait ToJSON {
def toJSON(level: Int = 0): String
val INDENTATION = " "
def indentation(level: Int = 0): (String,String) =
(INDENTATION * level, INDENTATION * (level+1))
}