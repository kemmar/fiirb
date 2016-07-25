package application

import com.twitter.finagle.Service
import com.twitter.finagle.http.Status.{BadRequest, InternalServerError, ServiceUnavailable, UnprocessableEntity}
import com.twitter.finagle.http._
import domain.system.error.{ProcessingException, ServiceException}
import io.circe.generic.auto._
import io.finch.{Endpoint, Error, Output}
import io.finch.Output._
import io.finch.circe._

trait RouteConfig {

  def routes(route: Endpoint): Service[Request, Response] = route.handle({
    case e: Error =>
      createFailure(failure(new ProcessingException("error.bad.request", e.getMessage()), BadRequest))

    case e: ProcessingException =>
      createFailure(failure(e, UnprocessableEntity))

    case e: ServiceException =>
      createFailure(failure(e, ServiceUnavailable))

    case e: Exception =>
      println(e)
      createFailure(failure(new ProcessingException("unknown.error", "an unknown error has accord"), InternalServerError))
  }).toService

  def createFailure[A](e: Output[A]) = {
    e
      .withContentType(Some("application/json"))
  }


  def buildRoutes: Service[Request, Response]
}
