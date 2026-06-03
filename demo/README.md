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


