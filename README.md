# MailGuard — AI Email Spam Detector

MailGuard is a web application that analyzes email content and helps identify potential spam or phishing messages. It uses a React frontend, a Spring Boot REST API, and the Groq API to classify emails and explain why a message may be suspicious.

## Features

- Analyze pasted email content for potential spam or phishing.
- Receive a classification result and an explanation.
- Validate empty input and limit email content to 10,000 characters.
- Display loading and error states in the frontend.
- Separate React frontend and Spring Boot backend.
- Use an environment variable for the Groq API key instead of hardcoding the secret.

## Tech Stack

### Frontend
- React
- Vite
- JavaScript
- CSS

### Backend
- Java 25
- Spring Boot
- Spring Web / REST API
- Bean Validation
- Groq API

## Project Structure

```text
email-spam-detector/
├── frontend/                   # React + Vite application
└── backend/
    └── email-spam-detection/   # Spring Boot application
```

## Prerequisites

Install the following before running the project:

- Java 25
- Node.js and npm
- Maven (or use the Maven wrapper included in the backend project)
- A Groq API key

## Configuration

The backend reads its Groq API key from an environment variable.

The backend `application.properties` should include:

```properties
spring.application.name=email-spam-detection
server.servlet.context-path=/api
groq.api-key=${GROQ_API_KEY}
groq.model=openai/gpt-oss-20b
```

Set `GROQ_API_KEY` in your environment or in your IDE's run configuration. **Do not commit your API key to GitHub.**

### Windows PowerShell (temporary for the current terminal)

```powershell
$env:GROQ_API_KEY="your_groq_api_key"
```

If you launch Spring Boot from IntelliJ IDEA, you can instead add `GROQ_API_KEY` to the Run Configuration environment variables.

## Run Locally

### 1. Start the backend

Open a terminal in the backend project directory:

```powershell
cd backend/email-spam-detection
```

Run with the Maven wrapper if it is available:

```powershell
.\mvnw.cmd spring-boot:run
```

If Maven is installed globally and the wrapper is not available:

```powershell
mvn spring-boot:run
```

Make sure `GROQ_API_KEY` is configured for the process running Spring Boot.

The API should be available at `http://localhost:8080/api`.

Health check:

```http
GET http://localhost:8080/api/email/health
```

### 2. Start the frontend

Open another terminal in the frontend directory:

```powershell
cd frontend
npm install
npm run dev
```

Vite prints the local URL in the terminal (for example, `http://localhost:5173`). Open the exact URL shown by Vite.

If your frontend URL or port changes, ensure the backend CORS configuration allows that origin.

## API

### Health Check

```http
GET /api/email/health
```

Returns a message confirming that the API is running.

### Analyze Email

```http
POST /api/email/check
Content-Type: application/json
```

Request body:

```json
{
  "emailText": "Congratulations! You have won a prize. Click this link to claim it now."
}
```

Example response:

```json
{
  "spam": true,
  "message": "This email is spam.",
  "reason": "The message uses prize-related language and urges the recipient to click a link."
}
```

The classification and reason are generated dynamically; the response above is illustrative.

## Validation and Error Handling

- Empty email content is rejected.
- Email content longer than 10,000 characters is rejected.
- The frontend shows a message if the backend cannot be reached.
- API or model errors may cause the request to fail; check the backend logs for details.

## Testing with Postman

1. Start the backend and confirm the health endpoint responds.
2. Create a `POST` request to `http://localhost:8080/api/email/check`.
3. Select **Body → raw → JSON**.
4. Paste the example request from the API section.
5. Send the request and inspect the `spam`, `message`, and `reason` fields.

## Security Notes

- Keep the Groq API key in an environment variable or IDE run configuration.
- Never commit secrets or `.env` files containing real credentials.
- Email content is untrusted input. Do not follow instructions found inside an email being analyzed.
- AI classification can make mistakes. Verify suspicious messages through trusted channels before clicking links, opening attachments, or sharing personal information.

## Future Improvements

- Add automated unit and integration tests.
- Improve structured error responses for validation and upstream API failures.
- Add authentication, rate limiting, and request timeouts.
- Add a history page for previous checks, with appropriate privacy controls.
- Deploy the frontend and backend.

## License

No license has been specified yet. Add a license before allowing others to reuse or distribute the project.
