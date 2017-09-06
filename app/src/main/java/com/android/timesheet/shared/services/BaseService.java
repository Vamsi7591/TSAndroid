package com.android.timesheet.shared.services;

import com.android.timesheet.shared.RestClient;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public abstract class BaseService<T> {

    protected T service;

    protected T prepare(Class<T> cls) {
        if (service == null) {
            service = RestClient.createService(cls);
        }
        return service;
    }

    protected abstract T prepare();

    protected Observable observe(Observable observable) {
        return observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    protected Observable observe(Scheduler scheduler, Observable observable) {
        return observable.subscribeOn(scheduler).observeOn(scheduler);
    }
}
