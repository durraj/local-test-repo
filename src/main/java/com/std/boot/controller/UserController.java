package com.std.boot.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.std.account.service.SecurityService;
import com.std.account.service.UserService;
import com.std.account.service.UserTokenService;
import com.std.account.validator.STDResultResponse;
import com.std.account.validator.UserValidator;
import com.std.api.response.APIStatus;
import com.std.api.response.StatusResponse;
import com.std.api.util.APIUtil;
import com.std.api.util.DateUtil;
import com.std.api.util.MD5Hash;
import com.std.api.util.UniqueID;
import com.std.boot.model.Address;
import com.std.boot.model.Customer;
import com.std.boot.model.CustomerImage;
import com.std.boot.model.User;
import com.std.boot.model.UserProfile;
import com.std.boot.model.UserToken;
//import com.std.out.ResultJson;
import com.std.common.util.Constant;
import com.std.dto.DealDTO;
import com.std.dto.DealsDTO;
import com.std.integration.scheduler.RESTDealJobLauncher;

@RestController
public class UserController extends APIUtil{
	 	
		@Autowired
	    private UserService userService;

	   /* @Autowired
	    private SecurityService securityService;

	    @Autowired
	    private UserValidator userValidator;
	    
	    @Autowired
	    private STDResultResponse resultJson;*/
	    @Autowired
	    private UserTokenService userTokenService;
	    
	    @Autowired
	    private ItemReader itemReader;
	    
