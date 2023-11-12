package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Follow;

public class FollowConverter {

    public static Follow toModel(FollowView fv) {

        return new Follow(
                fv.getId(),
                EmployeeConverter.toModel(fv.getFollowing_name()),
                EmployeeConverter.toModel(fv.getFollower_name()));
    }

    public static FollowView toView(Follow f) {

        if( f == null) {
            return null;
        }
        return new FollowView(
                f.getId(),
                EmployeeConverter.toView(f.getFollowing_name()),
                EmployeeConverter.toView(f.getFollower_name()));
    }

    public static List<FollowView> toViewList(List<Follow> list){
        List<FollowView> fvs = new ArrayList<>();

        for(Follow f : list) {
            fvs.add(toView(f));
        }
        return fvs;
    }

    public static void copyViewToModel(Follow f, FollowView fv) {
        f.setId(fv.getId());
        f.setFollowing_name(EmployeeConverter.toModel(fv.getFollowing_name()));
        f.setFollower_name(EmployeeConverter.toModel(fv.getFollower_name()));
    }

}
