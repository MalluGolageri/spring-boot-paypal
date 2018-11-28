<html>
<body>
<form action="http://localhost:8080/paypal/complete/payment" method="post">
    <input type="hidden" name="paymentId" value='<%= request.getParameter("paymentId") %>' />
    <input type="hidden" name="payerId" value='<%= request.getParameter("PayerID") %>' />
    <input type="hidden" name="amount" value='<%= request.getParameter("amount") %>' />

    paymentId: <%= request.getParameter("paymentId") %> <br/>
    PayerID: <%= request.getParameter("PayerID") %><br/>
    Amount:<%= request.getParameter("amount") %> <br/>
<br/> <br/>
<input type="image" src="http://samedayexpress.ca/wp-content/uploads/2014/04/paynow_button.png" height="70" width="200" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>

</body>
</html>

