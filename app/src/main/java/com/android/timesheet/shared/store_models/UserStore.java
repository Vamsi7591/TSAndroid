package com.android.timesheet.shared.store_models;

import android.content.Context;

import com.android.timesheet.shared.exceptions.UserStoreException;
import com.android.timesheet.shared.models.User;
import com.android.timesheet.shared.models.User_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by vamsikonanki on 8/21/2017.
 */

public class UserStore extends BaseStore{

    private static User mUser;

    protected UserStore(Context context) {
        super(context);
    }

    public void saveAsCurrentUser(User user) {
        user.save();
    }

    public User getCurrentUser() throws UserStoreException {
        return UserStore.currentUser();
    }

    public static void clearUser() {
        mUser = null;
        SQLite.delete(User.class).execute();
    }

    public static User currentUser() {
        if (mUser == null) {
            long userWithToken = SQLite.selectCountOf(User_Table.empCode).from(User.class).where(User_Table.empCode.isNotNull()).count();

            if (userWithToken > 1) {
                throw new UserStoreException();
            }

            mUser = SQLite.select().from(User.class).where(User_Table.empCode.isNotNull()).querySingle();
        }

        return mUser;
    }

}
