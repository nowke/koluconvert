package in.nowke.koluconvert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nav on 28/6/15.
 */
public class FragmentKolu extends Fragment {
    Button convert;
    TextInputLayout koluTextInput;
    TextInputLayout angulaTextInput;
    RadioGroup koluTypeRadio;
    TextView resultText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kolu, container, false);
        koluTextInput = (TextInputLayout) rootView.findViewById(R.id.textinput_kolu);
        angulaTextInput = (TextInputLayout) rootView.findViewById(R.id.textinput_angula);
        koluTypeRadio = (RadioGroup) rootView.findViewById(R.id.madhur_payyannur_radio);
        resultText = (TextView) rootView.findViewById(R.id.textKolu);

        convert = (Button) rootView.findViewById(R.id.buttonConvertKolu);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertKolu(v);
            }
        });

        angulaTextInput.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    angulaTextInput.getEditText().clearFocus();
                    koluTextInput.getEditText().clearFocus();
                    convertKolu(v);
                    return true;
                }
                return false;
            }
        });

        koluTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                convertKolu(group.findViewById(checkedId));
            }
        });
        return rootView;
    }

    private void convertKolu(View v) {
        // KOLU_FACTOR : Madhur Kolu = 0.743, Payyannur kolu = 0.7114
        Double KOLU_FACTOR;
        Double MADHUR_KOLU_FACTOR = 0.743;
        Double PAYYANNUR_KOLU_FACTOR = 0.7114;

        // Default KOLU_FACTOR set to Madhur Kolu
        KOLU_FACTOR = MADHUR_KOLU_FACTOR;

        // Get the value from Kolu and Angula EditText
        String koluValStr = koluTextInput.getEditText().getText().toString();
        String angulaValStr = angulaTextInput.getEditText().getText().toString();

        if (koluValStr.equals("") && (angulaValStr.equals("") || angulaValStr.equals("."))) {
            return;
        }

        // Set Kolu/Angula to zero if they're not specified
        if (koluValStr.equals("") || koluValStr.equals(".")) {
            koluValStr = "0";
        }

        if (angulaValStr.equals("") || angulaValStr.equals(".")) {
            angulaValStr = "0";
        }


        // Convert strings to numbers
        Integer koluVal = Integer.parseInt(koluValStr);
        Double angulaVal = Double.parseDouble(angulaValStr);

        // Notify and break if Angula is >= 24
        if (angulaVal >= 24) {
            Helpers.hideKeyboard(getActivity(), koluTextInput.getEditText());
            Helpers.hideKeyboard(getActivity(), angulaTextInput.getEditText());
            Snackbar.make(v, "Angula can't be 24 or more", Snackbar.LENGTH_LONG).show();
            return;
        }

        switch (koluTypeRadio.getCheckedRadioButtonId()) {
            case R.id.radio_madhur:
                KOLU_FACTOR = MADHUR_KOLU_FACTOR;
                break;

            case R.id.radio_payyannur:
                KOLU_FACTOR = PAYYANNUR_KOLU_FACTOR;
                break;
        }

        // Calculate the Stuffs
        Double angulaDecimals = angulaVal / 24.0;
        Double koluDecimals = koluVal + angulaDecimals;

        Double finalMeters = koluDecimals * KOLU_FACTOR;
        Double precFinalMeters = Helpers.toFixed(finalMeters, 4);

        resultText.setText(Html.fromHtml(String.valueOf(precFinalMeters) + "<small> m</small>"));

        Helpers.hideKeyboard(getActivity(), koluTextInput.getEditText());
        Helpers.hideKeyboard(getActivity(), angulaTextInput.getEditText());
    }

}
