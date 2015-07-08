package in.nowke.koluconvert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by nav on 28/6/15.
 */
public class FragmentFeet extends Fragment {

    Button convert;
    RadioGroup radioFeet;
    TextInputLayout textInputFeet;
    TextInputLayout textInputInch;
    TextView resultText;
    ScrollView scrollView;

    private static int MODE_FOOT = 1;
    private static int MODE_SQFT = 2;
    private static int MODE_CUFT = 3;

    private static int FEETMODE = MODE_FOOT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feet, container, false);
        textInputFeet = (TextInputLayout) rootView.findViewById(R.id.textinput_feet);
        textInputInch = (TextInputLayout) rootView.findViewById(R.id.textinput_inches);
        radioFeet = (RadioGroup) rootView.findViewById(R.id.feet_radio);
        convert = (Button) rootView.findViewById(R.id.buttonConvertFeet);
        resultText = (TextView) rootView.findViewById(R.id.textFeet);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertFeet(v);
            }
        });

        textInputInch.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    textInputFeet.getEditText().clearFocus();
                    textInputInch.getEditText().clearFocus();
                    convertFeet(v);
                    return true;
                }
                return false;
            }
        });

        radioFeet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_feet:
                        FEETMODE = MODE_FOOT;
                        textInputInch.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radio_sqft:
                        FEETMODE = MODE_SQFT;;
                        textInputInch.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radio_cuft:
                        FEETMODE = MODE_CUFT;
                        textInputInch.setVisibility(View.INVISIBLE);
                        break;
                }
                convertFeet(group.findViewById(checkedId));
            }
        });


        return rootView;
    }

    private void convertFeet(View v) {
        String editFeetStr = textInputFeet.getEditText().getText().toString().trim();
        String editInchStr = textInputInch.getEditText().getText().toString().trim();

        if ((editFeetStr.equals("") || editFeetStr.equals(".")) && (editInchStr.equals("") || editInchStr.equals("."))) {
            return;
        }

        if (editFeetStr.equals("") || editFeetStr.equals(".")) {
            editFeetStr = "0";
        }

        if(editInchStr.equals("") || editInchStr.equals(".")) {
            editInchStr = "0";
        }

        Double feetValue = Double.parseDouble(editFeetStr);
        Double inchValue = Double.parseDouble(editInchStr);

        String resFeet;

        if (inchValue >= 12) {
            Helpers.hideKeyboard(getActivity(), textInputInch.getEditText());
            Helpers.hideKeyboard(getActivity(), textInputFeet.getEditText());
            Snackbar.make(v, "Inch can't be 12 or more", Snackbar.LENGTH_LONG).show();
            return;
        }
        switch (FEETMODE) {
            case 1:
                // Feet
                Double inchDecimals = inchValue / 12.0;
                Double feetDecimals = feetValue + inchDecimals;
                Double meterValue = feetDecimals / 3.28;

                Double meterValuePrec = Helpers.toFixed(meterValue, 4);
                resultText.setText(Html.fromHtml(String.valueOf(meterValuePrec) + "<small> m</small>"));
                break;

            case 2:
                // Square Feet
                Double squareMeter = (1 / 10.7639) * feetValue;
                Double squareMeterPrec = Helpers.toFixed(squareMeter, 4);
                resultText.setText(Html.fromHtml(String.valueOf(squareMeterPrec) + "<small> m²</small"));
                break;

            case 3:
                // Cubic Feet
                Double cubicMeter = (1 / 35.28) * feetValue;
                Double cubicMeterPrec = Helpers.toFixed(cubicMeter, 4);
                resultText.setText(Html.fromHtml(String.valueOf(cubicMeterPrec) + "<small> m³</small>"));
                break;
            default:
                break;
        }
        Helpers.hideKeyboard(getActivity(), textInputFeet.getEditText());
        Helpers.hideKeyboard(getActivity(), textInputInch.getEditText());
    }
}
