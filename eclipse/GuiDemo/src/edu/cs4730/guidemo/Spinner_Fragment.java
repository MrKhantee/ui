package edu.cs4730.guidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;


/**
 *  This is an example with spinners and array Adapters.
 *  Also a seek bar too for fun.
 *  
 *  odd, the layout for the spinners are using a white text.  Normally they follow the theme.  
 *  Not sure what happened here.  But the text is hard to read.
 */
public class Spinner_Fragment extends Fragment implements AdapterView.OnItemSelectedListener  {

	
	String TAG = "Spinner_fragment";
	Context myContext;

	Spinner SpinnerSB, mySpinner;
	SeekBar mySeekBar;
	
	//myList used to "fill" the first spinner.
	String[] myList= {"0","1","2","3","4","5"};
	

	public Spinner_Fragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "OnCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView =  inflater.inflate(R.layout.spinner_fragment, container, false);

		//first we will work on the spinner1 (which controls the seekbar)
		SpinnerSB = (Spinner)myView.findViewById(R.id.spinner1);
		//create the ArrayAdapter of strings from my List.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(myContext,android.R.layout.simple_spinner_item, myList);
		//set the dropdown layout
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//finally set the adapter to the spinner
		SpinnerSB.setAdapter(adapter);
		//set the selected listener as well
		SpinnerSB.setOnItemSelectedListener(this);
		
		//get the seekbar, no listener, the spinner will change it.
		mySeekBar = (SeekBar) myView.findViewById(R.id.seekBar1);
		mySeekBar.setMax(5);  //matches the items in myList.
		
		//finally the second spinner, but using the array from values strings.
		mySpinner = (Spinner)myView.findViewById(R.id.spinner2);
		//from the resource in values  /res/values/strings called spinneritems
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(myContext, R.array.spinneritems, 
				android.R.layout.simple_spinner_item);
		//now like before
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mySpinner.setAdapter(adapter2);
		mySpinner.setOnItemSelectedListener(this);
		
		return myView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myContext = activity.getApplicationContext();
		Log.d(TAG,"onAttach");
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		if (parent.getId() == R.id.spinner1)  { // used to control the seekbar
			if (position !=-1)  //-1 is nothing selected.  just making sure.
			mySeekBar.setProgress (position);  //just use position, don't care about the text itself themselves.
		} else {
			//this case, I want the text in the spinner box.
			Toast.makeText(myContext, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		//Im ignoring this one.
	}


}
