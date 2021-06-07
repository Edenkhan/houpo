package com.youruan.dentistry.console.activity.form;

import com.youruan.dentistry.console.base.form.ListForm;
import com.youruan.dentistry.core.banner.query.BannerQuery;
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
     * 轮播图名称
     */
    private String bannerName;
    /**
     * 状态 0-停用 1-启用
     */
    private Integer status;


    @Override
    public BannerQuery buildQuery() {
        BannerQuery qo = new BannerQuery();
        qo.setPage(getPage());
        qo.setStartCreatedDate(startCreatedDate);
        qo.setEndCreatedDate(endCreatedDate);
        qo.setLikeBannerName(bannerName);
        qo.setStatus(status);
        if ("createdDate".equals(getSortField())) {
            qo.setOrderByCreatedDate(getSortOrder().equalsIgnoreCase("descend")
                    ? QueryCondition.ORDER_BY_KEYWORD_DESC
                    : QueryCondition.ORDER_BY_KEYWORD_ASC);
        }
        return qo;
    }
}
