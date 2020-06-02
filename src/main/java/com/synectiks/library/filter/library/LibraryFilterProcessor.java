package com.synectiks.library.filter.library;

import com.synectiks.library.business.service.CmsLibraryService;
import com.synectiks.library.domain.vo.CmsLibraryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryFilterProcessor {
    private final Logger logger = LoggerFactory.getLogger(LibraryFilterProcessor.class);

    @Autowired
    private CmsLibraryService cmsLibraryService;

    public List<CmsLibraryVo> searchLib(String bookTitle, Long departmentId, Long libraryId) throws Exception {
        return cmsLibraryService.searchLib(bookTitle, departmentId,libraryId);
    }

    public List<CmsLibraryVo> searchLib(LibraryFilterInput filter) throws Exception {
        return cmsLibraryService.searchLib(filter);
    }
}
