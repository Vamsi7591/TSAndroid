package com.android.timesheet.auth;

import android.content.Context;
import android.text.Editable;
import android.util.Log;

import com.android.timesheet.shared.models.AuthUser;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.UserResponse;
import com.android.timesheet.shared.models.ValidationError;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;

import java.util.HashMap;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class AuthPresenter extends BasePresenter<AuthViewBehavior<User>, AuthInteractor, AuthRouter> {

    public AuthPresenter(Context context) {
        super(context);
    }

    public AuthPresenter(Context context, AuthViewBehavior<User> mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected AuthInteractor provideInteractor() {

        return new AuthInteractor(context);
    }

    @Override
    protected AuthRouter provideRouter() {
        return new AuthRouter(context);
    }

    /**
     * User authentication required fields for {@code submitLogin(empCode,password)}
     *
     * @param empCode  - User employee code.
     * @param password - User password.
     * @return data - It returns the result where user valid or not.
     */
    void submitLogin(Editable empCode, Editable password) {
        AuthUser authUser = new AuthUser(empCode.toString(), password.toString());

        HashMap<ValidationError, Integer> errors = authUser.validate();

        if (errors != null && errors.size() > 0) {
            viewBehaviour().validationError(errors);
        } else {
            viewBehaviour().onLoading();

            AuthUser auth = new AuthUser();
            auth.empCode = empCode.toString();
            auth.password = password.toString();

            Log.d("LoginAuth", auth.empCode
                    + "\t"
                    + auth.password
                    + "\t"
                    + getSecureAndroidId());


            interactor().submitLogin(auth, new ServiceCallback<UserResponse>() {

                @Override
                public void onSuccess(UserResponse userResponse) {

                    if (userResponse.user != null) {

                        User data = userResponse.getUser().get(0);
                        data.save();
                        Log.d("submitLogin:User", data.toString());

                        viewBehaviour().onSuccess(data);

                    } else if (userResponse.getMessage() != null && !userResponse.getMessage().isEmpty()) {
                        MyException myException = new MyException(userResponse.getMessage());
                        viewBehaviour().onFailed(myException);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    viewBehaviour().onFailed(e);
                    viewBehaviour().onComplete();
                    router().logout();
                }

            });
        }
    }

    public class MyException extends Exception {

        String message;

        MyException(String message) {
            this.message = message;
        }

        // Overrides Exception's getMessage()
        @Override
        public String getMessage() {
            return message;
        }

        // Testing
        public void main(String[] args) {
            MyException e = new MyException(message);
            System.out.println(e.getMessage());
        }
    }


    public void openSplash() {
        router().openMainActivity();
    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }
}
