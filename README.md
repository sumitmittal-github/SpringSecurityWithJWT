# Spring Security with JWT in Spring boot

<p>
	This repository contains Spring Security with JWT in Spring boot
</p>



<p> 
	REST Clients to test the Microservices are - <br/>
	1. Add chrome extension :	ReqBin HTTP Client (https://reqbin.com/)  <br/>
	2. Add chrome extension :	Talend API Tester - Free Edition <br/>
</p>
<br/> <br/>
 


<p>
	To download 256 bit Sign in key -  <br/>
	1. https://seanwasere.com/generate-random-hex/ <br/>
	2. https://asecuritysite.com/encryption/plain <br/>
	3. https://www.save-editor.com/crypto/crypt_key_generator.html <br/>
	4. http://allkeysgenerator.com <br/>
</p>
<br/> <br/>



<p>
	verify this JWT Token from - 	https://jwt.io/ <br/> <br/>
</p>
<br/> <br/>



<p>
	API-1 : To register the user in DB - <br/>
	POST : http://localhost:8080/api/v1/auth/register

	Body : 
	{
	  "firstname": "Sumit",
	  "lastname": "Mittal",
	  "email": "sumit@gmail.com",
	  "username": "sumit.mital",
	  "password": "pwd123"
	}

	Response : "User registered successfully"
</p>
<br/> <br/>



<p>
	API-2 : To Login the user - <br/>
	POST : http://localhost:8080/api/v1/auth/login
	
	Body :
	{
	  "username": "sumit.mittal",
	  "password": "pwd123"
	}

	Response : 
	"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdW1pdCIsImlhdCI6MTcwMjEzNTM0OCwiZXhwIjoxNzAyMTM3MTQ4fQ.ebIB3wZdU_l1J8WzhlsZ-BLdIvelfJKbgtrCXuL8Dw0"
</p>
<br/> <br/>



<p>
	API-3 : To access secure API - <br/>
	PUT : http://localhost:8080//api/v1/user
	
	Authorization -> Bearer token -> eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdW1pdCIsImlhdCI6MTcwMjEzNTM0OCwiZXhwIjoxNzAyMTM3MTQ4fQ.ebIB3wZdU_l1J8WzhlsZ-BLdIvelfJKbgtrCXuL8Dw0

	Body : 
	{
	  "firstname": "My New First Name",
	  "lastname": "My New Last Name",
	  "email": "mynewemail@gmail.com"
	}

	Response : 
    {
	  "firstname": "My New First Name",
	  "lastname": "My New Last Name",
	  "email": "mynewemail@gmail.com",
	  "username": "sumit.mital",
	  "password": "pwd123"
	}
</p>

