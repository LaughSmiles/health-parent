package exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//可选参数  controller
@ControllerAdvice(basePackages = "com..controller")
public class BaseExceptionHandler {
    /***
     * 异常处理
     * @param e
     * @return
     */
    //处理异常类型
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
