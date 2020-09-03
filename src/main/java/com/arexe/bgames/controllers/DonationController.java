package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.User;
import com.arexe.bgames.payu.order.*;
import com.arexe.bgames.service.UserService;
import com.arexe.bgames.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

import static com.arexe.bgames.payu.order.OrderResponse.Status.STATUS_CODE_SUCCESS;

@Controller
public class DonationController {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${payu.clientId}")
    private String clientId; //merchantPosId

    @Value("${payu.storeName}")
    private String storeName;

    private final OrderService orderService;
    private final UserService userService;
    private final MessageSource messageSource;

    @Autowired
    public DonationController(OrderService orderService, UserService userService, MessageSource messageSource) {
        this.orderService = orderService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping(value = "/donate")
    public String showDonationPage(Model model) {
        final String email = getUserEmail();
        final OrderForm orderForm = new OrderForm();
        orderForm.setEmail(email);
        model.addAttribute("orderForm", orderForm);
        return "donate";
    }

    @PostMapping(value = "/donate-checkout")
    public RedirectView donationCheckout(@ModelAttribute OrderForm orderForm, HttpServletRequest request) {
        final OrderRequest orderRequest = prepareOrderCreateRequest(orderForm, request);
        final OrderResponse response = orderService.createOrder(orderRequest);

        if (!response.getStatus().getStatusCode().equals(STATUS_CODE_SUCCESS)) {
            throw new RuntimeException("PayU payment error");
        }

        return new RedirectView(response.getRedirectUri());
    }

    @GetMapping(value = "/donate-payment")
    public String paymentFinish(final @RequestParam Optional<String> error, Model model,  Locale locale) {
        if (error.isPresent()) {
            model.addAttribute("message", messageSource.getMessage("payment.error", null, locale));
        } else {
            model.addAttribute("message", messageSource.getMessage("payment.success", null, locale));
        }
        return "donate-payment";
    }

    private OrderRequest prepareOrderCreateRequest(final OrderForm orderForm, final HttpServletRequest request) {
        return OrderRequest.builder()
                .customerIp(request.getRemoteAddr())
                .continueUrl(serverUrl + "donate-payment")
                .merchantPosId(clientId)
                .description(storeName)
                .currencyCode("PLN")
                .totalAmount(orderForm.getAmount())
                .products(Collections.singletonList(Product.builder()
                        .name("Donate")
                        .quantity("1")
                        .unitPrice(orderForm.getAmount())
                        .build())
                ).buyer(Buyer.builder()
                        .email(orderForm.getEmail())
                        .language("en")
                        .build()
                ).build();
    }

    private String getUserEmail() {
        String email = "";
        final Optional<User> loggedUser = getLoggedUser();
        if (loggedUser.isPresent()) email = loggedUser.get().getEmail();
        return email;
    }

    private Optional<User> getLoggedUser() {
        String loggedUser = UserUtils.getLoggedUser();
        return Optional.ofNullable(userService.findUserByEmail(loggedUser));
    }

}