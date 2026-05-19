# TrustChain

Transparent Charity Donation Platform powered by Merkle Tree verification.

## 1. Overview

TrustChain is a transparency-focused charity donation management system designed to improve public trust in online fundraising platforms.

The project uses Merkle Tree structures and SHA-256 hashing to provide:

- Tamper-resistant donation records
- Verifiable data integrity
- Transparent fundraising processes
- Traceable project progress

The goal is to solve common issues in traditional charity systems such as:

- Lack of transparency
- Unclear fund usage
- Centralized data manipulation risks
- Weak auditability

---

## 2. Core Features

- Donation record hashing
- Merkle Tree verification
- Fund flow traceability
- Project lifecycle management
- Public verification interface
- Multi-role permission system
- Responsive web platform

---

## 3. How It Works

Each donation record is converted into a unique hash and stored as a leaf node in a Merkle Tree.

![Merkel tree.png](README.ass/Merkel%20tree.png)

The generated Merkle Root can be used to verify whether donation data has been modified.

This ensures transparency and integrity without exposing the full database.

---

## 4. Tech Stack

- Backend: Spring Boot, MyBatis-Plus
- Database: MySQL
- Frontend: Vue2 + ElementUI
- Build Tool: Maven Wrapper (`mvnw.cmd`)
- Hash Algorithm: SHA-256

## 5. Build and Run

### 5.1 Environment Requirements

- JDK 8+
- MySQL 5.7/8.0

### 5.2 Configuration

Edit:
- `src/main/resources/application.yml`

Set your database connection:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

### 5.3 Execute SQL

Import:
- `db/springboot51ug89lx.sql`

### 5.4 Compile and Run

Windows:

```bash
.\mvnw.cmd -DskipTests compile
.\mvnw.cmd spring-boot:run
```

## 6. Verification Workflow

1. Start an online donation from the project details page.
2. Complete payment on the payment page.
3. Wait for the scheduled task (or trigger it manually) to build the Merkle batch.
4. Open the donation details and download the proof.
5. Go to the verification page and confirm the result is "Verification Passed".

---

## 7. Suggested Thesis Materials

For further thesis support based on this implementation, you can add:
- System architecture diagram
- Sequence diagram (Payment -> Evidence Recording -> Verification)
- Security analysis (tamper resistance, auditability, limitations)
