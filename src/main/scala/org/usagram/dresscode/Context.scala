package org.usagram.dresscode

sealed abstract class Context(val site: Site)

case class ShowContext(page: Page, variables: Map[String, Any] = Map.empty)(implicit site: Site) extends Context(site)

case class MakeContext()(implicit site: Site) extends Context(site)

trait Page
case class Index(posts: PostSeq) extends Page
case class Day(posts: PostSeq) extends Page
