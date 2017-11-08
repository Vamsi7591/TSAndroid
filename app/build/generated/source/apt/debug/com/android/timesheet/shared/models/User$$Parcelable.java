
package com.android.timesheet.shared.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2017-11-06T19:25+0530")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class User$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.User>
{

    private com.android.timesheet.shared.models.User user$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static User$$Parcelable.Creator$$5 CREATOR = new User$$Parcelable.Creator$$5();

    public User$$Parcelable(com.android.timesheet.shared.models.User user$$2) {
        user$$0 = user$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(user$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.User user$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(user$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(user$$1));
            parcel$$1 .writeString(user$$1 .date);
            parcel$$1 .writeString(user$$1 .password);
            parcel$$1 .writeString(user$$1 .empCode);
            parcel$$1 .writeString(user$$1 .empName);
            parcel$$1 .writeString(user$$1 .empRole);
            parcel$$1 .writeString(user$$1 .adminempcode);
            parcel$$1 .writeString(user$$1 .emailId);
            parcel$$1 .writeString(user$$1 .projectName);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.User getParcel() {
        return user$$0;
    }

    public static com.android.timesheet.shared.models.User read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.User user$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            user$$3 = new com.android.timesheet.shared.models.User();
            identityMap$$1 .put(reservation$$0, user$$3);
            user$$3 .date = parcel$$3 .readString();
            user$$3 .password = parcel$$3 .readString();
            user$$3 .empCode = parcel$$3 .readString();
            user$$3 .empName = parcel$$3 .readString();
            user$$3 .empRole = parcel$$3 .readString();
            user$$3 .adminempcode = parcel$$3 .readString();
            user$$3 .emailId = parcel$$3 .readString();
            user$$3 .projectName = parcel$$3 .readString();
            return user$$3;
        }
    }

    public final static class Creator$$5
        implements Creator<User$$Parcelable>
    {


        @Override
        public User$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new User$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public User$$Parcelable[] newArray(int size) {
            return new User$$Parcelable[size] ;
        }

    }

}
