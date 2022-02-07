package com.library.kindle.data


import lombok.AllArgsConstructor
import lombok.Data
import javax.persistence.*

@Data
@AllArgsConstructor
data class AuthorDTO(
    var id: Int,
    var author_name: String,
    var bookCount: Int,
    var book: List<BookDTO> = listOf()
) {
    constructor() : this(0, "",0)
}
