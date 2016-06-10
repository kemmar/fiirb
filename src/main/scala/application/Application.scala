package application

import com.twitter.finagle.Http
import com.twitter.util.Await

object Application extends App {
  Await.ready(Http.server.serve(":8081", RouteConfig.buildRoutes))
}
