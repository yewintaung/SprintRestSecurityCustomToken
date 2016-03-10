# SprintRestSecurityCustomToken

Secure Rest API Endpoint by custom token

For authentication <br/>
http://localhost:8080/SpringRestSecurityCustomToken/auth/login<br/>
POST {"email":"admin@mail.com", "password":"admin"}<br/>

Token will be returned and send it in Header (key as X-Auth-Token) for callback request.<br/>

There are three roles ROLE_ADMIN, ROLE_EDITOR, ROLE_USER<br/>

"/protect/user" can accessible whatever role it has<br/>
"/protect/editor" can accessible ROLE_ADMIN and ROLE_EDITOR<br/>
"/protect/admin" can only accessible by ROLE_ADMIN<br/>

Make sure token was expired. To do for that send it to "/auth/renew" with an unexpired token and new token will be returned.
http://localhost:8080/SpringRestSecurityCustomToken/auth/renew<br/>
Header : X-Auth-Token:user@mail.com:1457598200273:f796ae47d662f0c6e7bb4b25f120d3e3

If it access with unauthoried role, AccessDeniedException will be fired<br/>
With wrong username and password, BadCredentialsException will be fired
