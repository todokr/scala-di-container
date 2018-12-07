object MyApp extends App {

  val types = collection.mutable.Map.empty[String, Class[_]]
  def register[T](name: String, tpe: Class[T]): Unit = types += (name -> tpe)

  val instances = collection.mutable.Map.empty[String, AnyRef]
  def getInstance(name: String): AnyRef = {
    instances.getOrElseUpdate(name, types(name).newInstance().asInstanceOf[AnyRef])
  }

  class Foo() {
    override def toString: String = "this is Foo"
  }
  class Baa() {
    override def toString: String = "this is Baa"
  }

  register("foo", classOf[Foo])
  register("baa", classOf[Baa])

  println("foo: " + getInstance("foo").toString)
  println("baa: " + getInstance("baa").toString)
}
