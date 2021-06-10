package com.youruan.dentistry.console.activity.form;

import com.youruan.dentistry.console.base.form.ListForm;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.base.query.QueryCondition;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
public class ActivityListForm extends ListForm {


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreatedDate;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 报名状态 0-关闭 1-开启
     */
    private Integer enrollStatus;

    /**
     * 发布状态 0-未发布 1-已发布
     */
    private Integer releaseStatus;


    @Override
    public ActivityQuery buildQuery() {
        ActivityQuery qo = new ActivityQuery();
        qo.setPage(getPage());
        qo.setStartCreatedDate(startCreatedDate);
        qo.setEndCreatedDate(endCreatedDate);
        qo.setLikeTitle(title);
        qo.setEnrollStatus(enrollStatus);
        qo.setReleaseStatus(releaseStatus);
        if ("createdDate".equals(getSortField())) {
            qo.setOrderByCreatedDate(getSortOrder().equalsIgnoreCase("descend")
                    ? QueryCondition.ORDER_BY_KEYWORD_DESC
                    : QueryCondition.ORDER_BY_KEYWORD_ASC);
        }
        return qo;
    }
}
