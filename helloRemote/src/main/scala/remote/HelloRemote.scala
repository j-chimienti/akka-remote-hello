package remote

import akka.actor._

object HelloRemote extends App {

  val system = ActorSystem("remoteSystem")

  val ra = system.actorOf(RemoteActor.props, "remoteActor")

  ra ! "Remote actor is up"

  println(ra.path)

}

class RemoteActor extends Actor {

  def receive : Receive = {
    case msg: String =>
      println(s"RemoteActor received message '$msg'")
      sender() ! s"hello from $self"

  }

}

object RemoteActor {

  def props : Props = Props(new RemoteActor)
}