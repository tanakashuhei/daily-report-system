package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.IineView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.IineService;
import services.ReportService;

public class IineAction extends ActionBase {

    private IineService service;
    private ReportService serviceRep;

    @Override
    public void process() throws ServletException, IOException {

        service = new IineService();
        serviceRep = new ReportService();

        invoke();

        serviceRep.close();
        service.close();
    }

    public void edit() throws ServletException, IOException{

        EmployeeView loginEmployee = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        String id = getRequestParam(AttributeConst.REP_ID);
        ReportView pushReport = serviceRep.findOne(toNumber(id));

        String flagId = getRequestParam(AttributeConst.IINE_FLAG);
        Integer pushFlag = toNumber(flagId);

        IineView iv = new IineView(
                null,
                loginEmployee,
                pushReport,
                pushFlag);

        //putSessionScope(AttributeConst.IINE_INFO,iv);

        List<IineView> ivs = service.getAllReport(loginEmployee,pushReport);

        if(ivs.size() == 0) {
            //redirect(ForwardConst.ACT_IINE,ForwardConst.CMD_CREATE);
            service.create(iv);

        }else if(ivs.size() == 1 && ivs.get(0).getPushFlag() ==pushFlag ) {
            //redirect(ForwardConst.ACT_IINE,ForwardConst.CMD_DESTROY);
            service.deleat(ivs.get(0));

        }else {
            //redirect(ForwardConst.ACT_IINE, ForwardConst.CMD_UPDATE);
            iv.setId(ivs.get(0).getId());
            service.update(iv);
        }
        redirect(ForwardConst.ACT_REP,ForwardConst.CMD_SHOW, id);
    }


}
