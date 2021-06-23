package fsktm.edu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class signuptabfragment extends Fragment {

    EditText emailsignuppp, pass, cfmpass;
    AppCompatButton buttSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tabfragment,container,false);


        emailsignuppp = root.findViewById(R.id.emailsignup);
        pass = root.findViewById(R.id.signuppassword);
        cfmpass = root.findViewById(R.id.confirmpassword);
        buttSignUp = root.findViewById(R.id.buttonsignup);

        buttSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail() | !validatePass() | !validatecfmpass()){
                    return;
                }
                else{
                    startActivity(new Intent(getActivity(),biometricauthentication.class));
                }
            }
        });



        return root;
    }

    private boolean validateEmail(){
        String val = emailsignuppp.getText().toString().trim();
        //check spaces
        //"\\A\\w{4,20}\\z" means the username should have minimum of 4 & max of 20 char
        // w/o spaces at the start or in the end
        //Any capital A to small z
        String checkEmail = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        //check the value is empty or not
        if (val.isEmpty()) {
            //seterror is build in fx
            emailsignuppp.setError("Field cannot be empty");
            return false;
        }
        //if value entered is not matched with the define method, error will appear
        else if(!val.matches(checkEmail)){
            emailsignuppp.setError("Invalid Email");
            return false;
        }
        else {
            //null will automatically remove the error
            emailsignuppp.setError(null);
            return true;
        }
    }

    private boolean validatePass(){
        //create string to get user's value of fullname
        //as we are using material design and assign id to layout, need to define getedittext,
        // trim to ensure there's no spaces that will be stored into the database
        String val = pass.getText().toString().trim();
        String val2 = cfmpass.getText().toString().trim();
        //check spaces
        //"\\A\\w{4,20}\\z" means the username should have minimum of 4 & max of 20 char
        // w/o spaces at the start or in the end
        //Any capital A to small z
        String checkPass = "^"+ //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                //"(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=S+$)" +           //no white spaces
                ".{4,20}";               //at least 4 characters
        // "$";;

        //check the value is empty or not
        if (val.isEmpty()) {
            //seterror is build in fx
            pass.setError("Field cannot be empty");
            return false;
        }
        else if(!val.equals(val2)){
            cfmpass.setError("Passwords do not match");
            return false;
        }
        //if value entered is not matched with the define method, error will appear
        else if(!val.matches(checkPass)){
            pass.setError("Password should contain 4 characters");
            return false;
        }
        else {
            //null will automatically remove the error
            pass.setError(null);

            return true;
        }
    }

    private boolean validatecfmpass(){
        String val = cfmpass.getText().toString().trim();
        String val2 = pass.getText().toString().trim();


        if(!val.equals(val2)){
            cfmpass.setError("Passwords do not match");
            return false;
        }
        else {
            //null will automatically remove the error
            cfmpass.setError(null);
            return true;
        }
    }
}
