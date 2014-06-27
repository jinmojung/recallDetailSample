package kr.codesolutions.vo

import groovy.transform.CompileStatic;

import java.io.Serializable;
import java.util.List;

@CompileStatic
public class RequestModelQueryPageable {
    boolean enable;
    int pageNumber;
    int pageSize;
    List<RequestModelQueryPageableSortOrders> sortOrders;

}