	   /* @Autowired
	    private STDBCryptPasswordEncoder bCryptPasswordEncoder;*/
	   /* @RequestMapping(value = "/registration", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new UserProfile());

	        return "registration";
	    }*/
	   /* @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public @ResponseBody STDResultResponse registration(            
	            @RequestParam(value="firstName", required=true) String firstName,
	            @RequestParam(value="lastName", required=true) String lastName,
	            @RequestParam(value="email", required=true) String email,
	            @RequestParam(value="password", required=true) String password,
	            @RequestParam(value="confirmpassword", required=true) String confirmpassword
	            ) {
	    	BCryptPasswordEncoder bCryptPasswordEncoder1 = new BCryptPasswordEncoder();
			//STDBCryptPasswordEncoder bCryptPasswordEncoder = new STDBCryptPasswordEncoder(bCryptPasswordEncoder1 );
	    		UserProfile userForm= new UserProfile(firstName,lastName,email,password,confirmpassword);
	    		//userValidator.validate(userForm, null);
	    		STDResultResponse error = userValidator.validate(userForm);
	    		
	    		if (error.isErrors()) {
	    			resultJson.put("success", false);
	    			resultJson.put("error", new JSONObject(error.getAllErrors()));
		            //return resultJson;
		        }
	    		//resultJson.put("success", true);
	    		if(!error.isErrors()){
	    			userForm.setPassword(bCryptPasswordEncoder1.encode(userForm.getPassword()));
	    			userService.save(userForm);
	    			securityService.autologin(userForm.getEmail(), userForm.getPassword());
	    		}
	    		return error;
	            //return resultJson;               
	    }*/
	    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
	    public @ResponseBody STDResultResponse login(            
	            @RequestParam(value="email", required=true) String email,
	            @RequestParam(value="password", required=true) String password
	            ) {
	    	 if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
	             return new ResponseEntity<>(new User(), HttpStatus.OK);
	         }
	         if (userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == null){
	             return new ResponseEntity<>(new User(), HttpStatus.OK);
	         }
	    	//UserProfile user = userService.findByUsername(email);
	    	//ResultJson resultJson = new ResultJson();
	    	securityService.autologin(email, password);
	    	//RequestContextHolder.currentRequestAttributes()
	    	 if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
	    	 {
	    		 System.out.println("session id :"+RequestContextHolder.currentRequestAttributes().getSessionId());
	    		 resultJson.setErrors(false);
	    		 resultJson.getValues().put("loggedIn", true);//setValues(new HashMap<String,Object>().put("loggedIn", true));
	    	 }
	    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    	String encodedPassword= bCryptPasswordEncoder.encode(password);
	    	if(User.getPassword().equalsIgnoreCase(encodedPassword))
	    	{
	    		
	    	}
	         return resultJson;//new ResponseEntity<>(userService.findByUsername(email), HttpStatus.OK);
	    	
	    }*/
	    
	    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	    public String login(/*HttpServletRequest request,*/
	    		@RequestParam(value="email", required=true) String email,
	            @RequestParam(value="password", required=true) String password,
	            @RequestParam(value="keepMeLogin", required=true) Boolean keepMeLogin
	    ) {
	    	/*String email= request.getParameter("email");
	    	String password= request.getParameter("password");
	    	boolean keepMeLogin=true;*/

	        if ("".equals(email) || "".equals(password)) {
	            // invalid paramaters
	            statusResponse = new StatusResponse(APIStatus.INVALID_PARAMETER);
	        } else {
	            //User userLogin = userService.getUserByEmail(email, companyId);
	            User userLogin = userService.getUserByEmail(email);

	            if (userLogin != null) {
	                String passwordHash = null;
	                try {
	                    passwordHash = MD5Hash.MD5Encrypt(password + userLogin.getSalt());
	                } catch (NoSuchAlgorithmException ex) {
	                    throw new RuntimeException("User login encrypt password error", ex);
	                }

	                int userStatus = userLogin.getStatus();
	                if (userStatus == Constant.USER_STATUS.ACTIVE.getStatus()) {
	                    if (passwordHash.equals(userLogin.getPasswordHash())) {
	                        UserToken userToken = new UserToken();
	                        userToken.setToken(UniqueID.getUUID());
	                       // userToken.setCompanyId(companyId);
	                        userToken.setUserId(userLogin.getUserId());

	                        Date currentDate = new Date();
	                        userToken.setLoginDate(DateUtil.convertToUTC(currentDate));

	                        Date expirationDate = keepMeLogin ? new Date(currentDate.getTime() + Constant.DEFAULT_REMEMBER_LOGIN_MILISECONDS) : new Date(currentDate.getTime() + Constant.DEFAULT_SESSION_TIME_OUT);
	                        userToken.setExpirationDate(DateUtil.convertToUTC(expirationDate));

	                        userTokenService.save(userToken);
	                        statusResponse = new StatusResponse<>(HttpStatus.OK.value(), userToken);
	                    } else {
	                        // wrong password
	                        statusResponse = new StatusResponse(APIStatus.ERR_USER_NOT_VALID);
	                    }
	                } else if (userStatus == Constant.USER_STATUS.PENDING.getStatus()) {
	                    statusResponse = new StatusResponse(APIStatus.USER_PENDING_STATUS);
	                } else {
	                    statusResponse = new StatusResponse(APIStatus.ERR_USER_NOT_VALID);
	                }
	            } else {
	                // can't find user by email address in database
	                statusResponse = new StatusResponse(APIStatus.ERR_USER_NOT_EXIST);
	            }
	        }

	        return writeObjectToJson(statusResponse);
	    }
	    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	    public String register(
	    		 @RequestParam(value="firstName", required=true) String firstName,
		            @RequestParam(value="lastName", required=true) String lastName,
		            @RequestParam(value="email", required=true) String email,
		            @RequestParam(value="password", required=true) String password,
		            @RequestParam(value="confirmpassword", required=true) String confirmpassword
	    ) {

	        // check user already exists
	        User existed = userService.getUserByEmail(email);
	        if (existed == null) {
	            // email is valid to create user
	           // String email = user.getEmail(),
	                    //password = user.getPasswordHash();
	            
	            if (email != null && !email.equals("") && password != null && !password.equals("")) {

	                Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	                Matcher matcher = pattern.matcher(email);

	                if (!matcher.matches() || password.length() < 6) {
	                    statusResponse = new StatusResponse(APIStatus.ERR_INVALID_DATA);
	                    return writeObjectToJson(statusResponse);
	                }

	                User userSignUp = new User();
	                userSignUp.setUserId(UniqueID.getUUID());
	                //userSignUp.setCompanyId(companyId);
	                userSignUp.setCreateDate(new Date());
	                userSignUp.setEmail(email);
	                userSignUp.setFirstName(firstName);
	                userSignUp.setLastName(lastName);
	                userSignUp.setMiddleName("");
	                userSignUp.setSalt(UniqueID.getUUID());

	                try {
	                    userSignUp.setPasswordHash(MD5Hash.MD5Encrypt(password + userSignUp.getSalt()));
	                } catch (NoSuchAlgorithmException ex) {
	                    throw new RuntimeException("Encrypt user password error", ex);
	                }

	                userSignUp.setRoleId(Constant.USER_ROLE.REGISTED_USER.getRoleId());
	                userSignUp.setStatus(Constant.USER_STATUS.ACTIVE.getStatus());

	                userService.save(userSignUp);
	                // do send mail notify...
	                statusResponse = new StatusResponse(APIStatus.OK.getCode(), userSignUp);
	            } else {
	                statusResponse = new StatusResponse(APIStatus.ERR_INVALID_DATA);
	                return writeObjectToJson(statusResponse);
	            }

	        } else {
	            // notify user already exists
	            statusResponse = new StatusResponse(APIStatus.USER_ALREADY_EXIST);
	        }

	        return writeObjectToJson(statusResponse);
	    }
	    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	    public String logout( @RequestParam String token) {

	        UserToken userToken = userTokenService.getTokenById(token);
	        if (userToken != null) {
	            userTokenService.invalidateToken(userToken);
	            statusResponse = new StatusResponse(APIStatus.OK);
	        } else {
	            statusResponse = new StatusResponse(APIStatus.INVALID_TOKEN);
	        }

	        return writeObjectToJson(statusResponse);
	    }

}
