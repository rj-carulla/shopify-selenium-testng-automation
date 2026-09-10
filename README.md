# Shopify Selenium TestNG Automation

A Selenium WebDriver automation framework built with **Java, TestNG, and the Page Object Model (POM)** to automate functional and checkout testing for a Shopify-based e-commerce application.

The project focuses on building maintainable UI automation, validating checkout functionality, handling dynamic elements, working with iframes, and documenting defects discovered during testing.

---

## 🛠️ Tech Stack

| Technology         | Purpose                       |
| ------------------ | ----------------------------- |
| Java               | Programming language          |
| Selenium WebDriver | Browser automation            |
| TestNG             | Test execution and assertions |
| Maven              | Dependency management         |
| IntelliJ IDEA      | Development environment       |
| Git / GitHub       | Version control               |
| Firefox            | Test browser                  |
| Page Object Model  | Test architecture             |

---

## 📁 Project Structure

```text
src
├── main
│   └── java
│       │
│       ├── base
│       │   └── BasePage.java
│       │   
│       ├── components
│       │   └── HeaderComponent.java
│       │   └── CartDropdownComponent.java
│       │
│       └── pages
│           ├── LoginPage.java
│           ├── SignUpPage.java
│           ├── HomePage.java
│           ├── CatalogPage.java
│           ├── ProductPage.java
│           ├── CartPage.java
│           ├── CheckoutPage.java
│           └── ConfirmationPage.java
│
└── test
    └── java
        ├── base
        │   └── BaseTest.java
        │   
        └── tests
            ├── AddToCartTest.java
            ├── CartDropdownTest.java
            ├── CartPageTest.java
            ├── CatalogPageTest.java
            ├── CheckOutTest.java
            ├── HomePageTest.java
            ├── SignUpPageTest.java
            └── LoginPageTest.java
```

---

## 🧪 Testing Scope

The automation suite covers major e-commerce functionality including:

* Homepage
* Product catalog
* Product selection
* Add to cart
* Cart functionality
* Checkout
* Required-field validation
* Payment-field validation
* Invalid payment information
* Successful checkout
* Declined transactions
* Gateway failures
* Confirmation page validation

### Authentication

Signup and login tests were also implemented.

However, the application's CAPTCHA/hCaptcha behavior prevents these tests from being reliably executed through Selenium automation. The tests are therefore included as part of the project but are not considered reliable automated regression tests.

---

# ⭐ Test Highlights

These are some of the tests I would highlight in a portfolio presentation.

## 1. Add Product to Cart

Validates that a product can be selected from the catalog and added to the cart.

**Scenario:**

1. Navigate to the catalog
2. Select a product
3. Add the product to the cart
4. Verify the cart count is updated

### Demo

> https://github.com/user-attachments/assets/0bb3992d-007c-4208-aea6-45206d3c05d1

> [▶️ Watch Demo](./demos/1.mp4)
```text
📹 Add to Cart Test
```

---

## 2. Checkout Required Field Validation

Validates that checkout prevents the user from continuing when required information is missing.

The test verifies validation errors for fields including:

* Email
* Last name
* Address
* City
* ZIP/Postal code
* Card number
* Expiration date
* Security code
* Name on card

### Demo

> https://github.com/user-attachments/assets/b9ffd263-646a-4983-8558-03dc634c5109

> [▶️ Watch Demo](./demos/2.mp4) 

```text
📹 Required Field Validation
```

---

## 3. Successful Checkout

Validates a complete checkout flow using the application's test payment behavior.

**Scenario:**

1. Add product to cart
2. Navigate to checkout
3. Enter shipping information
4. Enter payment information
5. Submit payment
6. Verify the confirmation page

The test verifies that the checkout successfully reaches the confirmation page.

### Demo

> https://github.com/user-attachments/assets/13eed6bf-6066-4cea-8314-ce83d3cdb0a0

> [▶️ Watch Demo](./demos/3.mp4)

```text
📹 Successful Checkout
```

---

## 4. Payment Failure Scenarios

The checkout environment provides specific test values to simulate different payment outcomes.

| Test Value | Expected Result      |
| ---------- | -------------------- |
| `1`        | Approved transaction |
| `2`        | Declined transaction |
| `3`        | Gateway failure      |

### Declined Transaction/Gateway Failure

> https://github.com/user-attachments/assets/9aa9ed7e-b174-4cab-bfab-6250839cb6b0

> [▶️ Watch Demo](./demos/4.mp4)

