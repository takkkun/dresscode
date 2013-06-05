package org.usagram.dresscode

object Example extends View {
  def express(implicit context: Context) =
    <html>
      <head>
        <meta charset="utf-8" />
        <title>{refer("Title")}</title>
      </head>
      <body>
      {
        showFor {
          case Index(posts) => expressIndex(posts)
          case Day(posts)   => expressDay(posts)
        }
      }
      </body>
    </html>

  def expressIndex(posts: PostSeq)(implicit context: Context) =
    <ul id="index">{posts map expressPost}</ul>

  def expressDay(posts: PostSeq)(implicit context: Context) =
    <ul id="day">{posts map expressPost}</ul>

  def expressPost(post: Post)(implicit context: Context) = post match {
    case post: Text =>
      <li>
        <div class="post text">
          {post.postType}
          <a href={post.permalink}>Permalink</a>
        </div>
      </li>
    case post: Photo =>
      <li>
        <div class="post photo">
          <a href={post.permalink}>to photo</a>
        </div>
      </li>
    case post =>
      <li></li>
  }
}

object Main {
  def main(args: Array[String]) {
    implicit val site = Site("takkkun")

    println(Example express ShowContext(
      Index(
        PostSeq(
          Text(),
          Photo(),
          Quote()
        )
      ),
      variables = Map(
        "Title" -> "Example tumblelog"
      )
    ))

    println(Example express MakeContext())
  }
}
