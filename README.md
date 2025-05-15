# Fintech API

A robust Spring Boot-based fintech application that provides secure financial services including user management, wallet operations, and KYC verification.

## Features

- 🔐 Secure Authentication & Authorization
- 👤 User Management
- 📍 Address Management
- 📝 KYC Verification
- 💳 Wallet Management
- 🔒 Transaction PIN Security
- ✉️ Email Verification

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- JWT Authentication
- PostgreSQL
- JPA/Hibernate
- Maven
- Lombok
- ModelMapper

## Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher
- IDE (IntelliJ IDEA recommended)

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/yourusername/fintech.git
cd fintech
```

2. Configure the database:

- Create a PostgreSQL database
- Update `application.properties` with your database credentials

3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication

- `POST /api/v1/auth/register` - Register a new user
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/verify-email` - Verify user email

### User Management

- `GET /api/v1/users/profile` - Get user profile
- `PUT /api/v1/users/profile` - Update user profile
- `POST /api/v1/users/transaction-pin` - Set transaction PIN

### Address Management

- `POST /api/v1/addresses` - Create address
- `GET /api/v1/addresses/{id}` - Get address
- `PUT /api/v1/addresses/{id}` - Update address
- `DELETE /api/v1/addresses/{id}` - Delete address

### KYC Management

- `POST /api/v1/kyc` - Submit KYC details
- `GET /api/v1/kyc` - Get KYC status
- `PUT /api/v1/kyc` - Update KYC details

### Wallet Management

- `POST /api/v1/wallets` - Create wallet
- `GET /api/v1/wallets` - Get user wallets
- `POST /api/v1/wallets/transfer` - Transfer funds

## Security Features

- JWT-based authentication
- Password encryption
- Transaction PIN protection
- Email verification
- KYC verification
- Role-based access control

## Data Models

### User

- Basic user information
- Email verification status
- Profile completion status
- Transaction PIN status

### Address

- Street
- City
- State
- Country
- Postal Code

### KYC Details

- ID Type (Passport, Driver's License, etc.)
- ID Number
- Date of Birth
- ID Issuing Country
- ID Expiry Date
- KYC Status

### Wallet

- Balance
- Currency
- Transaction History

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Your Name - [@yourtwitter](https://twitter.com/yourtwitter)
Project Link: [https://github.com/yourusername/fintech](https://github.com/yourusername/fintech)
