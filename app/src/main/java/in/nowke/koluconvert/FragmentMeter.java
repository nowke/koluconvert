package in.nowke.koluconvert;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by nav on 28/6/15.
 */
public class FragmentMeter extends Fragment {

    Button convertButton;
    RadioGroup meterRadioGroup;
    TextInputLayout textInputMeter;
    TextView resultText1;
    TextView resultText2;
    TextView resultText3;

    private static int MODE_METER = 1;
    private static int MODE_SQMTR = 2;
    private static int MODE_CUMTR = 3;

    private static int METERMODE = MODE_METER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meter, container, false);
        convertButton = (Button) rootView.findViewById(R.id.buttonConvertMeter);
        meterRadioGroup = (RadioGroup) rootView.findViewById(R.id.meter_radio);
        textInputMeter = (TextInputLayout) rootView.findViewById(R.id.textinput_meter);
        resultText1 = (TextView) rootView.findViewById(R.id.textMeter1);
        resultText2 = (TextView) rootView.findViewById(R.id.textMeter2);
        resultText3 = (TextView) rootView.findViewById(R.id.textMeter3);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertMeter(v);
            }
        });

        textInputMeter.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    textInputMeter.getEditText().clearFocus();
                    convertMeter(v);
                    return true;
                }
                return false;
            }
        });


        meterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_meter:
                        METERMODE = MODE_METER;
                        break;
                    case R.id.radio_sqm:
                        METERMODE = MODE_SQMTR;
                        break;
                    case R.id.radio_cum:
                        METERMODE = MODE_CUMTR;
                        break;
                }
                convertMeter(group.findViewById(checkedId));
            }
        });

        return rootView;
    }

    private void convertMeter(View v) {
        String editMeterStr = textInputMeter.getEditText().getText().toString();

        if (editMeterStr.matches("")) {
            editMeterStr = "0";
        }

        Double meterValue = Double.parseDouble(editMeterStr);

        switch (METERMODE) {
            case 1:
                // Meter
                Double feetValueDecimals = meterValue * 3.28;
                Integer feetIntegerPart = feetValueDecimals.intValue();
                Double feetDecimalPart = feetValueDecimals - feetIntegerPart;
                Double inchPartPrec = Helpers.toFixed(feetDecimalPart * 12, 1);
                Double feetValueDecimalsPrec = Helpers.toFixed(feetValueDecimals, 2);

                Double mkolValueDecimals = (1 / 0.743) * meterValue;
                Integer mkolIntegerPart = mkolValueDecimals.intValue();
                Double mkolDecimalPart = mkolValueDecimals - mkolIntegerPart;
                Double mkangPart = Helpers.toFixed(mkolDecimalPart * 24, 1);

                Double pkolValueDecimals = (1 / 0.7114) * meterValue;
                Integer pkolIntegerPart = pkolValueDecimals.intValue();
                Double pkolDecimalPart = pkolValueDecimals - pkolIntegerPart;
                Double pkangPart = Helpers.toFixed(pkolDecimalPart * 24, 1);

                resultText1.setText(feetValueDecimalsPrec.toString() + "' = " + feetIntegerPart.toString() + "' " + inchPartPrec.toString() + "\"" );
                resultText2.setText(mkolIntegerPart.toString() + "mk " + mkangPart.toString() + "ang");
                resultText3.setText(pkolIntegerPart.toString() + "pk " + pkangPart.toString() + "ang");

                break;

            case 2:
                // Square Meter
                Double sqmeterValue =  Helpers.toFixed(meterValue * 10.7639, 4);
                resultText1.setText(sqmeterValue.toString() + " sq.ft");
                resultText2.setText("");
                resultText3.setText("");

                break;

            case 3:
                // Cubic Meter
                Double cumeterValue = Helpers.toFixed(meterValue * 35.28, 4);
                resultText1.setText(cumeterValue.toString() + " cu.ft");
                resultText2.setText("");
                resultText3.setText("");

                break;
        }
        Helpers.hideKeyboard(getActivity(), textInputMeter.getEditText());
    }
}
