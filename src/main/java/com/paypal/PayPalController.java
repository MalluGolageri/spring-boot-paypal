package com.paypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "/paypal")
public class PayPalController {

    private final PayPalClient payPalClient;

    @Autowired
    PayPalController(PayPalClient payPalClient) {
        this.payPalClient = payPalClient;
    }

    @RequestMapping(value = "/make/payment", method = RequestMethod.POST)
    public Map<String, Object> makePayment(HttpServletRequest servletRequest, HttpServletResponse servletResponse, @RequestParam("sum") String sum) throws IOException {
        Map<String, Object> resp = payPalClient.createPayment(sum);
        String redirectUrl = (String) resp.get("redirect_url");

        System.out.println(redirectUrl);
        servletResponse.sendRedirect(redirectUrl);
        return resp;
    }

    @RequestMapping(value = "/complete/payment", method = RequestMethod.POST)
    public Map<String, Object> completePayment(HttpServletRequest request, HttpServletResponse servletResponse, @RequestParam("paymentId") String paymentId,
                                               @RequestParam("payerId") String payerId) throws IOException, ServletException {
        System.out.println("completing payment, paymentId:" + paymentId + " payerId:" + payerId);
        if (StringUtils.isEmpty(paymentId)) paymentId = request.getParameter("paymentId");
        if (StringUtils.isEmpty(payerId)) payerId = request.getParameter("PayerID");
        Map<String, Object> resp = payPalClient.completePayment(paymentId, payerId);
        if (!CollectionUtils.isEmpty(resp) && "success".equalsIgnoreCase((String) resp.get("status"))) {
            System.out.println("==Request completed successfully==");
            servletResponse.sendRedirect("http://localhost:8080/paypal/success");
        }
        return resp;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirm() {
        return new ModelAndView("/WEB-INF/view/confirm.jsp");
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ModelAndView success() {
        return new ModelAndView("/WEB-INF/view/success.jsp");
    }

    @RequestMapping("/")
    public String hello() {
        return "/WEB-INF/view/paypal.jsp";
    }


}
