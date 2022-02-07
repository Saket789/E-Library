package com.library.kindle.mapper.impl

import com.library.kindle.mapper.BookMapper
import com.library.kindle.mappers.AuthorMapper
import com.library.kindle.data.Book
import com.library.kindle.data.BookDTO
import com.library.kindle.repository.AuthorRepository
import com.library.kindle.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookMapperImpl(
    val authorMapper: AuthorMapper,
) : BookMapper {

    @Autowired
    lateinit var repository: AuthorRepository

    @Autowired
    lateinit var service: AuthorService

    override fun toDTO(book: Book): BookDTO {
        if (book == null)
            return book

        val bookDto = BookDTO()
        bookDto.apply {
            isbn = book.isbn
            title = book.title
            pages = book.pages
            content = book.content
            author = authorMapper.toDTO(book.author)
            created = book.created
            category = book.category
        }
        return bookDto
    }


    override fun toEntity(bookDto: BookDTO,cat: ArrayList<String>): Book {
        val book = Book()
        book.apply {
            isbn = bookDto.isbn
            title = bookDto.title
            pages = bookDto.pages
            content = book.content
            val exist: Boolean = repository.existsById(bookDto.author.id)

            if (!exist)
            {
                var temp: String = service.insertAuthor(bookDto.author)
            }
            author = bookDto.author.let { authorMapper.toEntity(it)}!!
            created = bookDto.created
            var temp: ArrayList<String> = arrayListOf()
            for (s:String in cat) {
                println(s)
                temp.add(s)
            }
            category = cat
        }
        return book
    }

}
