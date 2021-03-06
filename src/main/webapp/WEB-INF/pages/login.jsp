<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h2>Sign In</h2>

    <!-- Путь к фильтру аутентификации -->
    <spring:url var="authUrl" value="/static/j_spring_security_check" />

        <form method="post" class="signin" action="${authUrl}">
            <fieldset>

                <table cellspacing="0">
                    <tr >
                        <th><label for="username_or_email">Username or Email</label></th>
                        <td><input id="username_or_email"
                                   name="j_username"
                                   type="text"/> <!-- Поле ввода имени пользователя -->
                        </td>
                    </tr>
                    <tr>
                        <th><label for="password">Password</label></th>
                        <td><input id="password"
                                   name="j_password"
                                   type="password"/> <!-- Поле ввода пароля -->
                        </td>
                    </tr>

                    <tr>
                        <th></th>
                        <td><input name="commit" type="submit" value="Sign In"/></td>
                    </tr>
                </table>

            </fieldset>
        </form>

    <script type="text/javascript">
        document.getElementById('username_or_email').focus();
    </script>
</div>