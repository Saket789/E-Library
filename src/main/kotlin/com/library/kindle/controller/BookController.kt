package com.library.kindle.controller

import com.library.kindle.data.BookDTO
import com.library.kindle.service.BookService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    private lateinit var service: BookService


    @GetMapping(
        value=["/"]
    )
    fun getBook(): List<BookDTO>{
        return service.getBook()
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        path = ["/insert"],
        consumes = ["multipart/form-data"]
    )
    @ResponseBody
    fun insertBook(
        @RequestParam(name = "Author Name") name: String,
        @RequestParam(name = "id") id: Int,
        @RequestParam(name = "No of Pages") pages: Int,
        @RequestParam(name = "Book Name") title: String,
        @RequestParam(name = "Genre") category: ArrayList<String>,
        @RequestParam(name = "Pdf") content: MultipartFile
    ) = service.insertBook(name, title, id, pages, category, content.bytes)


    @DeleteMapping(
        value = ["/{id}"]
    )
    fun deleteNote(
        @PathVariable(name = "id") id: String
    ): String = service.deleteBook(id)


    @GetMapping(
        value = ["/search/{title}"]
    )
    fun searchBook(
        @PathVariable(name = "title") title: String
    ) = service.findByTitle(title)


    @GetMapping(
        value = ["/searchByCategory"]
    )
    fun searchByCategory(
        @RequestParam("genre")genre: ArrayList<String>
    ) = service.findByCategory(genre)


}