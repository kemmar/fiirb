package application

import com.twitter.finagle.Service
import com.twitter.finagle.http._
import controller.HelloWorldController.helloWorldRoute
import io.circe.generic.auto._
import io.finch.circe._

object RouteConfig {
  def buildRoutes: Service[Request, Response] =
    helloWorldRoute.toService
}
