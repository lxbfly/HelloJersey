/**
 * StaffStore.java   2013-3-11 下午10:14:24 by gavin 
 *
 * Copyright (c) 2000 - 2013 Gavin Lee. All rights reserved.
 * 
 */
package com.sample.hello.store;

import java.util.HashMap;
import java.util.Map;
import com.sample.hello.bean.Contact;
import com.sample.hello.bean.Staff;
public class StaffStore {
    private static Map<String, Staff> store;
    private static StaffStore instance = null;
    
    private StaffStore() {
        store = new HashMap<String,Staff>();
        initStaff();
    }

    private void initStaff() {
        Staff staff = new Staff("hjt",78,"国家主席");
        store.put(staff.getName(), staff);
    }
    
    public static Map<String, Staff> getStore() {
        if (instance == null) {
            instance = new StaffStore();
        }
        return store;
    }
}
