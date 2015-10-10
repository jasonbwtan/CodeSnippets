## About
This example shows how to serve html and jsp content
Looks like you can only have one internalviewresolver.
JSPs can be resolved directly with the view name in either controller or mvc:view-controller
HTML must be resolved by a controller and forward:static-path defined by mvc:resource mapping

navigate to:
http://localhost:8080/spring/index
http://localhost:8080/home
http://localhost:8080/form

alternatively, set 	<property name="suffix" value=".jsp" /> of "org.springframework.web.servlet.view.InternalResourceViewResolver"
