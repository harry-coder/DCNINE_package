package com.dc9_master.data_holder;

/**
 * Created by nitinb on 06-02-2016.
 */
public class ScopeDTO {

    public String getDefaultSelect() {
       // defaultSelect="select";
        return defaultSelect;
    }

    public void setDefaultSelect(String defaultSelect) {
        this.defaultSelect = defaultSelect;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getScopeCode() {
        return scopeCode;
    }

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    String defaultSelect,scopeName,scopeCode,scopeId;

}
