# iEstoque API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


This project is an api made in Java, Java Spring, Migrations, MySLQ as the database and SpringSecurity with JWT as the authentication control.

I noticed that many companies still use pen and paper to record information such as product quantities in stock, expiration dates, batch numbers, and other crucial details.

 This outdated practice not only harms the environment but also hinders the technological progress of these companies. To address this issue, I'm developing the iEstoque. 

# Contents
[Installation](#installation) <br>
[Usage](#usage)<br>
[Endpoints](#endpoints)<br>
[Database](#database)<br>
[Final considerations](#final-considerations)<br>
[Contributions are Welcome!](#contributions-are-welcome)<br>
[General Guidelines](#general-guidelines)

# Installation 
1. Clone the repository
<div style="background-color: #161B22; padding: 15px; border-radius: 10px">
https://github.com/douglasbarreiros701/iEstoque.git
</div>


2. Install the dependencies with Maven<br>

3. Install [MySLQ](https://dev.mysql.com/doc/mysql-getting-started/en/)

# Usage
1. Start the application on [ApiApplication.java](https://github.com/douglasbarreiros701/iEstoque/blob/master/src/main/java/com/iestoque/api/ApiApplication.java)

2. The API will be accessible at http://localhost:9090 or if you want the version with an interface http://localhost:9090/swagger-ui/index.html


# Endpoints
The api has the following endpoints
<div style="background-color: #161B22; padding: 15px; border-radius: 10px">

GET /products -> Returns all products from the database

<br>
POST /products/{id} -> Post the product to a specific user

<br>
POST /auth/login -> Login to a user

<br>
POST /auth/register - Register a new user into the App

</div>

# Authentication
no rules yet

# Database
The project uses [MySLQ](https://dev.mysql.com/doc/mysql-getting-started/en/) as the database. The necessary database migrations are managed using Flyway.

# Features to come
* Access to products only for the logged in user
* Changing user settings
* Important notices about in-stock products on e-mail
* Login with google

# Final considerations
The documentation above is an overview of the iEstoque API. Be sure to consult the full documentation for detailed information on all available features and endpoints. If you have any questions or need support, please contact me.

# Contributions are Welcome!
Thank you for your interest in contributing to our project. If you want to be part of the development, fix issues, or add new features, we're happy to have your help. Follow the guidelines below to get started:



## How to Contribute

1. **Create a Branch**: Create a new branch to work on the topic you've

<div style="background-color: #161B22; padding: 15px; border-radius: 10px">
   git checkout -b your-new-branch
    </div>
    <br>

2. **Make Your Changes**: Make the necessary changes to the code, documentation, or resources

3. **Test Your Changes**: Ensure that your changes work as expected and add tests if applicable.

4. **Commit Your Changes:**: Commit your changes with clear commit messages.
<div style="background-color: #161B22; padding: 15px; border-radius: 10px">
   git clone https://github.com/your-username/repository-name.git
   </div>
   <br>

5. **Push Your Changes**: Push your changes to the forked repository:
<div style="background-color: #161B22; padding: 15px; border-radius: 10px">
   git push origin your-new-branch
   </div>
   <br>

6. **Open a Pull Request**: Go to the original repository's page on GitHub and click "New Pull Request." Describe your changes and explain why they are necessary.
<br>

# General Guidelines
* Be respectful and courteous to other contributors.
<br>
* Follow the project's coding and style standards.
<br>
* Avoid making massive, unrelated changes.
<br>
* Communicate clearly and effectively in your commit messages and Pull Requests.
<br>
* If you're working on a significant new feature, open an issue for discussion before starting to write code.

Thanks in advance for your contributions to making **iEstoque** better and more useful for everyone!

