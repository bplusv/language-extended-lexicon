# LEL (Language Extended Lexicon)
LEL is an app to store and analyze the extended lexicon for software requirements.

## Requirements
- This project is a Java EE Web application
- The deploy server is GlassFish, it’s configured for default on NetBeans.
- EclipseLink JPA 2.1 is used for persistence.

## Setup
1. Install MySQL Server.
3. Install NetBeans IDE with Java EE (includes GlassFish).
4. Run the dev/Database/Lel_DB_Schema.sql SQL script.
5. Run the dev/data-dumps/Dump20121024.sql data dump.
6. Create the ‘lel_user’ mysql user and give it access to the lel DB.
7. SetUp the connection Pool & Connection on the GlassFish Server.
8. Start the GlassFish server and load the website.
