package kr.codesolutions.vo

import groovy.transform.CompileStatic;

@CompileStatic
public class BaseVo {
    long number;
    long size;
    long totalPages;
    long numberOfElements;
    long totalElements;
    boolean hasPreviousPage;
    boolean isFirstPage;
    boolean hasNextPage;
    boolean isLastPage;
    boolean hasContent;
    long beginPage;
    long endPage;
    long previousPage;
    long nextPage;
    long status;
    long pageNumber;
    long pageSize;
    boolean firstPage;
    boolean lastPage;
}
