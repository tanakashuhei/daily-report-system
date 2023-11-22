package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.FollowView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.EmployeeService;
import services.FollowService;
import services.ReportService;

public class FollowAction extends ActionBase {

    private FollowService service;
    private ReportService serviceRep;
    private EmployeeService serviceEmp;

    @Override
    public void process() throws ServletException, IOException {

        service = new FollowService();
        serviceRep = new ReportService();
        serviceEmp = new EmployeeService();

        invoke();
        serviceRep.close();
        service.close();

    }

    public void index() throws ServletException, IOException{

        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        int page = getPage();
        List<FollowView> follows = service.getMinePerPage(loginEmployee,page);

        long myFollowsCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.FOLLOWS, follows);
        putRequestScope(AttributeConst.FOLL_COUNT, myFollowsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        forward(ForwardConst.FW_FOLL_INDEX);
    }
    //従業員を指定してFollowのデータベースに登録する
    public void create() throws ServletException, IOException{
        EmployeeView loginEmployee = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
        String id =getRequestParam(AttributeConst.FOLL_ID);
        EmployeeView followerEmployee = serviceEmp.findOne(toNumber(id));


        //System.out.println(loginEmployee+"これがログイン");
        //System.out.println(followerEmployee+"これが相手");

        FollowView fv = new FollowView(
                null,
                loginEmployee,
                followerEmployee);

        service.create(fv);
        redirect(ForwardConst.ACT_FOLL, ForwardConst.CMD_INDEX);
    }
    //指定した従業員のレポート一覧を呼ぶ
    public void show() throws ServletException, IOException{

        EmployeeView follows = serviceEmp.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));

        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        boolean fv = service.findRelation(loginEmployee, follows);

        int page = getPage();
        List<ReportView> reports = serviceRep.getMinePerPage(follows, page);

        long followReportsCount = serviceRep.countAllMine(follows);

        System.out.println(fv);

        putRequestScope(AttributeConst.FOLL_RELATION, fv);
        putRequestScope(AttributeConst.FOLL_NAME_ER, follows);
        putRequestScope(AttributeConst.REPORTS, reports);
        putRequestScope(AttributeConst.REP_COUNT, followReportsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        forward(ForwardConst.FW_FOLL_SHOW);
       /* System.out.println(follows);
        System.out.println(reports);
        System.out.println(followReportsCount);
        System.out.println(page);*/
    }
    public void destroy() throws ServletException, IOException{

        FollowView fv = service.findOne(toNumber(getRequestParam(AttributeConst.FOLL_ID)));

        //System.out.println(fv+"これが相手");
        service.deleat(fv);

        redirect(ForwardConst.ACT_FOLL, ForwardConst.CMD_INDEX);

    }



}
