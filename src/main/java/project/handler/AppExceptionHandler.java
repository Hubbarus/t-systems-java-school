package project.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.exception.AppJsonParseException;
import project.exception.AppQueueException;
import project.exception.IMGUploadException;
import project.exception.NoSuchAddressException;
import project.exception.NoSuchClientException;
import project.exception.NoSuchItemException;

/**
 * Handler for all exceptions in app.
 */
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = IMGUploadException.class)
    public String catchIMGUploadException(Model model, IMGUploadException e) {
        model.addAttribute("exception", e);
        model.addAttribute("msg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(value = NoSuchAddressException.class)
    public String catchNoSuchAddressException(Model model, NoSuchAddressException e) {
        model.addAttribute("exception", e);
        model.addAttribute("msg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(value = NoSuchClientException.class)
    public String catchNoSuchClientException(Model model, NoSuchClientException e) {
        model.addAttribute("exception", e);
        model.addAttribute("msg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(value = NoSuchItemException.class)
    public String catchNoSuchItemException(Model model, NoSuchItemException e) {
        model.addAttribute("exception", e);
        model.addAttribute("msg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(value = AppJsonParseException.class)
    public String catchParseException(Model model, AppJsonParseException e) {
        model.addAttribute("exception", e);
        model.addAttribute("msg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(value = AppQueueException.class)
    public String catchAppQueueException(Model model, AppQueueException e) {
        model.addAttribute("exception", e);
        model.addAttribute("msg", e.getMessage());
        return "error";
    }
}
