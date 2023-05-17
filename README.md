Moikiitos 
=========
This is a simple Microblogging web application. Any resemblance to existing internet services is coincidental.

This project uses [Spring MVC], [Spring Data JPA], [Spring Security], and [Apache Tiles].

Copyright &copy; 2023-, Peng Minghua.  Licensed under [Apache License, Version 2.0].


Prerequisites
=============
This project requires a [Java Development Kit] v11 or newer and [Apache Maven] 3 to compile the source code.


Database Setup
--------------
This webapp requires a database, so setup one and add the following JNDI entry to your servlet container: `jdbc/moikiitos`.

Add your database settings like username & password to this file if using Tomcat: `src/main/webapp/META-INF/context.xml`, copy it under '$TOMCAT_HOME/conf/'.

The schema will be created when the application starts. Or you can import data.sql to your database, you can use `pmh/password` to login.

The app includes database drivers for [MySQL] 8 and uses that dialect by default.


Building
========
First, download the latest and greatest source code:

    git clone git@github.com:pmh905001/moikiitos.git

Build and run the code with the following commands:

    cd moikiitos
    mvn clean install
    mvn tomcat7:run


Then open your browser to this address: [http://localhost:8080/moikiitos/](http://localhost:8080/moikiitos/)

Editing
=======
This project does not require an IDE, but is setup to be edited using an [Eclipse].

1. Create a new workspace
2. click File -> Open Projects from File System... -> Import Source
3. For the root directory, select the moikiitos directory from the git repository you cloned locally.
4. click Finish


Change Log
=======
1. Based & referenced to existed project https://github.com/d-rep/Microblog/
2. Upgrade to java 11 and make it can run.
3. Update context.xml to fix utf8 encoding character & time zone of MySQL.
4. Re-factor code to remove useless log.
5. Update UI layout and wording for all views.
6. Optimized feed list view, e.g hide words if overflow.
7. Support confirmation password and URL to redirect on register view.
8. Support email account to register/login/query and displayed as one column by extends JdbcUserDetailsManager.
9. Add following/followers views & displaying the number by the filter.


To Do List
=======
1. Make feed items can be displayed more text.
2. Some unit tests failed due to java version or incompatibility package.
3. Global exception handler.
4. Upgrade to latest version of Spring & TOMCAT version to fix Fix jaxb-api-2.3.0.jar issue on start service
5. Re-factor code to expose RESTful APIs to support other devices and separating front and back end.
6. Use cache(redis/mq) to improve performance if necessary, e.g number of following/follower, feed list.
