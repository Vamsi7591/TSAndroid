package com.android.timesheet.shared.services.rest;

import com.android.timesheet.shared.models.TimeSheetResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ILeaveService {

    @GET("applyLeave?")
    Observable<TimeSheetResponse> applyLeave(@Query("empCode") String empCode);

}
