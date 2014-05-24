This is the LEL Project.

Built with the NetBeans IDE, using JAVA EE.


Setup Up project & Configuration:
	1. Install MySQL Server
	2. Install MySQL WorkBench (Recommended)
	3. Install NetBeans IDE with Java EE (includes GlassFish)

The included ‘Dev’ Folder includes some resources for helping you set up the project:

	4. Run the ‘Lel DB Schema’ SQL script to generate the ‘lel’ DB & tables:
	(‘dev/Database/Lel_DB_Schema.sql’)

	5. Run the latest data dump available to populate the DB tables:
	e.g. (‘dev/data-dumps/Dump20121024.sql’)

	6. Create the ‘lel_user’ mysql user and give it access to the lel DB.
		Username: lel_user
		Password: L3lP4ss



	——— If the JDBC resources didn’t deployed automatically ——-

	7. SetUp the connection Pool & Conneciton on the GlassFish Server:
		(view ‘setup/glassfish-resources/xml’ file for details)

		a) Access the GlassFish Admin Panel at: localhost:4848
		b) Create a new ‘Connection Pool’ at JDBC > JDBC Connection Pools:
			Pool Name: lelPool
			Database Driver Vendor: MySQL
			-> Click Next
			Add additional Properties:	
				URL: jdbc:mysql://localhost:3306/lel
        			User: lel_user
        			Password: L3lP4ss
			-> Click Finish

		c) Create a new “JDBC Resource’:
			JNDI Name: jdbc/lel
			Pool Name: lel/pool
			-> Click OK

	8. Open the project on NetBeans IDE and run it =)

Project Requirements:
	1. This project is a Java EE Web application
	2. The deploy server is GlassFish, it’s configured for default on NetBeans.
	2. EclipseLink JPA 2.1 is used for persistence, it’s NetBeans default provider
	(querys are written to use EclipseLink syntax)


If you have any questions, please contact bp.lusv@gmail.com
Thank you.