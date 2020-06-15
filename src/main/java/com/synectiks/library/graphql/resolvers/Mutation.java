package com.synectiks.library.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.library.business.service.BookService;
import com.synectiks.library.business.service.CmsLibraryService;
import com.synectiks.library.business.service.CommonService;
import com.synectiks.library.config.ApplicationProperties;
import com.synectiks.library.constant.CmsConstants;
import com.synectiks.library.domain.Batch;
import com.synectiks.library.domain.Department;
import com.synectiks.library.domain.Student;
import com.synectiks.library.domain.vo.CmsBookVo;
import com.synectiks.library.domain.vo.CmsLibraryVo;
import com.synectiks.library.filter.Book.BookListFilterInput;
import com.synectiks.library.filter.Book.BookfilterProcessor;
import com.synectiks.library.filter.library.LibraryFilterInput;
import com.synectiks.library.filter.library.LibraryFilterProcessor;
import com.synectiks.library.graphql.types.Book.AddBookInput;
import com.synectiks.library.graphql.types.Book.AddBookPayload;
import com.synectiks.library.graphql.types.Library.AddLibraryInput;
import com.synectiks.library.graphql.types.Library.AddLibraryPayload;
import com.synectiks.library.repository.BookRepository;
import com.synectiks.library.repository.LibraryRepository;
import com.synectiks.library.service.util.CommonUtil;
import com.synectiks.library.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    @Autowired
    BookService bookService;

    @Autowired
    CmsLibraryService cmsLibraryService;

    @Autowired
    CommonService commonService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookfilterProcessor bookfilterProcessor;

    @Autowired
    private LibraryFilterProcessor libraryFilterProcessor;

    @Autowired
    private ApplicationProperties applicationProperties;

    public AddBookPayload addBook(AddBookInput cmsBookVo) {
        CmsBookVo vo = this.bookService.addBook(cmsBookVo);
        return new AddBookPayload(vo);
    }

    public List<CmsBookVo> getBookList(BookListFilterInput filter) throws Exception {
        List<CmsBookVo> list = this.bookfilterProcessor.searchBook(filter);
        List<CmsBookVo> ls = new ArrayList<>();
        String prefUrl = applicationProperties.getPrefSrvUrl();
        String stUrl = applicationProperties.getStdSrvUrl();
        for(CmsBookVo book: list) {
            CmsBookVo vo = CommonUtil.createCopyProperties(book, CmsBookVo.class);
            vo.setStrIssueDate(DateFormatUtil.changeLocalDateFormat(book.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setStrDueDate(DateFormatUtil.changeLocalDateFormat(book.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setStrReceivedDate(DateFormatUtil.changeLocalDateFormat(book.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            String url = prefUrl+"/api/department-by-id/"+vo.getDepartmentId();
            Department d = this.commonService.getObject(url, Department.class);
            url = prefUrl+"/api/batch-by-id/"+vo.getBatchId();
            Batch b = this.commonService.getObject(url,Batch.class);
            url = stUrl+"/api/student-by-id/"+vo.getStudentId();
            Student s = this.commonService.getObject(url,Student.class);
            vo.setBatch(b);
            vo.setDepartment(d);
            vo.setStudent(s);
            vo.setIssueDate(null);
            vo.setDueDate(null);
            vo.setReceivedDate(null);
            ls.add(vo);
        }
        logger.debug("Total books retrieved. "+list.size());
        return ls;
    }

    public AddLibraryPayload addLibrary(AddLibraryInput cmsLibraryVo) {
        CmsLibraryVo vo = this.cmsLibraryService.addLibrary(cmsLibraryVo);
        return new AddLibraryPayload(vo);
    }

    public List<CmsLibraryVo> getLibraryList(LibraryFilterInput filter) throws Exception {
        List<CmsLibraryVo> list = this.libraryFilterProcessor.searchLib(filter);
        List<CmsLibraryVo> ls = new ArrayList<>();
        String prefUrl = applicationProperties.getPrefSrvUrl();
        for(CmsLibraryVo lb: list) {
            CmsLibraryVo vo = CommonUtil.createCopyProperties(lb, CmsLibraryVo.class);
            String url = prefUrl+"/api/department-by-id/"+vo.getDepartmentId();
            Department d = this.commonService.getObject(url, Department.class);
            vo.setDepartment(d);
            ls.add(vo);
        }
        logger.debug("Total libraries retrieved. "+list.size());
        return ls;
    }


}
