package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IineView {

    private Integer id;

    private EmployeeView employee;

    private ReportView report;

    private Integer pushFlag;

}
