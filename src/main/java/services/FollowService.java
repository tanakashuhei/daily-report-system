package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FollowConverter;
import actions.views.FollowView;
import constants.JpaConst;
import models.Follow;

public class FollowService extends ServiceBase {

    public List<FollowView> getMinePerPage(EmployeeView employee,int page){
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOLL_GET_ALL_MINE, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWING, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE*(page-1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return FollowConverter.toViewList(follows);
    }
    public long countAllMine(EmployeeView employee) {
        long count = (long) em.createNamedQuery(JpaConst.Q_FOLL_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWING, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /*public List<FollowView> getAllPerPage(int page){
        List<Follow> follows = em.createNamedQuery(JpaConst.Q_FOLL_GET_ALL, Follow.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE*(page-1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return FollowConverter.toViewList(follows);
    }*/

    public FollowView findOne(int id) {
        return FollowConverter.toView(findOneInternal(id));
    }

    public void create(FollowView fv) {
        createInternal(fv);
    }

    public void deleat(FollowView fv) {
        deleatInternal(fv);
    }

    private Follow findOneInternal(int id) {
        return em.find(Follow.class, id);
    }
    private void createInternal(FollowView fv) {

        em.getTransaction().begin();
        em.persist(FollowConverter.toModel(fv));
        em.getTransaction().commit();
    }
    private void deleatInternal(FollowView fv) {

        em.getTransaction().begin();
        Follow f = findOneInternal(fv.getId());
        FollowConverter.copyViewToModel(f,fv);
        em.remove(f);
        em.getTransaction().commit();
    }
    public boolean findRelation(EmployeeView following_name, EmployeeView follower_name) {


        List<Follow> relations = em.createNamedQuery(JpaConst.Q_FOLL_FIND_RELATION, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_FOLLOWING, EmployeeConverter.toModel(following_name))
                .setParameter(JpaConst.JPQL_PARM_FOLLOWER, EmployeeConverter.toModel(follower_name))
                .getResultList();

        //System.out.println(relations+"これ");
        Integer num = null;
        num = relations.size();

        if(num != 0) {
            return true;
        }else {
            return false;
        }

    }


}
