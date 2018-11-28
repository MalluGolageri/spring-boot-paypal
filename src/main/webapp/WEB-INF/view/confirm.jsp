<html>
<body>
<form action="http://localhost:8080/paypal/complete/payment" method="post">
    <input type="hidden" name="paymentId" value='<%= request.getParameter("paymentId") %>' />
    <input type="hidden" name="payerId" value='<%= request.getParameter("PayerID") %>' />
    <input type="hidden" name="amount" value='<%= request.getParameter("amount") %>' />

    paymentId: <%= request.getParameter("paymentId") %> <br/>
    PayerID: <%= request.getParameter("PayerID") %><br/>
    Amount:<%= request.getParameter("amount") %>

<h2> Please Confirm Payment </h2>
&nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="submit" name="submitButton" value="Confirm" />
</form>
</body>
</html>

