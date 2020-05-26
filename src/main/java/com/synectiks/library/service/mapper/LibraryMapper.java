package com.synectiks.library.service.mapper;


import com.synectiks.library.domain.*;
import com.synectiks.library.service.dto.LibraryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Library} and its DTO {@link LibraryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LibraryMapper extends EntityMapper<LibraryDTO, Library> {



    default Library fromId(Long id) {
        if (id == null) {
            return null;
        }
        Library library = new Library();
        library.setId(id);
        return library;
    }
}
