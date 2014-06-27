<g:if test="${searchResults != null}">
	<g:each var="result" in="${searchResults}">
		<div class="result-item">
			<div class="name">${result?.name}</div>
			<c:if test="${result?.position != null}">
				<div class="position">${result?.position}</div>
			</c:if>
			<c:if test="${result?.location != null}">
				<div class="location">${result?.location}</div>
			</c:if>
		</div>
	</g:each>
</g:if>