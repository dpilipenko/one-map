package com.rosetta.onemap.quartz

import com.rosetta.onemap.Office;

class QueryDBJob {
	static triggers = {
		cron name: 'queryDB', cronExpression: "0 2 * * * ?"
	}
	def group = "QueryDB"
	def description = "Scheduled job that makes a DB call. This is to prevent DB connection pool timeouts"
	def execute () {
		print "... QueryDB - There are currently " + Office.count() + " Office(s) ..."
	}
}