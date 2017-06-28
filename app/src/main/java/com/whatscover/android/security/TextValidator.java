package com.whatscover.android.security;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.whatscover.android.R;

import java.util.regex.Pattern;

/**
 * Created by arazfarhang on 02/06/2016.
 */
public class TextValidator extends AwesomeValidation {

    private static TextValidator instance;
    protected Context mContext;

    private TextValidator(@NonNull Context context) {
        super(ValidationStyle.UNDERLABEL);

        //setColor(Color.RED);
        mContext = context;
        setContext(mContext);
    }


    public static TextValidator createTextValidator(@NonNull Context context) {
        return new TextValidator(context);
    }

    public void addMailValidation(EditText text) {
        addValidation(text, TextValidator.EMAIL, mContext.getString(R.string.error_invalid_email));
    }

    public void addPasswordValidation(EditText text) {
        addValidation(text, TextValidator.PASSWORD, mContext.getString(R.string.error_invalid_password));
    }

    public void addNameValidation(EditText text) {
        addValidation(text, TextValidator.NAME, mContext.getString(R.string.error_invalid_name));
    }

    public void addNumberValidation(EditText text) {
        addValidation(text, TextValidator.NUMBER, mContext.getString(R.string.error_invalid_number));
    }

    public void addDateValidation(EditText text) {
        addValidation(text, TextValidator.DATE, mContext.getString(R.string.error_invalid_date));
    }



/*
    public static AwesomeValidation getValidator(){
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        mAwesomeValidation.setColor(Color.RED);  // optional, default color is RED if not set

        return mAwesomeValidation;
    }*/

    public static final String TELEPHONE = "(^\\+\\d+)?[0-9\\s()-]*";
    public static final String NAME = "[a-zA-Z\\s]+";

    //dd/MM/yyyy
    public static final String DATE = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\" +
            "/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\" +
            "/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)" +
            "\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";
    public static final String NUMBER = "^[0-9]+(\\.[0-9]{1,2})?$";
    //public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,30}$";
    public static final String EMAIL = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
//   public static final String EMAIL = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\" +\n" +
    //         "            \"\\\\@\" +\n" +
    //       "            \"[a-zA-Z0-9][a-zA-Z0-9\\\\-]{0,64}\" +\n" +
    ///     "            \"(\" +\n" +
    //  "                \"\\\\.\" +\n" +
    // "                \"[a-zA-Z0-9][a-zA-Z0-9\\\\-]{0,25}\" +\n" +
    // "            \")+";

    /**
     * # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
     * (?=\\S+$)          # no whitespace allowed in the entire string
     * .{4,}             # anything, at least six places though
     * # end-of-string
     *
     * @return
     */


    public Pattern getPatternFromRegex(String regex) {
        return Pattern.compile(regex);
    }

    public Pattern getPasswordPattern() {
        return getPatternFromRegex(PASSWORD);
    }

    public Pattern getEmailPattern() {
        return Patterns.EMAIL_ADDRESS;
    }

    public Pattern getTelephonePattern() {
        return getPatternFromRegex(TELEPHONE);
    }
}
