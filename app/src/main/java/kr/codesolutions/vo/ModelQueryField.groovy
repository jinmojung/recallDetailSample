package kr.codesolutions.vo

import groovy.transform.CompileStatic;

import java.io.Serializable;

@CompileStatic
public class ModelQueryField implements Serializable {
    public String field;
    public boolean checked;
}
