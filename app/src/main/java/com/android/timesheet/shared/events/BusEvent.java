package com.android.timesheet.shared.events;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vamsikonanki on 3/24/16.
 */
public abstract class BusEvent<T> {

    protected T value;

    protected List<T> dataList;

    protected boolean mListData = false;

    public BusEvent(T o) {
        this.value = o;
    }

    public BusEvent(List<T> l) {
        mListData = true;
        this.dataList = l;
    }

    public boolean isList() {
        return mListData;
    }

    public T getValue() {
        return value;
    }

    public List<T> getList() {
        if (dataList == null && value != null) {
            dataList = new ArrayList<T>();
            dataList.add(value);
        }

        return dataList;
    }
}
