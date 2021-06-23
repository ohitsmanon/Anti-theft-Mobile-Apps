package fsktm.edu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class logintabfragment extends Fragment {

EditText email, password;
TextView forgetpass;
Button login;
float v=0;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tabfragment,container,false);



        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        forgetpass = root.findViewById(R.id.forgetpassword);
        login = root.findViewById(R.id.login);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail() | !validatePass()){
                    return;
                }
                else{
                    startActivity(new Intent(getActivity(),UserDashboard.class));
                }
            }
        });



        return root;
    }

    private boolean validateEmail(){
        String val = email.getText().toString().trim();
        //check spaces
        //"\\A\\w{4,20}\\z" means the username should have minimum of 4 & max of 20 char
        // w/o spaces at the start or in the end
        //Any capital A to small z
        String checkEmail = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        //check the value is empty or not
        if (val.isEmpty()) {
            //seterror is build in fx
            email.setError("Field cannot be empty");
            return false;
        }
        //if value entered is not matched with the define method, error will appear
        else if(!val.matches(checkEmail)){
            email.setError("Invalid Email");
            return false;
        }
        else {
            //null will automatically remove the error
            email.setError(null);
            return true;
        }
    }

    private boolean validatePass(){
        //create string to get user's value of fullname
        //as we are using material design and assign id to layout, need to define getedittext,
        // trim to ensure there's no spaces that will be stored into the database
        String val = password.getText().toString().trim();
       // String val2 = cfmpass.getText().toString().trim();
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
            password.setError("Field cannot be empty");
            return false;
        }
        //if value entered is not matched with the define method, error will appear
        else if(!val.matches(checkPass)){
            password.setError("Password should contain 4 characters");
            return false;
        }
        else {
            //null will automatically remove the error
            password.setError(null);

            return true;
        }
    }
}
