package com.library.kindle.service

import com.library.kindle.data.AuthorDTO
import com.library.kindle.data.Book
import com.library.kindle.data.BookDTO
import com.library.kindle.mapper.BookMapper
import com.library.kindle.mappers.AuthorMapper
import com.library.kindle.repository.AuthorRepository
import com.library.kindle.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.NamedQuery
import kotlin.collections.ArrayList

@Service("Book service")
class BookService() {

    @Autowired
    lateinit var repository: BookRepository

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookMapper: BookMapper

    @Autowired
    lateinit var authorMapper: AuthorMapper

    @Autowired
    lateinit var authorService: AuthorService


    fun getBook(): List<BookDTO> {
        val bookDtolist: MutableList<BookDTO> = mutableListOf()
        val bookList = repository.findAll()
        for(book: Book in bookList){
            bookDtolist.add(bookMapper.toDTO(book))
        }
        return bookDtolist
    }


    fun insertBook(name: String, t: String, id: Int, page: Int, cat: ArrayList<String>, con: ByteArray): ResponseEntity<Map<String, Any>> {
        val book = Book()
        book.apply {
            isbn = UUID.randomUUID().toString()
            title = t
            pages = page.toLong()
            val exist: Boolean = authorRepository.existsById(id)
            val authordto: AuthorDTO = AuthorDTO()
            authordto.id = id
            authordto.author_name = name
            if (!exist)
            {
                var temp: ResponseEntity<Map<String,Any>> = authorService.insertAuthor(authordto)
            }
            author = authorMapper.toEntity(authordto)
            created = created
            category = cat
            content = con
        }
        repository.save(book)
        var response: MutableMap<String,Any> = mutableMapOf()
        response["message"] = "Book Inserted Successfully"
        return ResponseEntity(response, HttpStatus.OK)
    }


    fun deleteBook(id: String): String {
//        var s2 = repository.findById(id)
//        println(s2.)
////        var temp = repository.findAllByAuthor(s2.)
        repository.deleteById(id)
        return "Book deleted successfully"
    }


    fun findByTitle(title: String): Iterable<BookDTO>{
        val bookDtolist: MutableList<BookDTO> = mutableListOf()
        val bookList = repository.findByTitle(title)
        for(book: Book in bookList){
            bookDtolist.add(bookMapper.toDTO(book))
        }

        return bookDtolist
    }

//    @Query(
////        name = "Book.findCategory",
//        "SELECT b FROM Book b WHERE :str IN (b.category)"
//    )
//    fun findCategory(string: String) : List<Book> {
//
//    }

    fun findByCategory(genre: ArrayList<String>): Iterable<BookDTO>{
        println(genre)
//        for(string: String in genre)
//            println(string)
        val bookList: MutableList<BookDTO> = mutableListOf()

        var temp: List<Book> = repository.findAll()

        for(book: Book in temp) {
            for(str: String in genre) {

                if(str in book.category) {
                    bookList.add(bookMapper.toDTO(book))
                    break
                }
            }
        }

//        var temp1: List<Book> = repository.findAll().filter {
//            it.category.contains()
//        }

        for(str: String in genre){

        }


//        for(book: Book in temp){
//            bookList.add(bookMapper.toDTO(book))
//            println(book.title)
//        }
        return bookList
    }

}


//println(genre)
//var temp = repository.findByCategory(genre)
//val bookList: MutableList<BookDTO> = mutableListOf()
//for(book: Book in temp){
//    bookList.add(bookMapper.toDTO(book))
//    println(book.title)
//}
//return bookList