package com.framework.cloud.mybatis.utils;

import com.baidu.fsg.uid.UidGenerator;
import com.framework.cloud.mybatis.SnowflakeIdConfiguration;
import lombok.experimental.UtilityClass;

/**
 * 临时的雪花ID生成
 *
 * @author wusiwei
 */
@UtilityClass
public final class IdUtil {

    /**
     * spring init reflect uidGenerator {@link SnowflakeIdConfiguration.AbstractIdChoose#create()}  }
     */
    private UidGenerator uidGenerator;

    public long getId() {
        return uidGenerator.getUID();
    }

    public String getIdStr() {
        return Long.toString(uidGenerator.getUID());
    }

    public String parseId(long id) {
        return uidGenerator.parseUID(id);
    }
}
