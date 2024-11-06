package org.simpleway.springprogram;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DelayController {

    @GetMapping("/delay")
    public String delayedResponse() throws InterruptedException {
        // 模拟长时间的处理，延迟 10 秒
        Thread.sleep(10000);
        return "Delayed response";
    }

    @GetMapping("/delayed-response")
    public void delayedResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();  // 发送部分数据后立即关闭

        // 模拟非正常关闭连接
        throw new IOException("Simulated connection abort");
    }

    @GetMapping("/controlled-close")
    public void controlledClose(HttpServletResponse response) throws IOException, InterruptedException {
        response.setStatus(HttpServletResponse.SC_OK);
        // 设置延迟时间以控制中断点
        Thread.sleep(3000);  // 等待3秒

        // 模拟非正常关闭连接
        throw new IOException("Simulated connection abort");
    }

    @GetMapping("/controlled-close-test")
    public void controlledCloseTest(HttpServletResponse response) throws IOException, InterruptedException {
        response.setStatus(HttpServletResponse.SC_OK); // 设置 200 状态

        // 模拟延迟
        Thread.sleep(30000);  // 延迟3秒

        // 模拟非正常连接中断
        response.getOutputStream().close();  // 直接关闭输出流以模拟连接中断
    }
}
