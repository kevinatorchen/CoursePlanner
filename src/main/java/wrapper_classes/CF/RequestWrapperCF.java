package wrapper_classes.CF;

import java.util.List;

/**
 * Created by Kevin on 5/28/2018.
 */
public class RequestWrapperCF {
    private List<CourseCF> courseRequests;
    private List<ActivityCF> activityRequests;

    public RequestWrapperCF() {

    }

    public List<CourseCF> getCourseRequests() {
        return courseRequests;
    }

    public List<ActivityCF> getActivityRequests() {
        return activityRequests;
    }



}
