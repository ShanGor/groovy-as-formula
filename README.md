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

- Use test the calculation: (Please replace the **`JWT token`** with the one generated just now.)
```
curl -i http://localhost:8080/calc/MyFormula \
 -d '{"x":23}' -H "Content-Type: application/json" \
 -H "X-JWT-HEADER: eyJhbGciOiJFUzI1NiJ9.eyJuYmYiOjE1NTY5NzQ3NTMsImV4cCI6MTU1Njk3NTM1M30.PlFZ5rxp5fUIbrAmhEHUFj4sHuSyek7K0737okeVLopLMtk5N0ypvxx-WlIvKXnMig5czP1MtioW4R6PG3JW3A"
```