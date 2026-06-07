#apk update && apk add openjdk17-jdk

sudo su -
apk update && apk add openjdk21-jdk


### MySql ###

# 1. Update packages and install MariaDB (MySQL drop-in replacement)
sudo apk update && sudo apk add mariadb mariadb-client

# 2. Initialize the data directory (Required for Alpine)
sudo mysql_install_db --user=mysql --datadir=/var/lib/mysql

# 3. Start the service
# sudo rc-service mariadb start || mariadb-service start
# error

sudo mkdir -p /run/mysqld && sudo chown -R mysql:mysql /run/mysqld


sudo mariadbd-safe --user=mysql --datadir=/var/lib/mysql &

sudo mysql -u root
rootpassword

MariaDB [(none)]> ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password USING PASSWORD('rootpassword');

MariaDB [(none)]> CREATE DATABASE springboot_db;

MariaDB [(none)]> FLUSH PRIVILEGES;

MariaDB [(none)]> EXIT;

mysql -u root -p rootpassword

# Start MySQL Enabling Networking.
sudo nohup mariadbd --datadir=/var/lib/mysql --user=mysql --skip-grant-tables --skip-networking=0 --port=3306 > /tmp/mysql.log 2>&1 &


#Department add json payload

{
  "deptName": "Engineering"
}

#employee add json payload

{
  "firstName": "Alex",
  "lastName": "Morgan",
  "email": "alex.morgan@example.com",
  "deptId": 1
}

Testing curl 

curl -X POST http://localhost:8080/api/departments \
     -H "Content-Type: application/json" \
     -d '{"deptName": "Engineering"}'


curl -X POST http://localhost:8080/api/employees \
     -H "Content-Type: application/json" \
     -d '{"firstName": "Alex", "lastName": "Morgan", "email": "alex.morgan@example.com", "deptId": 1}'


# Run Command
/usr/bin/env /usr/lib/jvm/java-21-openjdk/bin/java @/tmp/cp_69zufz63ux7qfyu3rl57yfjth.argfile com.spring.boot.rest.demo.DemoApplication 


# Debug Command
/usr/bin/env /usr/lib/jvm/java-21-openjdk/bin/java -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:40819 @/tmp/cp_69zufz63ux7qfyu3rl57yfjth.argfile com.spring.boot.rest.demo.DemoApplication


# URL get

https://expert-telegram-jrjqv959v549c549g-8080.app.github.dev/api/employees/3

https://expert-telegram-jrjqv959v549c549g-8080.app.github.dev/api/departments/1

# Extentions:
1. Extenstion Pack for Java
2. Spring Boot Extension Pack


# Node Manual Install
-----------------------------------------------------------------------
sudo su -
codespaces-32d812:/workspaces/springbt-rest# apk update && apk upgrade

# Installs the current stable version available in Alpine's repository
apk add --no-cache nodejs npm
# Verify Npm Node
node -v
npm -v

# React
# Using Vite (Recommended for speed)
> npm create vite@latest my-react-app -- --template react
  # Use code with caution.Using Next.js (For full-stack applications)
  # > create-next-app@latest my-react-app

# Angular
> sudo su -
> npm install -g @angular/cli

> ng new angular-app

> cd my-angular-app
    -- ng serve --host 0.0.0.0
    -- ng serve --host 0.0.0.0 --port 4200 --disable-host-check
> ng serve 