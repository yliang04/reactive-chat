# RABBITMQ
##### To start the broker
* go to `C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.8\sbin`
* run rabbitmq-server.bat 

##### To control the broker
* Use rabbitmqctl.bat

##### To Visualize RabbitMQ broker
* Run rabbitmq-plugins enable rabbitmq_management
* Visit http://localhost:15672
* The default username/password is `guest/guest`

## NOTE
* The reason why we want to use RabbitMQ is that we want to be able to scale easily later
on. Imagine that this application has millions of active users. It is not going to be
feasible to use only one java program to handle both reading and writing comments. We need
to be able to use a message queue to deliver tasks to different programs on different 
machines.

* When using message queue service like RabbitMQ, it is necessary to provide a
`MessageConverter` bean to serialize objects(messages). In the case of
 using the `spring-boot-starter-amqp` package, we will register a bean to
 provide a `Jackson2JsonMessageConverter`. See book at page 179.
 
 * gradle build
```
implementation 'org.springframework.boot:spring-boot-starter-amqp'
```