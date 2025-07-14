 J-ai Chatbot Application🌼

This is a full-stack chatbot web application named **J-ai** built using:
- Angular (standalone components)** for the frontend
- Spring Boot for the backend
- MySQL database
- RESTful APIs for chat, user, and message interaction

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Features

-User Login & Registration  
-Start New Chat  
-Chat History Panel (like ChatGPT)  
-Send and Receive Messages (with basic auto-reply)  
-Edit and Delete Chats and Messages  
-Dark/Light Theme Toggle  
-Navigation Bar with Pages: About, Settings, User Info

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Backend Setup (Spring Boot)

 Folder Structure:
 src/
├── main/
│ ├── java/com/example/jaiBackend2/
│ │ ├── Controller/
│ │ │ ├── AuthController.java
│ │ │ ├── ChatController.java
│ │ │ └── MessageController.java
│ │ ├── Entity/
│ │ ├── Repository/
│ │ ├── dto/
│ │ └── JaiBackend2Application.java
│ └── resources/
│ ├── application.properties

Ensure your application.properties looks like this:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/jai_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Frontend Setup (Angular)
Folder Structure
pgsql
Copy
Edit
src/
├── app/
│   ├── pages/
│   │   ├── login/
│   │   ├── chat/
│   │   ├── navbar/
│   │   ├── settings/
│   │   ├── about/
│   │   └── user/
│   ├── services/
│   │   ├── auth.ts
│   │   ├── chat.ts
│   │   └── message.ts
│   ├── app.routes.ts
│   ├── app.config.ts
│   └── app.ts

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Install Dependencies
-npm install
-Make sure zone.js is installed (required by Angular):
-npm install zone.js

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Run Angular Frontend

API Endpoints
Auth APIs
Endpoint	Method	Description
/api/auth/register	POST	Register new user
/login	POST	Login user (form-encoded)
/api/auth/me	GET	Get current user info

Chat APIs
Endpoint	Method	Description
/api/chat	GET	Get all chats
/api/chat/new	POST	Create new chat
/api/chat/{id}	DELETE	Delete chat

Message APIs
Endpoint	Method	Description
/api/message/send	POST	Send a message
/api/message/{chatId}	GET	Get messages
/api/message/{id}	PUT	Update a message

API testing:
<img width="526" height="552" alt="image" src="https://github.com/user-attachments/assets/54997c97-d36b-4b2d-8a23-d88e96a79718" />
<img width="529" height="556" alt="image" src="https://github.com/user-attachments/assets/f2477e92-a70e-485b-a241-0ab518285f28" />
<img width="524" height="558" alt="image" src="https://github.com/user-attachments/assets/8ecee3a9-0495-446a-bbf9-9e4e4eb54b90" />
<img width="528" height="553" alt="image" src="https://github.com/user-attachments/assets/d5764635-cb26-4a48-9059-2e5635543692" />
<img width="515" height="554" alt="image" src="https://github.com/user-attachments/assets/d5e8427c-29e3-4652-a848-1da7ebe8ff58" />
<img width="541" height="556" alt="image" src="https://github.com/user-attachments/assets/bce01e2b-ee64-49fb-8b35-ed965d38f7b0" />


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CORS & Proxy Configuration
In Angular root, create proxy.conf.json:

{
  "/api": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true,
    "withCredentials": true
  },
  "/login": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true,
    "withCredentials": true
  }
}
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Run
ng serve --proxy-config proxy.conf.json
