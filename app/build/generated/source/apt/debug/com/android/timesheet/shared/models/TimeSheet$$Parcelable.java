
package com.android.timesheet.shared.models;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2018-07-11T09:47+0530")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class TimeSheet$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.TimeSheet>
{

    private com.android.timesheet.shared.models.TimeSheet timeSheet$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static TimeSheet$$Parcelable.Creator$$4 CREATOR = new TimeSheet$$Parcelable.Creator$$4();

    public TimeSheet$$Parcelable(com.android.timesheet.shared.models.TimeSheet timeSheet$$2) {
        timeSheet$$0 = timeSheet$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(timeSheet$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.TimeSheet timeSheet$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(timeSheet$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(timeSheet$$1));
            parcel$$1 .writeString(timeSheet$$1 .date);
            parcel$$1 .writeInt(timeSheet$$1 .rowType);
            parcel$$1 .writeString(timeSheet$$1 .totalHours);
            parcel$$1 .writeString(timeSheet$$1 .empCode);
            parcel$$1 .writeString(timeSheet$$1 .taskDescription);
            parcel$$1 .writeString(timeSheet$$1 .header);
            parcel$$1 .writeString(timeSheet$$1 .startTime);
            parcel$$1 .writeString(timeSheet$$1 .weekNo);
            parcel$$1 .writeLong(timeSheet$$1 .timeSheetId);
            parcel$$1 .writeString(timeSheet$$1 .endTime);
            parcel$$1 .writeString(timeSheet$$1 .projectName);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.TimeSheet getParcel() {
        return timeSheet$$0;
    }

    public static com.android.timesheet.shared.models.TimeSheet read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.TimeSheet timeSheet$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            timeSheet$$3 = new com.android.timesheet.shared.models.TimeSheet();
            identityMap$$1 .put(reservation$$0, timeSheet$$3);
            timeSheet$$3 .date = parcel$$3 .readString();
            timeSheet$$3 .rowType = parcel$$3 .readInt();
            timeSheet$$3 .totalHours = parcel$$3 .readString();
            timeSheet$$3 .empCode = parcel$$3 .readString();
            timeSheet$$3 .taskDescription = parcel$$3 .readString();
            timeSheet$$3 .header = parcel$$3 .readString();
            timeSheet$$3 .startTime = parcel$$3 .readString();
            timeSheet$$3 .weekNo = parcel$$3 .readString();
            timeSheet$$3 .timeSheetId = parcel$$3 .readLong();
            timeSheet$$3 .endTime = parcel$$3 .readString();
            timeSheet$$3 .projectName = parcel$$3 .readString();
            return timeSheet$$3;
        }
    }

    public final static class Creator$$4
        implements Creator<TimeSheet$$Parcelable>
    {


        @Override
        public TimeSheet$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new TimeSheet$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public TimeSheet$$Parcelable[] newArray(int size) {
            return new TimeSheet$$Parcelable[size] ;
        }

    }

}
