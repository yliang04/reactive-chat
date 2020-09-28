# NOTE

* Bean should not have the same name, otherwise Sprint Boot is going to
use the later one to override the previous one.
    1. Example: The `InitChapterDatabase` and `InitImageDatabase` class
    used to have the same method `init` which are both `@bean`. When the
    project was compiled,  an error complained that naming conflict was
    detected.
    2. Same example can be seen with `ImageService` and `CommentService`.
    To avoid the problem I had to rename the `setUp()` method to be 
    more specific to the service.

* For some reason I had two instance to `ResourceLoader` in my
`ImageService`, which were detected from beans `gridFSTemplate` and
`webApplicatioNContext`. As a result, Spring Boot cannot autowire the
`ResourceLoader`. I had to work around this by adding `@Qualifier("webApplicationContext")`
to the method signature as I assume that the one from `webApplicationContext`
is the right one.

* HTML only support form method with `get` and `post`. However, you
can use Thymeleaf's `th:method` with `delete` and other non-supported
methods

* For some reason I'm not able to use Spring's `DeleteMapping` with 
the following html snippet. Thymeleaf generated the form with `post`
method. I had to use `PostMapping` instead.
```
<form th:action="@{'/images/' + ${image.name}}" th:method="delete" >
    <input type="submit" value="Delete" />
</form>
```

```
<form method="post" action="/images/learning-spring-boot-cover.jpg">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="Delete">
</form>
```
* `@SpringBootApplication` which contains `@componentScan` needs to 
stay at the top(root) of the project structure so that it can scan
all the subpackages