
package com.android.timesheet.shared.models;

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
public class Employee$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.Employee>
{

    private com.android.timesheet.shared.models.Employee employee$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Employee$$Parcelable.Creator$$1 CREATOR = new Employee$$Parcelable.Creator$$1();

    public Employee$$Parcelable(com.android.timesheet.shared.models.Employee employee$$2) {
        employee$$0 = employee$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(employee$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.Employee employee$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(employee$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(employee$$1));
            parcel$$1 .writeString(employee$$1 .password);
            parcel$$1 .writeString(employee$$1 .projectname);
            parcel$$1 .writeString(employee$$1 .empCode);
            parcel$$1 .writeString(employee$$1 .createdBy);
            parcel$$1 .writeString(employee$$1 .empName);
            parcel$$1 .writeString(employee$$1 .empRole);
            parcel$$1 .writeString(employee$$1 .empEmailId);
            parcel$$1 .writeString(employee$$1 .adminempcode);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.Employee getParcel() {
        return employee$$0;
    }

    public static com.android.timesheet.shared.models.Employee read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.Employee employee$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            employee$$3 = new com.android.timesheet.shared.models.Employee();
            identityMap$$1 .put(reservation$$0, employee$$3);
            employee$$3 .password = parcel$$3 .readString();
            employee$$3 .projectname = parcel$$3 .readString();
            employee$$3 .empCode = parcel$$3 .readString();
            employee$$3 .createdBy = parcel$$3 .readString();
            employee$$3 .empName = parcel$$3 .readString();
            employee$$3 .empRole = parcel$$3 .readString();
            employee$$3 .empEmailId = parcel$$3 .readString();
            employee$$3 .adminempcode = parcel$$3 .readString();
            return employee$$3;
        }
    }

    public final static class Creator$$1
        implements Creator<Employee$$Parcelable>
    {


        @Override
        public Employee$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Employee$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Employee$$Parcelable[] newArray(int size) {
            return new Employee$$Parcelable[size] ;
        }

    }

}
