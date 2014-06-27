package kr.codesolutions.vo

import groovy.transform.CompileStatic

@CompileStatic
public class MultiSelectVo {
    String label;
    String value;
    boolean selected;

    @Override
    public String toString() {
        return label;
    }
}
