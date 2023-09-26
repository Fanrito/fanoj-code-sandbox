package com.fanrito.fanojcodesandbox;


import com.fanrito.fanojcodesandbox.model.ExecuteCodeRequest;
import com.fanrito.fanojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
