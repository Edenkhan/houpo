package com.youruan.dentistry.console.enroll.form;

import com.youruan.dentistry.console.base.form.ListForm;
import com.youruan.dentistry.core.base.query.QueryCondition;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
public class EnrollListForm extends ListForm {


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreatedDate;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 类型 0职场百分百 1就业直通车 2普通活动
     */
    private Integer type;
    /**
     * 订单状态
     */
    private Boolean orderStatus;


    @Override
    public EnrollQuery buildQuery() {
        EnrollQuery qo = new EnrollQuery();
        qo.setPage(getPage());
        qo.setStartCreatedDate(startCreatedDate);
        qo.setEndCreatedDate(endCreatedDate);
        qo.setLikeOrderNo(orderNo);
        qo.setType(type);
        qo.setOrderStatus(orderStatus);
        if ("createdDate".equals(getSortField())) {
            qo.setOrderByCreatedDate(getSortOrder().equalsIgnoreCase("descend")
                    ? QueryCondition.ORDER_BY_KEYWORD_DESC
                    : QueryCondition.ORDER_BY_KEYWORD_ASC);
        }
        return qo;
    }
}
