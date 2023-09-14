# Healand Project

- API server (this)
- web-apply : Page used before app launch (sign up, log in, check coin information)
- app : Blockchain management app
- admin page

---

# Healand-Server

---

## Environment

### Enviroment

- Java 11
- Spring framework boot 2.7.7
- swagger 3.0
- logback 1.2.9
- amazone sdk 2.17.75

### IDE

- IntelliJ

---

## Deployment

### AWS

- Seoul Region
  - ec2 + elasticIP
- Tokyo Region
  - SMS

---

## How to run

### Development

Linux cli

```jsx
$ cd [PROJECT_JAR_DIR_PATH]
$ java -Dspring.profiles.active=[yaml] -jar [.jar file name] &
```

### Build

Linux cli

```jsx
$ cd [PROECT_ROOT]
$ chmod +x gradlew
```

---

## Git

### Branch

- **main**
  - Production (real server) distribution branch
-**stage**
  - Staging (test server) distribution branch
- **dev**
  - Development (development, local server) branch for testing and application of developments

By default, all modifications are done in dev and dev sub-branches.

Organize what needs to be distributed, merge it into the master and staging branches, and then deploy.

Ignore configuration (.yaml) files.

### Add yaml file

1. Add yaml file (application-{coinName})
2. Change yaml port

### Add Log settings

1. Add <springProflie> to logback-spring.xml

---

## Structure

### Controller

- Received through Dao and delivered to Service
- Normally, the transformation from Entity to Dao is done by the Controller.

### Service

- Responsible for complex business logic
- Typically, a Service can have multiple DaoServices, but cannot have other Services.
- Cannot have a repository

### DaoService

- Responsible for the most basic logic that goes directly to the DB

### Exception

- Commonly handled in CustomResponseStatusException

---

## Concept

### Member

- Customers (investors) of the system

### Wallet

- General wallet: Wallet containing actual coins
- Lockup wallet: A wallet that does not contain actual coins and exists only as data

### History

- Record coin changes in the wallet with the wallet records
- Applies to both regular wallets and locked-up wallets

### Transfer

- Transaction records of actual coins coming and going
- Only applies to general wallets because it only records records of actual coin changes.
