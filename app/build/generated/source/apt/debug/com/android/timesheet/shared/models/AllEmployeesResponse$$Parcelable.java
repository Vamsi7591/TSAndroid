
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
public class AllEmployeesResponse$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.AllEmployeesResponse>
{

    private com.android.timesheet.shared.models.AllEmployeesResponse allEmployeesResponse$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static AllEmployeesResponse$$Parcelable.Creator$$0 CREATOR = new AllEmployeesResponse$$Parcelable.Creator$$0();

    public AllEmployeesResponse$$Parcelable(com.android.timesheet.shared.models.AllEmployeesResponse allEmployeesResponse$$2) {
        allEmployeesResponse$$0 = allEmployeesResponse$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(allEmployeesResponse$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.AllEmployeesResponse allEmployeesResponse$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(allEmployeesResponse$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(allEmployeesResponse$$1));
            parcel$$1 .writeInt(allEmployeesResponse$$1 .code);
            if (allEmployeesResponse$$1 .employeeList == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(allEmployeesResponse$$1 .employeeList.size());
                for (com.android.timesheet.shared.models.Employee employee$$0 : ((List<com.android.timesheet.shared.models.Employee> ) allEmployeesResponse$$1 .employeeList)) {
                    com.android.timesheet.shared.models.Employee$$Parcelable.write(employee$$0, parcel$$1, flags$$0, identityMap$$0);
                }
            }
            parcel$$1 .writeString(allEmployeesResponse$$1 .message);
            parcel$$1 .writeInt((allEmployeesResponse$$1 .status? 1 : 0));
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.AllEmployeesResponse getParcel() {
        return allEmployeesResponse$$0;
    }

    public static com.android.timesheet.shared.models.AllEmployeesResponse read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.AllEmployeesResponse allEmployeesResponse$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            allEmployeesResponse$$3 = new com.android.timesheet.shared.models.AllEmployeesResponse();
            identityMap$$1 .put(reservation$$0, allEmployeesResponse$$3);
            allEmployeesResponse$$3 .code = parcel$$3 .readInt();
            int int$$0 = parcel$$3 .readInt();
            ArrayList<com.android.timesheet.shared.models.Employee> list$$0;
            if (int$$0 < 0) {
                list$$0 = null;
            } else {
                list$$0 = new ArrayList<com.android.timesheet.shared.models.Employee>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    com.android.timesheet.shared.models.Employee employee$$1 = com.android.timesheet.shared.models.Employee$$Parcelable.read(parcel$$3, identityMap$$1);
                    list$$0 .add(employee$$1);
                }
            }
            allEmployeesResponse$$3 .employeeList = list$$0;
            allEmployeesResponse$$3 .message = parcel$$3 .readString();
            allEmployeesResponse$$3 .status = (parcel$$3 .readInt() == 1);
            return allEmployeesResponse$$3;
        }
    }

    public final static class Creator$$0
        implements Creator<AllEmployeesResponse$$Parcelable>
    {


        @Override
        public AllEmployeesResponse$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new AllEmployeesResponse$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public AllEmployeesResponse$$Parcelable[] newArray(int size) {
            return new AllEmployeesResponse$$Parcelable[size] ;
        }

    }

}
