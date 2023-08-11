package triangle

import java.awt.Color
import java.awt.image.BufferedImage

case class Point(x: Int, y: Int)

class StartPoint(override val x: Int, override val y: Int, n: List[Int]) extends Point(x, y) {
  val nums: List[Int] = n
}

object Main extends App {
  println("Starting...")

  val canvas = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB)
  val g      = canvas.getGraphics

  val random = scala.util.Random

  println("Drawing points...")
  val (a, b, c, first) = getFirstPoint
  g.setColor(Color.magenta)
  setRandPoint(first, 100000L)

  println("Save file...")
  javax.imageio.ImageIO.write(canvas, "png", new java.io.File("drawing.png"))
  println("Completed")

  def getFirstPoint: (StartPoint, StartPoint, StartPoint, Point) = {
    g.setColor(Color.red)
    val a = new StartPoint(random.nextInt(950), random.nextInt(950), List(1, 2, 3, 4, 5))
    paintPoint(a)
    val b = new StartPoint(random.nextInt(950), random.nextInt(950), List(6, 7, 8, 9, 10))
    paintPoint(b)
    val c = new StartPoint(random.nextInt(950), random.nextInt(950), List(11, 12, 13, 14, 15))
    paintPoint(c)
    g.setColor(Color.blue)
    val first = Point(random.nextInt(950), random.nextInt(950))
    paintPoint(first)
    (a, b, c, first)
  }

  def paintPoint(point: Point, x1: Int = 5, y1: Int = 5): Unit = {
    g.fillRect(point.x, point.y, x1, y1)
  }

  @scala.annotation.tailrec
  def setRandPoint(point: Point, i: Long): Unit = {
    if (i > 0) {
      val num = random.nextInt(15) + 1
      val p: Point =
        if (a.nums.contains(num))
          calcPoint(a, point)
        else if (b.nums.contains(num))
          calcPoint(b, point)
        else if (c.nums.contains(num))
          calcPoint(c, point)
        else Point(0, 0)

      paintPoint(p)
      setRandPoint(p, i - 1)
    }
  }

  def calcPoint(p1: Point, p2: Point): Point = {
    val x = if (p1.x > p2.x) ((p1.x - p2.x) / 2) + p2.x else ((p2.x - p1.x) / 2) + p1.x
    val y = if (p1.y > p2.y) ((p1.y - p2.y) / 2) + p2.y else ((p2.y - p1.y) / 2) + p1.y
    Point(x, y)
  }
}
