package com.rosetta.onemap.ldap

import grails.plugin.springsecurity.SpringSecurityUtils

class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// xTODO put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
