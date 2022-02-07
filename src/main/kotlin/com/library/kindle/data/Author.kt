package com.library.kindle.data

import javax.persistence.*

@Entity
@Table(name = "Author")
@NamedQuery(
    name = "Author.findAuthor",
    query = "SELECT n FROM Author n WHERE n.author_name LIKE ?1"
)

data class Author(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int= 0,

    @Column(name = "author_name")
    var author_name: String = "",

    @Column(name = "count")
    var bookCount: Int = 0,

    @OneToMany(mappedBy = "author")
    var books: List<Book> = listOf()

)