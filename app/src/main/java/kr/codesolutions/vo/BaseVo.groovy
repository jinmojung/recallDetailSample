package kr.codesolutions.vo

import groovy.transform.CompileStatic;

@CompileStatic
public class BaseVo implements Serializable {
    private long number;
    private long size;
    private long totalPages;
    private long numberOfElements;
    private long totalElements;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean hasNextPage;
    private boolean isLastPage;
    private boolean hasContent;
    private long beginPage;
    private long endPage;
    private long previousPage;
    private long nextPage;
    private long status;
    private long pageNumber;
    private long pageSize;
    private boolean firstPage;
    private boolean lastPage;
}
