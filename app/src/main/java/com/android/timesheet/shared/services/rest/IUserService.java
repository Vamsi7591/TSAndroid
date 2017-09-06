package com.android.timesheet.shared.services.rest;

import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public interface IUserService {

    @GET("getEmployeeTimeSheet?")
    Observable<TimeSheetResponse> getTimeSheet(@Query("empCode") String empCode,
                                               @Query("empRole") String empRole);


    @GET("getDayReport?")
    Observable<TimeSheetResponse> getDayReport(@Query("empCode") String empCode,
                                               @Query("date") String date);

    @GET("getWeekReport?")
    Observable<TimeSheetResponse> getWeekReport(@Query("empCode") String empCode,
                                                @Query("weekNo") String weekNo,
                                                @Query("year") String year);

    @GET("getMonthReport?")
    Observable<TimeSheetResponse> getMonthReport(@Query("empCode") String empCode,
                                                 @Query("monthNo") String monthNo,
                                                 @Query("year") String year);

    @GET("getProjectNames?")
    Observable<ProjectNamesResponse> getProjectNames(@Query("empCode") String empCode);

    @GET("submitTimeSheet?")
    Observable<TimeSheetResponse> submitTimeSheet(@Query("empCode") String empCode,
                                                  @Query("date") String date,
                                                  @Query("weekNo") String weekNo,
                                                  @Query("projectName") String projectName,
                                                  @Query("taskDescription") String taskDescription,
                                                  @Query("startTime") String startTime,
                                                  @Query("endTime") String endTime);


    @GET("removeTimeSheet?")
    Observable<TimeSheetResponse> removeTimeSheet(@Query("empCode") String empCode,
                                                  @Query("timeSheetId") String timeSheetId);

    @GET("updateTimeSheet?")
    Observable<TimeSheetResponse> updateTimeSheet(@Query("empCode") String empCode,
                                                  @Query("date") String date,
                                                  @Query("projectName") String projectName,
                                                  @Query("taskDescription") String taskDescription,
                                                  @Query("startTime") String startTime,
                                                  @Query("endTime") String endTime);

}
