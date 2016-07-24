package com.lhs.scala.Akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by abel on 16-7-21.
  */


class Greeter extends Actor {

  override def receive: Receive = {
    case "greet" =>
      println("hello world")
      val hello = context.actorOf(Props[HelloWorld], "hello")
      hello ! "done"
  }
}

class HelloWorld extends Actor {

  override def receive: Receive = {
    case "done" =>
      println("done")
      context.system.shutdown()
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("hello")
    val greeter = system.actorOf(Props[Greeter], "greeter")
    greeter ! "greet"
  }
}