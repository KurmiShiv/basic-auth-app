# Login & Register Application

A simple login and registration system with **HTML/CSS/JavaScript** frontend and **Java Servlets** backend with PostgreSQL database. **No JSP files needed!**

## Key Features

✅ **Pure HTML/CSS/JavaScript** frontend
✅ **Java Servlets** for backend processing
✅ **PostgreSQL** database integration
✅ **Client-side state management** with sessionStorage
✅ **Modern, responsive UI** with gradient design
✅ **Form validation** - both client and server-side
✅ **Secure database queries** using PreparedStatement

## Prerequisites

- **Java Development Kit (JDK)** - 8 or higher
- **Apache Tomcat** - 10 or higher
- **PostgreSQL** - 9.5 or higher
- **PostgreSQL JDBC Driver (postgresql.jar)**

## Quick Setup Instructions

### 1. Create PostgreSQL Database

```bash
psql -U postgres -f auth_db.sql
```

### 2. Create Dynamic Web Project in Eclipse

- File → New → Dynamic Web Project
- Project name: `AuthApp`
- Target runtime: Apache Tomcat v9.0+

### 3. Add Java Servlets

Add these files to `src/`:
- `LoginServlet.java`
- `RegisterServlet.java`

### 4. Add Web Files

Add these files to `webapp/`:
- `index.html`
- `dashboard.html`
- `styles.css`
- `script.js`

### 5. Add JAR Files

Download and place in `webapp/WEB-INF/lib/`:
- `postgresql-42.x.x.jar` - from https://jdbc.postgresql.org/download.html
- `gson-2.x.x.jar` - from https://mvnrepository.com/artifact/com.google.code.gson/gson

### 6. Update web.xml

Replace `WebContent/WEB-INF/web.xml` with the provided `web.xml` file

### 7. Update Database Credentials

Edit `LoginServlet.java` and `RegisterServlet.java`:
```java
private static final String DB_PASSWORD = "your_postgres_password";
```

### 8. Run on Tomcat

Right-click project → Run As → Run on Server → Select Tomcat

Access: `http://localhost:8080/YourApp/`

## How It Works

### Login Flow
1. User enters email and password on `index.html`
2. JavaScript validates input and sends **AJAX request** to `/login` servlet
3. Servlet queries PostgreSQL database
4. Returns **JSON response** with success/error message
5. If successful → stores user info in `sessionStorage` and redirects to `dashboard.html`
6. Dashboard displays user information from `sessionStorage`

### Register Flow
1. User fills registration form on `index.html`
2. JavaScript validates and sends **AJAX request** to `/register` servlet
3. Servlet checks if email exists, then inserts new user
4. Returns **JSON response**
5. If successful → shows success message and switches to login form

### Logout
- User clicks logout button on dashboard

## API Endpoints

### POST /login
**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response (Success):**
```json
{
  "success": true,
  "message": "Login successful!",
  "userName": "John Doe",
  "userEmail": "john@example.com"
}
```

**Response (Failure):**
```json
{
  "success": false,
  "message": "Invalid email or password!"
}
```

### POST /register
**Request:**
```json
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "confirmPassword": "password123"
}
```

**Response (Success):**
```json
{
  "success": true,
  "message": "Registration successful! You can now login."
}
```

## File Descriptions

### Frontend Files

- **index.html** - Login and registration form page with AJAX form handlers
- **dashboard.html** - User dashboard (HTML, not JSP) - displays after login
- **styles.css** - Responsive styling with gradient design and animations
- **script.js** - AJAX form handling, validation, and state management

### Backend Files

- **LoginServlet.java** - Handles login requests, queries database, returns JSON
- **RegisterServlet.java** - Handles registration, validates email uniqueness, returns JSON
- **web.xml** - Servlet mapping and configuration

### Database

- **auth_db.sql** - PostgreSQL script to create database and users table

## Database Schema

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    fullName VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Sample Data

Default test user (from auth_db.sql):
- **Email:** john@example.com
- **Password:** password123

## Client-Side State Management

User information is stored in browser's `sessionStorage`:
```javascript
sessionStorage.setItem('userName', 'John Doe');
sessionStorage.setItem('userEmail', 'john@example.com');
```

This is cleared on logout. SessionStorage is browser-specific and cleared when the tab closes.

## License

This is a learning/demo project. Feel free to use and modify as needed.

## Happy Coding! 🚀
