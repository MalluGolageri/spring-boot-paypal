<html>
<body>
<form action="http://localhost:8080/paypal/complete/payment" method="post">
    <input type="hidden" name="paymentId" value='"<%= request.getParameter("paymentId") %>"' />
    <input type="hidden" name="payerId" value='"<%= request.getParameter("PayerID") %>"' />
    paymentId: <%= request.getParameter("paymentId") %>
    PayerID: <%= request.getParameter("PayerID") %>

<h2> Please Confirm Payment </h2>
&nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="submit" name="submitButton" value="Confirm" />
</form>
</body>
</html>

