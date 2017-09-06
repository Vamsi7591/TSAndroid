package com.android.timesheet.shared.services;

import com.android.timesheet.shared.RestClient;
import com.android.timesheet.shared.exceptions.NetworkException;
import com.android.timesheet.shared.models.ServerErrorResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.exceptions.OnErrorNotImplementedException;


/**
 * Created by vamsikonanki on 6/12/16.
 */
public abstract class ServiceSubscriber<T> extends Subscriber<T> {

    public final void onCompleted() {
        //This implementation do nothing
    }

    public final void onNext(T o) {
        if (o instanceof Response) {
            Response response = (Response) o;
            if (response.isSuccessful()) {
                onSuccess(o);
            } else {
                try {
                    ServerErrorResponse serverError = RestClient
                            .getInstance()
                            .convert(response.errorBody(), ServerErrorResponse.class);

                    onFailure(new NetworkException(serverError.getError()));
                } catch (UnknownHostException ue) {
                    onFailure(ue);
                    onDisconnected();
                } catch (IOException e) {
                    onFailure(new NetworkException(response.code(), response.message()));
                }
            }
        } else {
            onSuccess(o);
        }
    }

    @Override
    public final void onError(Throwable e) {
        onFailure(e);

        if (e instanceof UnknownHostException) {
            onDisconnected();
        } else if (e instanceof SocketTimeoutException) {
            onDisconnected();
        } else if (e instanceof OnErrorNotImplementedException) {
            System.out.println("OnErrorNotImplementedException:" + e.getMessage());
        }
    }

    public abstract void onFailure(Throwable e);

    public void onDisconnected() {
    }

    public abstract void onSuccess(T data);
}
