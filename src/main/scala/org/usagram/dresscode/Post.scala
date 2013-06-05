package org.usagram.dresscode

import scala.collection.SeqLike
import scala.xml.NodeSeq

sealed abstract class Post(site: Site) extends View {
  private val className = "\\w+$".r.findFirstIn(getClass.getName) getOrElse ""

  def postType(implicit context: Context) =
    showBy(className.toLowerCase) referBy "PostType"

  def permalink(implicit context: Context) =
    showBy(site to s"/post/${this.hashCode}") referBy "Permalink"
}

case class Text()(implicit site: Site) extends Post(site)

case class Photo()(implicit site: Site) extends Post(site)

case class Panorama()(implicit site: Site) extends Post(site)

case class Photoset()(implicit site: Site) extends Post(site)

case class Quote()(implicit site: Site) extends Post(site)

case class Link()(implicit site: Site) extends Post(site)

case class Chat()(implicit site: Site) extends Post(site)

case class Audio()(implicit site: Site) extends Post(site)

case class Video()(implicit site: Site) extends Post(site)

case class Answer()(implicit site: Site) extends Post(site)

case class PostSeq(posts: Post*) extends View {
  def map(express: Post => NodeSeq)(implicit context: Context) =
    showBy { posts.flatMap(express) } as {
      block("Posts") {
        posts flatMap {
          case post: Text     => block("Text")     { express(post) }
          case post: Photo    => block("Photo")    { express(post) }
          case post: Panorama => block("Panorama") { express(post) }
          case post: Photoset => block("Photoset") { express(post) }
          case post: Quote    => block("Quote")    { express(post) }
          case post: Link     => block("Link")     { express(post) }
          case post: Chat     => block("Chat")     { express(post) }
          case post: Audio    => block("Audio")    { express(post) }
          case post: Video    => block("Video")    { express(post) }
          case post: Answer   => block("Answer")   { express(post) }
        }
      }
    }
}
