# Groovy as Formula POC

To Run the test case, please:
- Start the Centralized Configuration
  with below environment variables:
```
# In *nix system, use 'export CONFIG_ACCESS_ID=hello'
# In Windows, use "set CONFIG_ACCESS_ID=hello"
# In IDE, just configure the run parameters for Environment Variables.

CONFIG_ENCRYPT_KEY=fdafdsafdsafdssafda
CONFIG_ACCESS_ID=hello
CONFIG_ACCESS_KEY=world
```

- Start the demo project, it is written in java8, also some code in Kotlin.
```
# With below java option
-Dspring.profiles.active=dev

# With below environment variables:
CONFIG_ACCESS_URL=http://localhost:8888
CONFIG_ACCESS_ID=hello
CONFIG_ACCESS_KEY=world
```

- Get a jwt token from port 8888  
`curl -i http://localhost:8080/jwt -d ''`

- Use the `jwt token` generated just now to test the calculation:
```
curl -i http://localhost:8080/calc/MyFormula/y \
-d '{"x":23}' -H "Content-Type: application/json" \
-H "X-JWT-HEADER: eyJhbGciOiJFUzI1NiJ9.eyJuYmYiOjE1NTY5NzY5OTYsImV4cCI6MTU1Njk3NzU5Nn0.LSQ1DScKIkomlA1bpJKBvjJ8XsUAQJQHAUnNKS8k5bl2P6OBtGzR6PSqMt1wVZMUGHmxDFiOVLS2WxCj8rHBbQ"
```

# Configurations
When you are preparing a new groovy formulas, you should configure these points:
- If your file name is `myFormula1.groovy`, ensure you have a `myFormula1Pre.groovy` to initialize all the variables that would be given from external requester. Because when the program starts up, it will compile all the groovy scripts and execute them, if you dont define and give some initialization, it will fail to compile.  
  For example
  > We have `MyFormula.groovy`, which expect an `x` from external env. Then we defined a `MyFormulaPre.groovy` to provide `x=0`
- Configure the `formula.groovy.files` in your `application.properties`.  
  For Example:
  > We configured below lines. The application will compile them and execute them one by one.
  > ```
  > # The sequence matters, because when the program starts up, it will execute each of them once, in this sequence.
  > formula.groovy.files=Decimal.groovy, \
  >  MyFormulaPre.groovy, MyFormula.groovy
  > ```

-- End of document
