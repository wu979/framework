package com.framework.cloud.holder.feign;

import com.framework.cloud.common.result.Result;
import com.framework.cloud.holder.vo.AuthorizationLoginVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wusiwei
 */
@FeignClient(contextId = "OauthHolderFeignService", value = "${client.oauth}", path = "/oauth", decode404 = true)
public interface OauthHolderFeignService {

    @PostMapping("/login")
    Result<AuthorizationLoginVO> loginUser(@RequestParam("authorization") String authorization);
}
