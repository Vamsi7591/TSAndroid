
package com.android.timesheet.shared.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2018-08-21T19:50+0530")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class WeekParams$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.WeekParams>
{

    private com.android.timesheet.shared.models.WeekParams weekParams$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static WeekParams$$Parcelable.Creator$$8 CREATOR = new WeekParams$$Parcelable.Creator$$8();

    public WeekParams$$Parcelable(com.android.timesheet.shared.models.WeekParams weekParams$$2) {
        weekParams$$0 = weekParams$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(weekParams$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.WeekParams weekParams$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(weekParams$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(weekParams$$1));
            parcel$$1 .writeString(weekParams$$1 .empCode);
            parcel$$1 .writeInt(weekParams$$1 .year);
            parcel$$1 .writeInt(weekParams$$1 .weekNo);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.WeekParams getParcel() {
        return weekParams$$0;
    }

    public static com.android.timesheet.shared.models.WeekParams read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.WeekParams weekParams$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            weekParams$$3 = new com.android.timesheet.shared.models.WeekParams();
            identityMap$$1 .put(reservation$$0, weekParams$$3);
            weekParams$$3 .empCode = parcel$$3 .readString();
            weekParams$$3 .year = parcel$$3 .readInt();
            weekParams$$3 .weekNo = parcel$$3 .readInt();
            return weekParams$$3;
        }
    }

    public final static class Creator$$8
        implements Creator<WeekParams$$Parcelable>
    {


        @Override
        public WeekParams$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new WeekParams$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public WeekParams$$Parcelable[] newArray(int size) {
            return new WeekParams$$Parcelable[size] ;
        }

    }

}
