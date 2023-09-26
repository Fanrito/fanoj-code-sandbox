package com.fanrito.fanojcodesandbox.utils;

import cn.hutool.core.util.StrUtil;
import com.fanrito.fanojcodesandbox.model.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.*;

/**
 * 进程工具类
 */
public class ProcessUtils {
    /**
     * 执行进程并获取信息
     *
     * @param runProcess
     * @param OpName
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String OpName) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            if (exitValue == 0) {
                // 正常退出
                System.out.println(OpName + "成功");
                // 分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                // 逐行读取
                String compileOutputLine;
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());

            } else {
                // 异常退出
                System.out.println(OpName + "失败，错误码：" + exitValue);
                // 分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                // 逐行读取
                String compileOutputLine;
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());

                // 分批获取进程的错误输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                // 逐行读取
                String errorCompileOutputLine;
                StringBuilder errorCompileOutputStringBuilder = new StringBuilder();

                while ((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorCompileOutputStringBuilder.append(errorCompileOutputLine).append("\n");
                }
                executeMessage.setErrorMessage(errorCompileOutputStringBuilder.toString());
            }
            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return executeMessage;
    }

    /**
     * 执行交互式进程并获取信息
     *
     * @param runProcess
     * @param OpName
     * @return
     */
    public static ExecuteMessage runInteractProcessAndGetMessage(Process runProcess, String OpName, String args) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            OutputStream outputStream = runProcess.getOutputStream();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] s = args.split(" ");
            outputStreamWriter.write(StrUtil.join("\n", (Object) s) + "\n");
            outputStreamWriter.flush();

            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            if (exitValue == 0) {
                // 分批获取进程的正常输出
                InputStream inputStream = runProcess.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // 逐行读取
                String compileOutputLine;
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine);
                }
                executeMessage.setMessage(String.valueOf(compileOutputStringBuilder));
                outputStreamWriter.close();
                outputStream.close();
                inputStream.close();
                runProcess.destroy();
            } else {
                // 分批获取进程的正常输出
                InputStream inputStream = runProcess.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // 逐行读取
                String compileOutputLine;
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine);
                }
                executeMessage.setMessage(String.valueOf(compileOutputStringBuilder));

                // 分批获取进程的错误输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                // 逐行读取
                String errorCompileOutputLine;
                StringBuilder errorCompileOutputStringBuilder = new StringBuilder();

                while ((errorCompileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorCompileOutputStringBuilder.append(errorCompileOutputLine);
                }
                executeMessage.setErrorMessage(String.valueOf(errorCompileOutputStringBuilder));
            }
            return executeMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return executeMessage;
    }
}
