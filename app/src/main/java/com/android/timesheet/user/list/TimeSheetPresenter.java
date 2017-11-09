package com.android.timesheet.user.list;

import android.content.Context;

import com.android.timesheet.shared.models.TimeSheet;
import com.android.timesheet.shared.models.TimeSheetResponse;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.presenters.BasePresenter;
import com.android.timesheet.shared.services.ServiceCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vamsikonanki on 8/22/2017.
 */

public class TimeSheetPresenter extends BasePresenter<TimeSheetViewBehaviour, TimeSheetInteractor,
        TimeSheetRouter> {

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

                    List<TimeSheet> timeSheets = new ArrayList<>();
                    timeSheets = today_retroHashMap.get(today.getKey().toString());

                    for (int k = 0; k < timeSheets.size(); k++) {

                        timeSheets.get(k).setRowType(TimeSheet.TYPE_BODY);
                        arrayList.add(timeSheets.get(k));
                    }

                    TimeSheet sheet = new TimeSheet(today.getKey().toString());
                    sheet.setDate(today.getKey().toString());
                    arrayList.add(sheet);

                }

                Collections.sort(arrayList, new StringDateComparator());
                Collections.reverse(arrayList);

                viewBehaviour().onSuccess(arrayList);
            }

        });
    }

    private HashMap sortByValues(HashMap map) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(map.keySet());
//        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        /*Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });*/
        Collections.sort(list, new StringDateComparator2());

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap<String, List<TimeSheet>> sortedHashMap = new LinkedHashMap<String, List<TimeSheet>>();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put((String) entry.getKey(), (List<TimeSheet>) entry.getValue());
        }
        return sortedHashMap;
    }

    class StringDateComparator implements Comparator<TimeSheet> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        public int compare(TimeSheet lhs, TimeSheet rhs) {
            try {
                return dateFormat.parse(lhs.date).compareTo(dateFormat.parse(rhs.date));
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    class StringDateComparator2 implements Comparator<String> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        public int compare(String lhs, String rhs) {
            try {
                return dateFormat.parse(lhs).compareTo(dateFormat.parse(rhs));
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public void removeTimeSheet(String empCode, String timeSheetId) {

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

    public void dayTimeSheet(String sheet) {
        router().dayTimeSheet(sheet);
    }
}
