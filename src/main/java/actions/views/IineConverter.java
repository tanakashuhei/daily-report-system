package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Iine;

public class IineConverter {

    public static Iine toModel(IineView iv) {

        return new Iine(
                iv.getId(),
                EmployeeConverter.toModel(iv.getEmployee()),
                ReportConverter.toModel(iv.getReport()),
                iv.getPushFlag());
    }

    public static IineView toView(Iine i) {

        if( i == null) {
            return null;
        }
        return new IineView(
                i.getId(),
                EmployeeConverter.toView(i.getEmployee()),
                ReportConverter.toView(i.getReport()),
                i.getPushFlag());
    }

    public static List<IineView> toViewList(List<Iine> list){
        List<IineView> ivs = new ArrayList<>();

        for(Iine i : list) {
            ivs.add(toView(i));
        }
        return ivs;
    }

    public static void copyViewToModel(Iine i, IineView iv) {

        i.setId(iv.getId());
        i.setEmployee(EmployeeConverter.toModel(iv.getEmployee()));
        i.setReport(ReportConverter.toModel(iv.getReport()));
        i.setPushFlag(iv.getPushFlag());
    }

}
