
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
public class Project$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.Project>
{

    private com.android.timesheet.shared.models.Project project$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Project$$Parcelable.Creator$$3 CREATOR = new Project$$Parcelable.Creator$$3();

    public Project$$Parcelable(com.android.timesheet.shared.models.Project project$$2) {
        project$$0 = project$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(project$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.Project project$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(project$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(project$$1));
            parcel$$1 .writeInt((project$$1 .commonFlag? 1 : 0));
            parcel$$1 .writeString(project$$1 .projectCode);
            parcel$$1 .writeString(project$$1 .projectName);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.Project getParcel() {
        return project$$0;
    }

    public static com.android.timesheet.shared.models.Project read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.Project project$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            project$$3 = new com.android.timesheet.shared.models.Project();
            identityMap$$1 .put(reservation$$0, project$$3);
            project$$3 .commonFlag = (parcel$$3 .readInt() == 1);
            project$$3 .projectCode = parcel$$3 .readString();
            project$$3 .projectName = parcel$$3 .readString();
            return project$$3;
        }
    }

    public final static class Creator$$3
        implements Creator<Project$$Parcelable>
    {


        @Override
        public Project$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Project$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Project$$Parcelable[] newArray(int size) {
            return new Project$$Parcelable[size] ;
        }

    }

}
