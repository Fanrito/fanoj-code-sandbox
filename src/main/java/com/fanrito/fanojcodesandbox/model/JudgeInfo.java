package com.fanrito.fanojcodesandbox.model;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 判题信息
     */
    private String message;

    /**
     * 执行时间（ms）
     */
    private Long time;

    /**
     * 消耗内存（KB）
     */
    private Long memory;
}
