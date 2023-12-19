package vdac.prethi.app.models;

import java.util.ArrayList;

public class Countries {
    public String code;
    public ArrayList<String> currencyCodes;
    public String name;
    public String wikiDataId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public void setCurrencyCodes(ArrayList<String> currencyCodes) {
        this.currencyCodes = currencyCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

    public Countries() {
    }
}
