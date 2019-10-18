package triangle

import akka.actor.{Actor, Props}
import java.awt.image.BufferedImage
import triangle.SaveActor.Save

object SaveActor {
  def props(): Props = Props(new SaveActor)

  case class Save(canvas: BufferedImage)
}

class SaveActor extends Actor {
  override def preStart(): Unit = println("Save file...")

  override def postStop(): Unit = println("Completed")

  override def receive: Receive = {
    case Save(canvas) =>
      javax.imageio.ImageIO.write(canvas, "png", new java.io.File("drawing.png"))
  }
}
