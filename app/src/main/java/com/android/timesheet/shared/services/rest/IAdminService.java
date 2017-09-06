package com.android.timesheet.shared.services.rest;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public interface IAdminService {

    @GET("getSummaryDetails?")
    Observable<UserResponse> getSummaryDetails(@Query("empCode") String empCode,
                                               @Query("projectName") String projectName,
                                               @Query("year") String year);

    @GET("getAdminStatus?")
    Observable<UserResponse> getAdminStatus(@Query("empCode") String empCode,
                                            @Query("isAdmin") boolean isAdmin);


    @GET("updateProfileDetails?")
    Observable<UserResponse> updateProfileDetails(@Query("empCode") String empCode,
                                                  @Query("empName") String empName);

    /*Project master & Employee project*/

    @GET("allProjectNames")
    Observable<ProjectNamesResponse> allProjectNames();

    @GET("getProjectNames?")
    Observable<ProjectNamesResponse> getProjectNames(@Query("empCode") String empCode);

    @GET("removeProject?")
    Observable<UserResponse> removeProject(@Query("projectName") String projectName);

    @GET("getUnassignedProjectNames?")
    Observable<UserResponse> getUnassignedProjectNames(@Query("empCode") String empCode);

    @GET("removeEmployeeFromProject?")
    Observable<UserResponse> removeEmployeeFromProject(@Query("empCode") String empCode,
                                                       @Query("projectName") String projectName);

    @GET("assignEmployeeFromProject?")
    Observable<UserResponse> assignEmployeeToProject(@Query("empCode") String empCode,
                                                     @Query("projectName") String projectName);
    /*Project master & Employee project*/

    /*Employee master*/
    @GET("getEmployee")
    Observable<AllEmployeesResponse> getEmployee();

    @GET("addEmployee?")
    Observable<AllEmployeesResponse> addEmployee(@Query("adminEmpCode") String adminEmpCode,
                                                 @Query("empName") String empName,
                                                 @Query("empEmailId") String empEmailId,
                                                 @Query("password") String password,
                                                 @Query("empRole") String empRole);

    @GET("removeEmployee?")
    Observable<AllEmployeesResponse> removeEmployee(@Query("adminEmpCode") String adminEmpCode,
                                                    @Query("empCode") String empCode);

    @GET("updateEmployee?")
    Observable<AllEmployeesResponse> updateEmployee(@Query("adminEmpCode") String adminEmpCode,
                                                    @Query("empCode") String empCode,
                                                    @Query("password") String password,
                                                    @Query("empRole") String empRole);
    /*Employee master*/


}
