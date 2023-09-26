package com.fanrito.fanojcodesandbox.controller;

import com.fanrito.fanojcodesandbox.JavaDockerCodeSandbox;
import com.fanrito.fanojcodesandbox.JavaNativeCodeSandbox;
import com.fanrito.fanojcodesandbox.model.ExecuteCodeRequest;
import com.fanrito.fanojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/")
public class MainController {

    private static final String AUTH_REQUEST_HEADER = "auth";

//    todo docker沙箱有bug，待解决
//    @Resource
//    private JavaDockerCodeSandbox javaDockerCodeSandbox;

    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest) {
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        return javaNativeCodeSandbox.executeCode(executeCodeRequest);
    }

}
