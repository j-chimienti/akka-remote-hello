package local

import akka.actor._
import local.LocalActor.Start

object HelloLocal extends App {

  val system = ActorSystem("localSystem")

  val la = system.actorOf(LocalActor.props, "localActor")

  la ! Start
}
::
class LocalActor extends Actor with ActorLogging {

  import LocalActor._

  val remote: ActorSelection = context.actorSelection("akka.tcp://remoteSystem@127.0.0.1:5555/user/remoteActor")

  def receive : Receive = receive(0)

  def receive(count: Int) : Receive = {

    case Start =>
      remote ! "hello from remote"

    case msg: String =>
          log.info(s"message received: $msg")

      count match {
        case under5 if under5 < 5 =>
          log.info(s"SEND a hello message b/c count = $under5")
          sender() ! "hello from local"
        case _ =>
      }
      context.become(receive(count + 1))

  }
}


object LocalActor {

  def props : Props = Props(new LocalActor)

  case object Start
}