hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			pooled = true
			driverClassName = "org.h2.Driver"
			username = "sa"
			password = ""
        }
    }
    test {
        dataSource {
            dbCreate = "update"	 
            url = "jdbc:mysql://SXD00HTTP04E.rosetta.com:3306/onemapdev?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
			username = "onemap"
			password = "Rosetta1"
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
			properties {
				maxActive = 50
				maxIdle = 25
				minIdle = 5
				initialSize = 5
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "SELECT 1"
			}
        }
		hibernate {
			show_sql = false
		}
    }
    production {
        dataSource {
            dbCreate = "update"	 
            url = "jdbc:mysql://SXD00HTTP04E.rosetta.com:3306/onemapdev?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true"
			username = "onemap"
			password = "Rosetta1"
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
			properties {
				maxActive = 50
				maxIdle = 25
				minIdle = 5
				initialSize = 5
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "SELECT 1"
			}
        }
		hibernate {
			show_sql = false
		}
    }
}