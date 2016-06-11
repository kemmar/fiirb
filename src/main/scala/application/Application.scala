package application

import com.twitter.finagle.Http
import com.twitter.util.Await

object Application extends App {
  val port = System.getenv("PORT")

  println(s"connecting to port: $port")
  Await.ready(Http.server.serve(s":$port", RouteConfig.buildRoutes))
}
