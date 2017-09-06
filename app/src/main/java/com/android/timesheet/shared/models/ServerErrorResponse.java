package com.android.timesheet.shared.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;
import java.util.Map;


/**
 * Created by vamsikonanki on 8/27/16.
 */
public class ServerErrorResponse {

    @SerializedName("errors")
    Object errors;

    public String getError() {
        if (errors != null) {
            if (errors instanceof LinkedTreeMap) {
                StringBuffer buffer = new StringBuffer();
                LinkedTreeMap<String, List<String>> map = ((LinkedTreeMap) errors);
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    buffer.append(entry.getKey().replace("_", " "));
                    buffer.append(" ");

                    try {
                        if (entry.getValue().size() > 0) {
                            buffer.append(entry.getValue().get(0));
                        }
                    } catch (Exception e) {
                    }
                }

                return buffer.toString();
            }

            return errors.toString().replace("\\W+", " ");
        } else {
            return "";
        }
    }
}
