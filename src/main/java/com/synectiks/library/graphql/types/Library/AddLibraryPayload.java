package com.synectiks.library.graphql.types.Library;

import com.synectiks.library.domain.vo.CmsLibraryVo;

public class AddLibraryPayload {
    private final CmsLibraryVo cmsLibraryVo;

    public AddLibraryPayload(CmsLibraryVo cmsLibraryVo){
        this.cmsLibraryVo = cmsLibraryVo;
    }

    public CmsLibraryVo getCmsLibraryVo() {
        return cmsLibraryVo;
    }

}
