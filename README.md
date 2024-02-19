QR Code Generation Project
This project provides a service for generating QR codes based on input data. It utilizes Spring Boot for backend functionality and provides an API for generating QR codes dynamically.

Project Structure
The project is structured as follows:

com.vladislav.app.rest.model: Contains the model classes used in the application.

DecodedQrRequest.java: Represents the request for decoding a QR code.
DecodedQrResponse.java: Represents the response for decoding a QR code.
GenerateQrRequest.java: Represents the request for generating a QR code.
com.vladislav.app.rest.resource: Contains the REST controllers for handling API endpoints.
QrCodeResource.java: REST controller for generating and decoding QR codes.
com.vladislav.app.rest.service: Contains the service classes responsible for business logic.
QrCodeService.java: Service class responsible for generating and decoding QR codes.
com.vladislav.app.rest: Contains the main application class.
QrCodeGeneratorApplication.java: Main class to run the Spring Boot application.
resources: Contains application properties and other resource files.

Dependencies
Spring Boot: For building the application.
Spring Web: For building RESTful APIs.
ZXing: A library for generating QR codes.
Java Logging API: For logging information.
How to Run the Project
Clone the repository: git clone https://github.com/vladik095/JAVA.git
Build the project: mvn clean install
Run the application: mvn spring-boot:run
The application will start, and you can access the API at localhost:8080/api/qr/generate?qrString=your_information_for_qrcode

Generate a QR code using URL
curl "http://localhost:8080/api/qr/generate?qrString=your_information_for_qrcode"

Generate a QR code using JSON payload
curl -X POST -H "Content-Type: application/json" -d '{"qrString":"your_information_for_qrcode"}' http://localhost:8080/api/qr/generate

Decode information from a QR code
curl -X POST -F "qrCode=@path/to/qr-code-image.png" http://localhost:8080/api/qr/decode

