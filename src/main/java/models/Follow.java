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

@Table(name = JpaConst.TABLE_FOLL)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_FOLL_GET_ALL_MINE,
            query = JpaConst.Q_FOLL_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOLL_COUNT_ALL_MINE,
            query = JpaConst.Q_FOLL_COUNT_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOLL_FIND_RELATION,
            query = JpaConst.Q_FOLL_FIND_RELATION_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow {

    @Id
    @Column(name= JpaConst.FOLL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name= JpaConst.FOLL_COL_FOLLOWING_NAME, nullable= false)
    private Employee following_name;

    @ManyToOne
    @JoinColumn(name= JpaConst.FOLL_COL_FOLLOWEW_NAME, nullable= false)
    private Employee follower_name;


}
