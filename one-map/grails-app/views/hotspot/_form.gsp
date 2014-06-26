<%@ page import="com.rosetta.onemap.Hotspot" %>



<div class="fieldcontain ${hasErrors(bean: hotspotInstance, field: 'polygon', 'error')} required">
	<label for="polygon">
		<g:message code="hotspot.polygon.label" default="Polygon" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="polygon" required="" value="${hotspotInstance?.polygon}"/>
</div>

