<html>
<head><title>Ticket System</title>
<body>
<div id="content">
    <a href="/logout">Logout</a>
    <fieldset>
        <legend>Add User</legend>
        <form name="user" action="/users/add" method="post">
            Name: <input type="text" name="name"/> <br/>
            Email: <input type="text" name="email"/> <br/>
            Password: <input type="text" name="password"/> <br/>
            <input type="submit" value="   Add   "/>
        </form>
    </fieldset>
    <fieldset>
        <legend>Find User By Name</legend>
        <form name="user" action="/users/get" method="get">
            <input type="hidden" name="by" value="name"/>
            Name: <input type="text" name="value"/> <br/>
            <input type="submit" value="   Find   "/>
        </form>
        <br/>
        <#if user??>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </#if>
    </fieldset>
    <fieldset>
        <legend>Find User By Email</legend>
        <form name="user" action="/users/get" method="get">
            <input type="hidden" name="by" value="email"/>
            Email: <input type="text" name="value"/> <br/>
            <input type="submit" value="   Find   "/>
        </form>
        <br/>
        <#if user??>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </#if>
    </fieldset>
    <fieldset>
        <legend>Book Ticket</legend>
        <form name="user" action="/users/tickets/book" method="post">
            User: <input type="text" name="name"/> <br/>
            ID: <input type="text" name="ticketID"/> <br/>
            <input type="submit" value="   Book   "/>
        </form>
    </fieldset>
    <fieldset>
        <legend>Get User Tickets</legend>
        <form name="user" content="" action="/users/tickets/get" method="get">
            Name: <input type="text" name="name"/> <br/>
            <input type="submit" value="   Get User Tickets   "/>
        </form>
        <br/>
        <table class="datatable">
            <#if ticketList??>
            <tr>
                <th>Name</th>
                <th>Date</th>
            </tr>
            <#list ticketList as ticket>
            <tr>
                <td>${ticket.name}</td>
                <td>${ticket.date}</td>
            </tr>
        </#list>
    </#if>
    </table>
    </fieldset>
    <fieldset>
        <legend>Get Booked Tickets</legend>
        <form name="user" content="" action="users/tickets/booked/get" method="get">
            <input type="submit" value="   Get Booked Tickets   "/>
        </form>
        <br/>
        <table class="datatable">
            <#if bticketList??>
            <tr>
                <th>Name</th>
                <th>Date</th>
            </tr>
            <#list bticketList as ticket>
            <tr>
                <td>${ticket.name}</td>
                <td>${ticket.date}</td>
            </tr>
        </#list>
    </#if>
    </table>
    </fieldset>

    <fieldset>
        <legend>Upload Tickets</legend>
        <form method="post" enctype="multipart/form-data" action="/uploadData">
            Upload File: <input type="file" name="file">
            <br/><br/><input type="submit" value="Upload Tickets File">
        </form>
    </fieldset>

    <fieldset>
        <legend>Get Tickets By User PDF</legend>
        <form name="user" enctype="application/pdf" action="/tickets/pdf/user" method="get">
            User: <input type="text" name="name"/> <br/>
            <input type="submit" value="   Get Tickets By User   "/>
        </form>
    </fieldset>

    <fieldset>
        <legend>Get Tickets By Event PDF</legend>
        <form name="user" enctype="application/json" action="/tickets/pdf/event" method="get">
            Event: <input type="text" name="name"/> <br/>
            <input type="submit" value="   Get Tickets By Event   "/>
        </form>
    </fieldset>
</div>
</body>
</html>