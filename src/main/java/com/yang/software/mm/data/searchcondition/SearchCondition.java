package com.yang.software.mm.data.searchcondition;

import com.yang.software.mm.data.Constants;

public class SearchCondition {
    private String searchContext = "";
    private String searchSection = "";
    private String searchPublishTime = "";

    public String getSearchContext() {
        return searchContext;
    }

    public void setSearchContext(String searchContext) {
        this.searchContext = searchContext;
    }

    public String getSearchSection() {
        return searchSection;
    }

    public void setSearchSection(String searchSection) {
        this.searchSection = searchSection;
    }

    public String getSearchPublishTime() {
        return searchPublishTime;
    }

    public void setSearchPublishTime(String searchPublishTime) {
        this.searchPublishTime = searchPublishTime;
    }

    @Override
    public String toString() {
        return "SearchCondition [searchContext=" + searchContext
                + ", searchSection=" + searchSection + ", searchPublishTime="
                + searchPublishTime + "]";
    }

    public String getDisplayString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.searchContext);
        builder.append(Constants.SEARCH_CONDITION_SPLIT);
        builder.append(this.searchPublishTime);
        builder.append(Constants.SEARCH_CONDITION_SPLIT);
        builder.append(this.searchSection);
        return builder.toString();
    }


}