```text
📹 Declined Transaction
```

---

# 🏗️ Framework Design

The project follows the **Page Object Model (POM)** to separate test logic from page interaction and make the framework easier to maintain.

### Page Objects

Each major application page has its own class containing:

* Locators
* Page-specific actions
* Validation/helper methods

For example:

```java
CheckoutPage checkoutPage = cartPage.navigateToCheckout();

checkoutPage.enterEmail("test@gmail.com");
checkoutPage.enterExpirationDate("0829");
checkoutPage.enterSecurityCode("123");
```

This keeps the test cases focused on **what is being tested** rather than how the UI is interacted with.

---

# 🧩 Reusable Components

The framework also uses reusable components for UI elements shared across multiple pages.

For example, the cart count appears throughout the application, so it is handled through:

```text
HeaderComponent
```

This prevents the same locator and interaction logic from being duplicated across multiple page objects.

---

# ⏳ Explicit Waits

The framework uses Selenium's `WebDriverWait` and `ExpectedConditions` instead of relying on fixed delays wherever possible.

Examples include waiting for:

* Elements to become visible
* Elements to become clickable
* Specific text to appear

```java
waitForElement(locator);
waitForElementClick(locator);
waitForText(locator, expectedText);
```

This makes the tests more reliable when working with dynamically rendered elements.

---

# 🖼️ Working With iFrames

The checkout payment fields are loaded inside separate iframes.

The framework handles these fields by switching into the appropriate iframe before interacting with the element and returning to the main document afterward.

```java
driver.switchTo().frame(iframe);

driver.findElement(cardNumField).sendKeys(cardNum);

driver.switchTo().defaultContent();
```

This was particularly important for automating:

* Card number
* Expiration date
* Security code
* Name on card

---

# 💳 Payment Testing

The checkout environment provides test payment values that allow different payment outcomes to be reproduced without using real payment information.

```text
1 → Approved
2 → Declined
3 → Gateway failure
```

This allows the automation suite to verify both successful and unsuccessful payment flows.

---

# 🔐 CAPTCHA Limitation

The application uses CAPTCHA/hCaptcha protection during authentication.

Because CAPTCHA is specifically designed to prevent automated interaction, the Signup and Login tests cannot be reliably executed through Selenium.

Rather than attempting to bypass the CAPTCHA, these tests are documented as a known automation limitation.

This is an example of an important distinction in UI automation:

> Not every manual test is necessarily suitable for full browser automation.

---

# 📋 Test Cases

| Test                    | Description                     | Status                |
| ----------------------- | ------------------------------- | --------------------- |
| Login                   | Verify valid login              | ⚠️ CAPTCHA limitation |
| Signup                  | Verify account creation         | ⚠️ CAPTCHA limitation |
| Homepage                | Verify homepage functionality   | ✅                     |
| Catalog                 | Verify product catalog          | ✅                     |
| Add to Cart             | Verify product can be added     | ✅                     |
| Cart                    | Verify cart functionality       | ✅                     |
| Required Fields         | Verify checkout validation      | ✅                     |
| Invalid Card Number     | Verify invalid card validation  | ✅                     |
| Invalid Expiration Date | Verify expiration validation    | ✅                     |
| Invalid Security Code   | Verify security-code validation | ✅                     |
| Successful Checkout     | Verify successful payment flow  | ✅                     |
| Declined Transaction    | Verify declined payment         | ✅                     |
| Gateway Failure         | Verify payment gateway failure  | ✅                     |

---

# 🧠 What I Learned

Through this project, I gained practical experience with:

* Selenium WebDriver
* TestNG
* Page Object Model
* Writing reusable page methods
* Designing test cases
* Explicit waits
* Dynamic web elements
* XPath and CSS selectors
* Handling iframes
* Checkout automation
* Payment validation
* Test assertions
* Debugging Selenium failures
* Identifying automation limitations
* Separating page objects from test logic
* Using reusable components
* Git and GitHub workflow

---

# 🚀 Future Improvements

Although the project is considered complete, potential future improvements include:

* Add automated test reporting with Allure or Extent Reports
* Expand browser coverage
* Add more data-driven tests
* Improve test data management
* Add additional checkout scenarios
* Improve automated test documentation

---

# 📌 Project Status

**Completed**

This project was created as a practical Selenium automation portfolio project focused on demonstrating UI automation, framework design, checkout testing, validation testing, and defect identification.

The project intentionally documents limitations such as CAPTCHA rather than attempting to bypass application security mechanisms.
