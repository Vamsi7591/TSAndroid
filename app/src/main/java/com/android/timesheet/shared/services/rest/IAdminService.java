package com.android.timesheet.shared.services.rest;

import com.android.timesheet.shared.models.AllEmployeesResponse;
import com.android.timesheet.shared.models.ProjectNamesResponse;
import com.android.timesheet.shared.models.ProjectSum_Response;
import com.android.timesheet.shared.models.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vamsikonanki on 8/23/2017.
 */

public interface IAdminService {

    @GET("removeEmployee")
    Observable<AllEmployeesResponse> getRemoveEmployee(@Query("adminempcode") String adminempcode,
                                                       @Query("empcode") String empcode);

    @GET("getSummaryDetails?")
    Observable<ProjectSum_Response> getSummaryDetails(@Query("empCode") String empCode,
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

    @GET("getUnassignedProjectNames?")
    Observable<ProjectNamesResponse> getUnassignedProjectNames(@Query("empCode") String empCode);

    @GET("removeEmployeeFromProject?")
    Observable<AllEmployeesResponse> removeEmployeeFromProject(@Query("empCode") String empCode,
                                                               @Query("projectName") String projectName);

    @GET("assignEmployeeFromProject?")
    Observable<AllEmployeesResponse> assignEmployeeToProject(@Query("empCode") String empCode,
                                                             @Query("projectName") String projectName);
    /*Project master & Employee project*/

    /*Employee master*/
    @GET("getEmployee")
    Observable<AllEmployeesResponse> getEmployee();

    @GET("addemployee?")
    Observable<AllEmployeesResponse> addEmployee(@Query("adminempcode") String adminempcode,
                                                 @Query("empName") String empName,
                                                 @Query("empemailid") String empemailid,
                                                 @Query("checkPassword") String password,
                                                 @Query("emprole") String emprole);




    @GET("removeEmployee?")
    Observable<AllEmployeesResponse> removeEmployee(@Query("adminempcode") String adminEmpCode,
                                                    @Query("empcode") String empCode);

    @GET("removeProject")
    Observable<ProjectNamesResponse> removeProject (@Query("projectName") String projectName);

    @GET("updateEmployee?")
    Observable<AllEmployeesResponse> updateEmployee(@Query("adminEmpCode") String adminEmpCode,
                                                    @Query("empCode") String empCode,
                                                    @Query("checkPassword") String password,
                                                    @Query("empRole") String empRole);


    @GET("UpdateProjectName?")
    Observable<ProjectNamesResponse> updateProject(@Query("projectcode") String projectcode,
                                                   @Query("projectname") String projectname,
                                                   @Query("commonflag") boolean commonflag);

    @GET("addProject?")
    Observable<ProjectNamesResponse> addProject (@Query("projectname") String projectname,
                                                 @Query("commonflag") boolean commonflag);


}
