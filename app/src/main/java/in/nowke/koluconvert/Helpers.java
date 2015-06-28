package in.nowke.koluconvert;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.math.BigDecimal;

/**
 * Created by nav on 28/6/15.
 */
public class Helpers {

    public static Double toFixed(Double value, Integer no_of_decimals) {
        // Rounds up 'value' to 'digits' no.of decimals
        return new BigDecimal(value).setScale(no_of_decimals, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void hideKeyboard(Context context, EditText editText) {
        // Hides the Keyboard input from 'editText'
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
