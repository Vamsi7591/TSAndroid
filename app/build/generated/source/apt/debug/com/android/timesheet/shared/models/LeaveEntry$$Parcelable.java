
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
public class LeaveEntry$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.LeaveEntry>
{

    private com.android.timesheet.shared.models.LeaveEntry leaveEntry$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static LeaveEntry$$Parcelable.Creator$$2 CREATOR = new LeaveEntry$$Parcelable.Creator$$2();

    public LeaveEntry$$Parcelable(com.android.timesheet.shared.models.LeaveEntry leaveEntry$$2) {
        leaveEntry$$0 = leaveEntry$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(leaveEntry$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.LeaveEntry leaveEntry$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(leaveEntry$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(leaveEntry$$1));
            parcel$$1 .writeString(leaveEntry$$1 .fromDate);
            parcel$$1 .writeString(leaveEntry$$1 .approvedDate);
            parcel$$1 .writeString(leaveEntry$$1 .leaveType);
            parcel$$1 .writeString(leaveEntry$$1 .approvedRemarks);
            parcel$$1 .writeString(leaveEntry$$1 .empCode);
            parcel$$1 .writeString(leaveEntry$$1 .toDate);
            parcel$$1 .writeLong(leaveEntry$$1 .leaveEntryId);
            parcel$$1 .writeString(leaveEntry$$1 .remarks);
            parcel$$1 .writeString(leaveEntry$$1 .noOfDays);
            parcel$$1 .writeString(leaveEntry$$1 .status);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.LeaveEntry getParcel() {
        return leaveEntry$$0;
    }

    public static com.android.timesheet.shared.models.LeaveEntry read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.LeaveEntry leaveEntry$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            leaveEntry$$3 = new com.android.timesheet.shared.models.LeaveEntry();
            identityMap$$1 .put(reservation$$0, leaveEntry$$3);
            leaveEntry$$3 .fromDate = parcel$$3 .readString();
            leaveEntry$$3 .approvedDate = parcel$$3 .readString();
            leaveEntry$$3 .leaveType = parcel$$3 .readString();
            leaveEntry$$3 .approvedRemarks = parcel$$3 .readString();
            leaveEntry$$3 .empCode = parcel$$3 .readString();
            leaveEntry$$3 .toDate = parcel$$3 .readString();
            leaveEntry$$3 .leaveEntryId = parcel$$3 .readLong();
            leaveEntry$$3 .remarks = parcel$$3 .readString();
            leaveEntry$$3 .noOfDays = parcel$$3 .readString();
            leaveEntry$$3 .status = parcel$$3 .readString();
            return leaveEntry$$3;
        }
    }

    public final static class Creator$$2
        implements Creator<LeaveEntry$$Parcelable>
    {


        @Override
        public LeaveEntry$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new LeaveEntry$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public LeaveEntry$$Parcelable[] newArray(int size) {
            return new LeaveEntry$$Parcelable[size] ;
        }

    }

}
