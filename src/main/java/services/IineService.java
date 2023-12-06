package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.IineConverter;
import actions.views.IineView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Iine;

public class IineService extends ServiceBase {

    public List<IineView> getAllReport(EmployeeView employee,ReportView report){//reportに対する全評価

        List<Iine> iines = em.createNamedQuery(JpaConst.Q_IINE_GET_ALL_REPORT, Iine.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(employee))
                .setParameter(JpaConst.JPQL_PARM_REPORT,ReportConverter.toModel(report))
                .getResultList();

        return IineConverter.toViewList(iines);
    }


    public long getCountAll(ReportView report, Integer pushFlag) {

        long reportPush = em.createNamedQuery(JpaConst.Q_IINE_GET_COUNT_ALL, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .setParameter(JpaConst.JPQL_PARM_PUSH, pushFlag)
                .getSingleResult();

        return reportPush;
    }
   /* public boolean findPush(EmployeeView employee, ReportView report) {

    }*/

    public IineView findOne(int id) {
        return IineConverter.toView(findOneInternal(id));
    }


    public void create(IineView iv) {
        createInternal(iv);
    }
    public void deleat(IineView iv) {
        deleatInternal(iv);
    }
    public void update(IineView iv) {
        updateInternal(iv);
    }

    private Iine findOneInternal(int id) {
        return em.find(Iine.class, id);
    }

    private void createInternal(IineView iv) {

        em.getTransaction().begin();
        em.persist(IineConverter.toModel(iv));
        em.getTransaction().commit();
    }
    private void deleatInternal(IineView iv) {

        em.getTransaction().begin();
        Iine i = findOneInternal(iv.getId());
        IineConverter.copyViewToModel(i, iv);
        em.remove(i);
        em.getTransaction().commit();
    }
    private void updateInternal(IineView iv) {

        em.getTransaction().begin();
        Iine i = findOneInternal(iv.getId());
        IineConverter.copyViewToModel(i, iv);
        em.getTransaction().commit();

    }
}
