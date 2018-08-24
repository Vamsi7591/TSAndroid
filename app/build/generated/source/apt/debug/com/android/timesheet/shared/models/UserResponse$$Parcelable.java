
package com.android.timesheet.shared.models;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2018-08-24T20:01+0530")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class UserResponse$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.UserResponse>
{

    private com.android.timesheet.shared.models.UserResponse userResponse$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static UserResponse$$Parcelable.Creator$$7 CREATOR = new UserResponse$$Parcelable.Creator$$7();

    public UserResponse$$Parcelable(com.android.timesheet.shared.models.UserResponse userResponse$$2) {
        userResponse$$0 = userResponse$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(userResponse$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.UserResponse userResponse$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(userResponse$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(userResponse$$1));
            parcel$$1 .writeInt(userResponse$$1 .code);
            parcel$$1 .writeString(userResponse$$1 .message);
            if (userResponse$$1 .user == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(userResponse$$1 .user.size());
                for (com.android.timesheet.shared.models.User user$$0 : ((List<com.android.timesheet.shared.models.User> ) userResponse$$1 .user)) {
                    com.android.timesheet.shared.models.User$$Parcelable.write(user$$0, parcel$$1, flags$$0, identityMap$$0);
                }
            }
            parcel$$1 .writeInt((userResponse$$1 .status? 1 : 0));
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.UserResponse getParcel() {
        return userResponse$$0;
    }

    public static com.android.timesheet.shared.models.UserResponse read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.UserResponse userResponse$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            userResponse$$3 = new com.android.timesheet.shared.models.UserResponse();
            identityMap$$1 .put(reservation$$0, userResponse$$3);
            userResponse$$3 .code = parcel$$3 .readInt();
            userResponse$$3 .message = parcel$$3 .readString();
            int int$$0 = parcel$$3 .readInt();
            ArrayList<com.android.timesheet.shared.models.User> list$$0;
            if (int$$0 < 0) {
                list$$0 = null;
            } else {
                list$$0 = new ArrayList<com.android.timesheet.shared.models.User>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    com.android.timesheet.shared.models.User user$$1 = com.android.timesheet.shared.models.User$$Parcelable.read(parcel$$3, identityMap$$1);
                    list$$0 .add(user$$1);
                }
            }
            userResponse$$3 .user = list$$0;
            userResponse$$3 .status = (parcel$$3 .readInt() == 1);
            return userResponse$$3;
        }
    }

    public final static class Creator$$7
        implements Creator<UserResponse$$Parcelable>
    {


        @Override
        public UserResponse$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new UserResponse$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public UserResponse$$Parcelable[] newArray(int size) {
            return new UserResponse$$Parcelable[size] ;
        }

    }

}
