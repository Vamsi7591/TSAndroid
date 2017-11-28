package com.android.timesheet.shared.services.rest;

import com.android.timesheet.shared.models.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public interface IAuthService {

    @GET("getValidateUser?")
    Observable<UserResponse> login(@Query("empCode") String empCode,
                                   @Query("password") String password);

//    @GET("changePassword?")
//    Observable<UserResponse> changePassword(@Query("empCode") String empCode,
//                                   @Query("oldPwd") String oldPwd,
//                                   @Query("newPwd") String newPwd);


}
