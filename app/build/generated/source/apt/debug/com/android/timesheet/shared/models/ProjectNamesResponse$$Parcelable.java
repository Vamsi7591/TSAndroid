
package com.android.timesheet.shared.models;

import java.util.ArrayList;
import java.util.List;
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
public class ProjectNamesResponse$$Parcelable
    implements Parcelable, ParcelWrapper<com.android.timesheet.shared.models.ProjectNamesResponse>
{

    private com.android.timesheet.shared.models.ProjectNamesResponse projectNamesResponse$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static ProjectNamesResponse$$Parcelable.Creator$$3 CREATOR = new ProjectNamesResponse$$Parcelable.Creator$$3();

    public ProjectNamesResponse$$Parcelable(com.android.timesheet.shared.models.ProjectNamesResponse projectNamesResponse$$2) {
        projectNamesResponse$$0 = projectNamesResponse$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(projectNamesResponse$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.android.timesheet.shared.models.ProjectNamesResponse projectNamesResponse$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(projectNamesResponse$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(projectNamesResponse$$1));
            if (projectNamesResponse$$1 .projectList == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(projectNamesResponse$$1 .projectList.size());
                for (com.android.timesheet.shared.models.Project project$$0 : ((List<com.android.timesheet.shared.models.Project> ) projectNamesResponse$$1 .projectList)) {
                    com.android.timesheet.shared.models.Project$$Parcelable.write(project$$0, parcel$$1, flags$$0, identityMap$$0);
                }
            }
            parcel$$1 .writeInt(projectNamesResponse$$1 .code);
            parcel$$1 .writeString(projectNamesResponse$$1 .message);
            parcel$$1 .writeInt((projectNamesResponse$$1 .status? 1 : 0));
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.android.timesheet.shared.models.ProjectNamesResponse getParcel() {
        return projectNamesResponse$$0;
    }

    public static com.android.timesheet.shared.models.ProjectNamesResponse read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.android.timesheet.shared.models.ProjectNamesResponse projectNamesResponse$$3;
            int reservation$$0 = identityMap$$1 .reserve();
            projectNamesResponse$$3 = new com.android.timesheet.shared.models.ProjectNamesResponse();
            identityMap$$1 .put(reservation$$0, projectNamesResponse$$3);
            int int$$0 = parcel$$3 .readInt();
            ArrayList<com.android.timesheet.shared.models.Project> list$$0;
            if (int$$0 < 0) {
                list$$0 = null;
            } else {
                list$$0 = new ArrayList<com.android.timesheet.shared.models.Project>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    com.android.timesheet.shared.models.Project project$$1 = com.android.timesheet.shared.models.Project$$Parcelable.read(parcel$$3, identityMap$$1);
                    list$$0 .add(project$$1);
                }
            }
            projectNamesResponse$$3 .projectList = list$$0;
            projectNamesResponse$$3 .code = parcel$$3 .readInt();
            projectNamesResponse$$3 .message = parcel$$3 .readString();
            projectNamesResponse$$3 .status = (parcel$$3 .readInt() == 1);
            return projectNamesResponse$$3;
        }
    }

    public final static class Creator$$3
        implements Creator<ProjectNamesResponse$$Parcelable>
    {


        @Override
        public ProjectNamesResponse$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new ProjectNamesResponse$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public ProjectNamesResponse$$Parcelable[] newArray(int size) {
            return new ProjectNamesResponse$$Parcelable[size] ;
        }

    }

}
