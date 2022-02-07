package com.library.kindle.mappers.impl

import com.library.kindle.data.Author
import com.library.kindle.data.AuthorDTO
import com.library.kindle.mappers.AuthorMapper
import org.springframework.stereotype.Component

@Component
class AuthorMapperImpl : AuthorMapper {



    override fun toDTO(author: Author): AuthorDTO {
        if (author == null) {
            return AuthorDTO()
        }

        val authorDto = AuthorDTO()
        authorDto.apply {
            id = author.id
            author_name = author.author_name
            bookCount = author.bookCount
        }
        return authorDto
    }

    override fun toEntity(authorDto: AuthorDTO): Author {
        val author = Author()
        author.apply {
            id = authorDto.id
            author_name = authorDto.author_name
            bookCount = authorDto.bookCount+1
        }
        return author
    }
}
