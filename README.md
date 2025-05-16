# Fintech API

A robust Spring Boot-based fintech application that provides secure financial services including user management, wallet operations, and KYC verification.

## Features

- 🔐 Secure Authentication & Authorization
- 👤 User Management
- 📍 Address Management
- 📝 KYC Verification
- 💳 Wallet Management
- 💱 Currency Management
- 🔒 Transaction PIN Security
- ✉️ Email Verification

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- JWT Authentication
- MySQL 8
- JPA/Hibernate
- Maven
- Lombok
- ModelMapper

## Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher
- MySQL 8 or higher
- IDE (IntelliJ IDEA recommended)

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/yourusername/fintech.git
cd fintech
```

2. Configure the database:

- Create a MySQL database
- Update `application.yml` with your database credentials

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
- `GET /api/v1/auth/me` - Get authenticated user details

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
- `PUT /api/v1/kyc/status` - Update KYC status (ADMIN only)
- `GET /api/v1/kyc/{userId}` - Get KYC details by user ID

### Wallet Management

- `POST /api/v1/wallets` - Create wallet
- `GET /api/v1/wallets` - Get user wallets
- `GET /api/v1/wallets/{userId}` - Get wallet by user ID
- `POST /api/v1/wallets/transfer` - Transfer funds

### Currency Management

- `POST /api/v1/currencies` - Create new currency
- `GET /api/v1/currencies` - Get all currencies
- `GET /api/v1/currencies/{id}` - Get currency by ID
- `PUT /api/v1/currencies/{id}` - Update currency
- `DELETE /api/v1/currencies/{id}` - Delete currency

## Security Features

- JWT-based authentication
- Password encryption
- Transaction PIN protection
- Email verification
- KYC verification
- Role-based access control
- Environment variable configuration

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
- KYC Status (PENDING, APPROVED, REJECTED)
- ID Image URLs (Front and Back)

### Wallet

- Wallet Number
- Name
- Amount
- Currency
- Active Status
- Main Wallet Flag
- Type (client)
- User Reference

### Currency

- Code (e.g., USD, EUR, GBP)
- Name
- Symbol
- Decimal Places
- Active Status

## Environment Variables

The application uses environment variables for sensitive configuration. Create a `.env` file in the project root with the following variables:

```env
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/fintech
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Email Configuration
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_password

# JWT Configuration
JWT_SECRET=your_jwt_secret
JWT_EXPIRATION=86400000

# Server Configuration
SERVER_PORT=8080
SERVER_CONTEXT_PATH=/api/v1

# App Configuration
APP_VERIFICATION_URL=http://localhost:8080/api/v1/auth/verify-email
APP_SECURITY_OTP_EXPIRATION=300
APP_TRANSACTION_PIN_LENGTH=6
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Afolabi Oluwasegun - [@lekksz](https://twitter.com/lekksz)
Project Link: [https://github.com/lekkss/fintech](https://github.com/lekkss/fintech)
