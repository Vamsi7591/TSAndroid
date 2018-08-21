package com.android.timesheet.shared.interactors;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.services.BaseService;
import com.android.timesheet.shared.store_models.BaseStore;
import com.android.timesheet.shared.store_models.UserStore;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public abstract class BaseInteractor<T extends BaseService> {

    @NonNull
    private final Context mContext;

    private CompositeSubscription mSubscription;

    private T mService;

    private HashMap<String, BaseStore> mStores = new HashMap<>();

    private HashMap<String, BaseService> mExternalService = new HashMap<>();

    public BaseInteractor(Context context) {
        this.mContext = context;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    protected abstract BaseService provideService();

    protected T service() {
        if (mService == null) {
            mService = (T) provideService();
        }

        return mService;
    }

    protected <V extends BaseService> V callService(Class<V> service) {
        V obj = (V) mExternalService.get(service.getName());

        if (obj == null) {
            try {
                Constructor<V> c = (Constructor<V>) Class.forName(service.getName()).getDeclaredConstructor();
                c.setAccessible(true);
                obj = c.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!mExternalService.containsKey(service.getName())) {
            mExternalService.put(service.getName(), obj);
        }

        return obj;
    }

    protected <U extends BaseStore> U store(Class<U> store) {
        U obj = (U) mStores.get(store.getName());

        if (obj == null) {
            try {
                Constructor<U> c = (Constructor<U>) Class.forName(store.getName()).getDeclaredConstructor(
                        Context.class);
                c.setAccessible(true);
                obj = c.newInstance(new Object[]{mContext});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!mStores.containsKey(store.getName())) {
            mStores.put(store.getName(), obj);
        }

        return obj;
    }

    protected CompositeSubscription subscriptions() {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }

        return mSubscription;
    }


    public User currentUser() {
        return store(UserStore.class).getCurrentUser();
    }

    public void clearUser() {
         store(UserStore.class).clearUser();
    }

    public void unsubscribe() {
        subscriptions().unsubscribe();
    }
}
