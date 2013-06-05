package org.usagram.dresscode

case class Site(username: String, customDomain: Option[String] = None) {
  val url = "http://" + customDomain.getOrElse(username + ".tumblr.com")

  def to(path: String) = (url.endsWith("/"), path.startsWith("/")) match {
    case (true, false) | (false, true) => url + path
    case (true, true)                  => url + path.tail
    case (false, false)                => url + "/" + path
  }
}
