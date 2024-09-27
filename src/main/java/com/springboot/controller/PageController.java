package com.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.springboot.Form.ForgotPasswordForm;
import com.springboot.Form.UserForm;
import com.springboot.dto.UserDto;
import com.springboot.entity.Country;
import com.springboot.entity.State;
import com.springboot.entity.User;
import com.springboot.helper.Message;
import com.springboot.helper.MessageType;
import com.springboot.repositories.CountryRepo;
import com.springboot.repositories.StateRepo;
import com.springboot.repositories.UserRepo;
import com.springboot.service.UserService;

import jakarta.servlet.http.HttpSession; 
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class PageController {
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private UserRepo userRepo;

   @Autowired
   private StateRepo stateRepo;

   @Autowired
   private CountryRepo countryRepo;
   
   @Autowired
   private PasswordEncoder passwordEncoder;
   
  //  @Autowired
  //  private EmailService emailService;
   
   private String otp;
    @GetMapping("/register")
    public String registerPage(Model model){
        System.out.println("Register Page Loading");
        UserForm userform =new UserForm();
        model.addAttribute("userform", userform);
        return "registation";
    }
    
    @ModelAttribute("countrys")
    public List<Country> populateList(Model model) {
      List<Country> country=countryRepo.findAll();
        return country;
    }
    
    //  @GetMapping("/send-email")
    // public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
    //   otp=GenerateOTP.getOTP();
    //     emailService.sendSimpleEmail(to, subject, text+otp);
    //     System.out.println("Email Send");
    //     return "Email sent successfully";
    // }
    // @GetMapping("/generateOTP")
    // public String GenerateOTP() {
      
    //   System.out.println("OTP generated : "+otp);
    //     return otp;
    // }

    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute("userform") UserForm userform,BindingResult bindingResult,HttpSession session){
       // System.out.println(userform);
       User user=userRepo.findByEmail(userform.getEmail().toLowerCase()).orElse(null);
      if(user==null){
        userform.setEmail(userform.getEmail().toLowerCase());
        if(bindingResult.hasErrors()){
          return "registation";
        }

        State state=stateRepo.findById(Long.parseLong(userform.getState())).orElse(null);
        Country country =countryRepo.findById(Long.parseLong(userform.getCountry())).orElse(null);
        UserDto userDto=UserDto.builder()
                      .name(userform.getFirstName()+" "+userform.getLastName())
                      .email(userform.getEmail())
                      .password(userform.getPassword())
                      .gender(userform.getGender())
                      .phonenumber(userform.getPhoneNumber())
                      .address(userform.getStreetAddress()+ " " +userform.getStreetAddress2()+", "+
                              state.getState_name()+", "+
                              userform.getCity()+" "+
                              userform.getPincode())
                      .birthDate(userform.getBirthDate())
                      .country(country.getCountry_name())
                      .build();
                      Long.parseLong("0", 10);
          userService.saveUser(userDto);
          System.out.println("User Save Successfully "+userDto);
          Message message=Message.builder().content("Registation Successful").link("/login").linkContent("Click here to Login").type(MessageType.green).build();
          session.setAttribute("message",message);
      }
      else
      {
          Message message=Message.builder().content("Email Address already Registation ").link("").linkContent("").type(MessageType.red).build();
          session.setAttribute("message",message);
      }
       
          return "redirect:register";
    } 

    @GetMapping("/login")
    public String loginPage(){
      System.out.println("Login page is loading");
      // LoginForm loginForm=new LoginForm();
      // model.addAttribute("loginForm",loginForm);
      return "login";
    }
    
  

    @GetMapping("/forgotPassword")
    public String ForgetPasswordPage(Model model){
      System.out.println("ForgotPassword page is loading");
      ForgotPasswordForm form=new ForgotPasswordForm();
      // String otp=GenerateOTP.getOTP();
      // System.out.println("OTP : "+otp);
      // emailService.sendSimpleEmail("parikshit9112@gmail.com","OTP for password change","OTP : "+otp);
      model.addAttribute("form", form);
      return "forgotPassword";
    }
    @RequestMapping(value="/do-update", method=RequestMethod.POST)
    public String  processLogin(@Valid @ModelAttribute("form") ForgotPasswordForm form,BindingResult bindingResult,HttpSession session){
      
      if(bindingResult.hasErrors()){
        return "forgotPassword";
      }
      else if(!(form.getPassword().equals(form.getRe_password()))){
        Message message=Message.builder().content("New Password And Re-Enter Password does not match").type(MessageType.red).build();
        session.setAttribute("message",message);
      }
      else{
      User user=userRepo.findByEmail(form.getUsername()).orElse(null);
      if(user==null){
        Message message=Message.builder().content("User does not exist ").type(MessageType.red).build();
        session.setAttribute("message",message);
      }
      else if(passwordEncoder.matches(form.getPassword(), user.getPassword())){
        System.out.println("check password : "+passwordEncoder.matches(form.getPassword(), user.getPassword()));  
        // System.out.println("check"+BCrypt.checkpw(form.getPassword(),user.getPassword()));
        Message message=Message.builder().content("Old and New password can't be same").type(MessageType.red).build();
        session.setAttribute("message",message);
      }
      else{
        System.out.println("Updated password : "+form.getPassword());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        // user.setPassword(form.getPassword());
        userService.updateUser(user);
        Message message=Message.builder().content("Password Successfully Reset").link("/login").linkContent("Click here to Login").type(MessageType.green).build();
        session.setAttribute("message",message);
      }
    }
      return "redirect:forgotPassword";
    }
    
}
