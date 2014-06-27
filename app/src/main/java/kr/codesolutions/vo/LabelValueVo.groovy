package kr.codesolutions.vo

import groovy.transform.CompileStatic

@CompileStatic
public class LabelValueVo {
    String label;
    String value;

    @Override
    public String toString() {
        return label;
    }
}
