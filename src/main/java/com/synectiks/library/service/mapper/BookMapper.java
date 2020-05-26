package com.synectiks.library.service.mapper;


import com.synectiks.library.domain.*;
import com.synectiks.library.service.dto.BookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring", uses = {LibraryMapper.class})
public interface BookMapper extends EntityMapper<BookDTO, Book> {

    @Mapping(source = "library.id", target = "libraryId")
    BookDTO toDto(Book book);

    @Mapping(source = "libraryId", target = "library")
    Book toEntity(BookDTO bookDTO);

    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
