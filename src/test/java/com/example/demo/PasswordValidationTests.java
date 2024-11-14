// package com.example.demo;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;

// import com.example.demo.service.PasswordValidationService;
// import com.example.demo.util.PasswordValidateCode;

// public class PasswordValidationTests
// {
//     @Autowired
//     PasswordValidationService passwordValidator;

//     @Test
//     void TestLength()
//     {
//         assertEquals(passwordValidator.validate("0Ab"), PasswordValidateCode.LENGTH);
//         assertEquals(passwordValidator.validate("012345Ab"), PasswordValidateCode.LENGTH);
//         assertEquals(passwordValidator.validate("0123456789Ab"), PasswordValidateCode.OK);
//     }

//     @Test
//     void TestLower()
//     {
//         assertEquals(passwordValidator.validate("0123456789ABCD"), PasswordValidateCode.LOWER_CASE);
//         assertEquals(passwordValidator.validate("0123456789ABcd"), PasswordValidateCode.OK);
//     }

//     @Test
//     void TestUpper()
//     {
//         assertEquals(passwordValidator.validate("0123456789abcd"), PasswordValidateCode.UPPER_CASE);
//         assertEquals(passwordValidator.validate("0123456789abCD"), PasswordValidateCode.OK);
//     }

//     @Test
//     void TestNumber()
//     {
//         assertEquals(passwordValidator.validate("AbCdEfGhIjKl"), PasswordValidateCode.NUMBER);
//         assertEquals(passwordValidator.validate("01AbCdEfGhIj"), PasswordValidateCode.OK);
//     }
// }