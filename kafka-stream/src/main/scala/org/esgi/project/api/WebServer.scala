package org.esgi.project.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore
import org.esgi.project.api.models.{MeanLatencyForURLResponse, VisitCountResponse}

object WebServer extends PlayJsonSupport {
  def routes(streams: KafkaStreams): Route = {
    concat(
      path("trades" / Segment) { period: String =>
        get {
          complete(
            List(VisitCountResponse("", 0))
          )
        }
      },
      path("latency" / "beginning") {
        get {
          complete(
            List(MeanLatencyForURLResponse("", 0))
          )
        }
      }
    )
  }
}
