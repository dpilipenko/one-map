<g:if test="${searchResults != null}">
	<g:each var="user" in="${searchResults}">
		<div class="result-item">
			<div class="name">${user?.firstName} ${user?.lastName}</div>
			<div class="position">Test</div>
			<div class="location">Test</div>
		</div>
	</g:each>
</g:if>