<%@ page import="com.rosetta.onemap.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="60" required="" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="60" required="" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'level', 'error')} ">
	<label for="level">
		<g:message code="user.level.label" default="Level" />
		
	</label>
	<g:textField name="level" value="${userInstance?.level}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'craft', 'error')} ">
	<label for="craft">
		<g:message code="user.craft.label" default="Craft" />
		
	</label>
	<g:textField name="craft" value="${userInstance?.craft}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="user.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="phone" type="number" value="${userInstance.phone}" required=""/>
</div>

