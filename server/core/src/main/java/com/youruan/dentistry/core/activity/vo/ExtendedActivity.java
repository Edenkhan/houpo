package com.youruan.dentistry.core.activity.vo;

import com.youruan.dentistry.core.activity.domain.Activity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExtendedActivity extends Activity {

    private final static long serialVersionUID = 1L;
    private String recentEditor;

}
