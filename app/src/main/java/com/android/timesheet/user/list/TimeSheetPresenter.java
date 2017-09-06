package com.android.timesheet.user.list;

import android.content.Context;

import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetPresenter extends BasePresenter<TimeSheetViewBehaviour, TimeSheetInteractor, TimeSheetRouter> {


    public TimeSheetPresenter(Context context) {
        super(context);
    }

    public TimeSheetPresenter(Context context, TimeSheetViewBehaviour mViewBehavior) {
        super(context, mViewBehavior);
    }

    @Override
    protected TimeSheetInteractor provideInteractor() {
        return new TimeSheetInteractor(context);
    }

    @Override
    protected TimeSheetRouter provideRouter() {
        return new TimeSheetRouter(context);
    }

    public void fetchTimeSheet(User user) {

        interactor().getEmployeeTimeSheet(user, new ServiceCallback<List<TimeSheet>>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(List<TimeSheet> data) {

                HashMap<String, List<TimeSheet>> today_retroHashMap = new HashMap<>();
                List<TimeSheet> arrayList = new ArrayList<>();
                arrayList = data;

                for (int i = 0; i < arrayList.size(); i++) {

                    ArrayList<TimeSheet> today_retroList = new ArrayList<>();
                    today_retroList.add(arrayList.get(i));

                    if (today_retroHashMap.containsKey(arrayList.get(i).getDate())) {

                        today_retroHashMap.get(arrayList.get(i).getDate()).add(arrayList.get(i));
                    } else {
                        today_retroHashMap.put((arrayList.get(i).getDate()), today_retroList);
                    }

                }

                arrayList = new ArrayList<>();

                for (HashMap.Entry today : today_retroHashMap.entrySet()) {

                    TimeSheet sheet = new TimeSheet(today.getKey().toString());
                    arrayList.add(sheet);

                    List<TimeSheet> timeSheets = new ArrayList<>();
                    timeSheets = today_retroHashMap.get(today.getKey().toString());

                    for (int k = 0; k < timeSheets.size(); k++) {

                        timeSheets.get(k).setRowType(TimeSheet.TYPE_BODY);
                        arrayList.add(timeSheets.get(k));
                    }

                }

                viewBehaviour().onSuccess(arrayList);
            }

        });


    }

    public void removeTimeSheet(String empCode,String timeSheetId){

        interactor().removeTimeSheet(empCode, timeSheetId, new ServiceCallback<TimeSheetResponse>() {
            @Override
            public void onFailure(Throwable e) {
                viewBehaviour().onFailed(e);
            }

            @Override
            public void onSuccess(TimeSheetResponse data) {
                viewBehaviour().removedTimeSheet(data);
            }
        });

    }

    protected User getCurrentUser() {
        return interactor().currentUser();
    }

    public void openTimeSheet(TimeSheet sheet) {
        router().openTimeSheet(sheet);
    }
}
