package com.rosetta.onemap.quartz

class LDAPSyncJob {
	
	static triggers = {
		cron name: 'ldapSync', cronExpression: "0 2 * * * ?"
	}

	def group = "LDAPSync"
	def description = "LDAP Sync Job"

	def execute(){
		print "LDAP Sync Job"
	}
}
