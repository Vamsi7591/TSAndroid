package com.android.timesheet.shared.services.rest;

import com.android.timesheet.shared.models.MonthResponse;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.WeekResponse;

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
    Observable<WeekResponse> getWeekReport(@Query("empCode") String empCode,
                                           @Query("weekNo") int weekNo,
                                           @Query("year") int year);

    @GET("getMonthReport?")
    Observable<MonthResponse> getMonthReport(@Query("empCode") String empCode,
                                             @Query("monthNo") int monthNo,
                                             @Query("year") int year);

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
                                                  @Query("endTime") String endTime,
                                                  @Query("timesheetid") long timesheetid);



    @GET("changePassword")
    Observable<TimeSheetResponse> changePassword(@Query("empcode") String empcode,
                                                 @Query("oldpwd") String oldpwd,
                                                 @Query("newpwd") String newpwd);

    @GET("updateemployee")
    Observable<TimeSheetResponse> updateEmployee(@Query("adminempcode") String adminempcode,
                                                 @Query("empcode") String empcode,
                                                 @Query("checkPassword") String password,
                                                 @Query("emprole") String emprole);


}
