package com.synectiks.library.filter.Book;

import com.synectiks.library.business.service.BookService;
import com.synectiks.library.domain.vo.CmsBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookfilterProcessor  {
    @Autowired

    private BookService bookService;
    public List<CmsBookVo> searchBook(Long bookId, Long studentId, Long departmentId, Long batchId) {
        return bookService.searchBook(bookId,studentId,departmentId,batchId);
    }
    public List<CmsBookVo> searchBook(BookListFilterInput filter){
           return bookService.searchBook(filter);
        }
    }

