package com.yang.software.mm.data;

import com.yang.software.mm.frame.Page;

public interface Constants {
    String SEARCH_CONDITION_SPLIT = "%-%";

    int NOT_INIT_NUMBER = -1;

    String NOT_INIT_NUMBER_STR = "-1";

    String EDIT_WAIT_PUBLISH_FACTORYID_FILTER = "factoryId=1&factoryId=2&factoryId=3";

    String ALL_FACTORYID_FILTER = "factoryId=1&factoryId=2&factoryId=3&factoryId=4";

    ThreadLocal<Page> page = new ThreadLocal<Page>();
}
