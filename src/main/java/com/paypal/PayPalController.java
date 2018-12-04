package com.paypal;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.model.UpdatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        String redirectUrl = (String) resp.get("redirectURI");
        System.out.println(redirectUrl);
        servletResponse.sendRedirect(redirectUrl);
        return resp;
    }

    @RequestMapping(value = "/complete/payment", method = RequestMethod.POST)
    public Payment completePayment(HttpServletRequest request, HttpServletResponse servletResponse,
                                   @RequestParam("paymentId") String paymentId,
                                   @RequestParam("payerId") String payerId,
                                   @RequestParam("amount") String amount) throws IOException {
        System.out.println("completing payment, paymentId:" + paymentId + " payerId:" + payerId);
        if (StringUtils.isEmpty(paymentId)) paymentId = request.getParameter("paymentId");
        if (StringUtils.isEmpty(payerId)) payerId = request.getParameter("PayerID");
        if (StringUtils.isEmpty(amount)) amount = request.getParameter("amount");
        //TODO handle exception
        Double total = Double.parseDouble(amount);
        Payment payment = payPalClient.completePayment(paymentId, payerId);
        if (payment != null) {
            System.out.println("==Request completed successfully==");
            servletResponse.sendRedirect("http://localhost:8080/paypal/success?amount=" + total);
        }
        return payment;
    }

//    @RequestMapping(value = "/update/payment",method = RequestMethod.PATCH)
//    public Map<String, Object> updatePayment(HttpServletRequest request,HttpServletResponse response,
//                                             @RequestBody UpdatePaymentRequest updatePaymentRequest,
//                                             @RequestParam("paymentId") String paymentId) throws IOException, PayPalRESTException {
//        Map<String, Object> resp = payPalClient.updatePayment(paymentId, updatePaymentRequest);
//        String redirectUrl = (String) resp.get("redirectURI");
//        System.out.println(redirectUrl);
//        response.sendRedirect(redirectUrl);
//        return resp;
//
//    }



    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirm() {
        return new ModelAndView("/WEB-INF/view/confirm.jsp");
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ModelAndView success(HttpServletRequest request) {
        return new ModelAndView("/WEB-INF/view/success.jsp");
    }

    @RequestMapping("/")
    public String hello() {
        return "/WEB-INF/view/paypal.jsp";
    }


}
