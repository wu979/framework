package com.framework.cloud.mybatis.utils;

import com.baidu.fsg.uid.UidGenerator;
import lombok.experimental.UtilityClass;

/**
 * 临时的雪花ID生成
 *
 * @author wusiwei
 */
@UtilityClass
public final class IdUtil {

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
