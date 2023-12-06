package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_IINE)
@NamedQueries({
    @NamedQuery(
            name= JpaConst.Q_IINE_GET_ALL_REPORT,
            query= JpaConst.Q_IINE_GET_ALL_REPORT_DEF),
    @NamedQuery(
            name= JpaConst.Q_IINE_GET_COUNT_ALL,
            query= JpaConst.Q_IINE_GET_COUNT_ALL_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Iine {

    @Id
    @Column(name= JpaConst.IINE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.IINE_COL_EMP, nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = JpaConst.IINE_COL_REP, nullable = false)
    private Report report;

    @Column(name = JpaConst.IINE_COL_PUSH_FLAG, nullable = false)
    private Integer pushFlag;


}
