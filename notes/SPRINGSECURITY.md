## Spring Security Notes

* Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder which required plain text passwords. 
In Spring Security 5, the default is DelegatingPasswordEncoder, which required Password Storage Format.

    If you see the following error 
    ```
    There is no PasswordEncoder mapped for the id “null”
    ```
    It means you are storing password as plain text without proper storage format
    
    See more info at this [link](https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-format)
    and this [link](https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-format)
    
    
* Browser will still pop basic authentication dialog for user and password even if I
have `ServerHttpSecurity` set up. This is because in my templates I made http requests
to `/user` with a  "WWW-Authenticate" header. I need to find a way suppress the headers.