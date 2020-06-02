package com.synectiks.library.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.synectiks.library.business.service.BookService;
import com.synectiks.library.business.service.CmsLibraryService;
import com.synectiks.library.business.service.CommonService;
import com.synectiks.library.config.ApplicationProperties;
import com.synectiks.library.domain.Batch;
import com.synectiks.library.domain.Department;
import com.synectiks.library.domain.Student;
import com.synectiks.library.domain.vo.CmsBookVo;
import com.synectiks.library.domain.vo.CmsLibraryVo;
import com.synectiks.library.domain.vo.LibraryFilterDataCache;
import com.synectiks.library.filter.Book.BookfilterProcessor;
import com.synectiks.library.filter.library.LibraryFilterProcessor;
import com.synectiks.library.repository.BookRepository;
import com.synectiks.library.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final static Logger logger = LoggerFactory.getLogger(Query.class);

    private final BookRepository bookRepository;

    @Autowired
    private BookfilterProcessor bookfilterProcessor;

    @Autowired
    private BookService bookService;

    private final LibraryRepository libraryRepository;

    @Autowired
    private LibraryFilterProcessor libraryFilterProcessor;

    @Autowired
    private CmsLibraryService cmsLibraryService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public Query(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    public List<CmsBookVo> getBookList() throws Exception {
        logger.debug("Query - getBookList :");
        return this.bookService.getBookList();
    }

    public List<CmsLibraryVo> getLibraryList() throws Exception {
        logger.debug("Query - getLibraryList :");
        return this.cmsLibraryService.getLibraryList();
    }

    public List<CmsBookVo>searchBook(Long bookId, Long studentId,Long departmentId,Long batchId) throws Exception{
        return Lists.newArrayList(bookfilterProcessor.searchBook(bookId,studentId,departmentId,batchId));
    }

    public List<CmsLibraryVo>searchLib(String bookTitle, Long departmentId, Long libraryId ) throws Exception {
        return  Lists.newArrayList(libraryFilterProcessor.searchLib(bookTitle, departmentId,libraryId));
    }

    public LibraryFilterDataCache createLibraryDataCache() throws Exception{
        String preUrl = this.applicationProperties.getPrefSrvUrl();
        String stUrl = this.applicationProperties.getStdSrvUrl();
        String baurl = preUrl+"/api/batch-by-filters/";
        Batch[] batchList = this.commonService.getObject(baurl,Batch[].class);
        String deurl = preUrl+"/api/department-by-filters/";
        Department[] departmentList = this.commonService.getObject(deurl,Department[].class);
        List<CmsLibraryVo> libraryList = this.cmsLibraryService.getLibraryList();
        String stuurl = stUrl+"/api/student-by-filters/";
        Student[] studentList = this.commonService.getObject(stuurl,Student[].class);
        List<CmsBookVo> bookList = this.bookService.getBookList();
        LibraryFilterDataCache cache = new LibraryFilterDataCache();
        cache.setBatches(Arrays.asList(batchList));
        cache.setDepartments(Arrays.asList(departmentList));
        cache.setBooks(bookList);
        cache.setLibraries(libraryList);
        cache.setStudents(Arrays.asList(studentList));
        return cache;
    }

}
