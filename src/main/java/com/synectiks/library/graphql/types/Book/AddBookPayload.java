package com.synectiks.library.graphql.types.Book;

import com.synectiks.library.domain.vo.CmsBookVo;

public class AddBookPayload {
    private final CmsBookVo cmsBookVo;

    public AddBookPayload(CmsBookVo cmsBookVo){
        this.cmsBookVo = cmsBookVo;
    }

    public CmsBookVo getCmsBookVo() {
        return cmsBookVo;
    }

}
