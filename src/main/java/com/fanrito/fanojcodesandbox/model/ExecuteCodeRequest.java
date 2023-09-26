package com.fanrito.fanojcodesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {
    /**
     * 一组输入
     */
    private List<String> inputList;

    /**
     * 执行代码
     */
    private String code;

    /**
     * 变成语言
     */
    private String language;
}
