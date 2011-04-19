package Codemeditation.DroidBoard;

import roboguice.activity.RoboTabActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class ProjectPhasesTabActivity extends RoboTabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_phases_tab);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int project_id = bundle.getInt("PROJECT_ID");
		String project_name = bundle.getString("PROJECT_NAME");
		new LoadPhasesctsTask().execute(null);
		
		Intent phaseintent = new Intent(ProjectPhasesTabActivity.this, PhaseActivity.class);
		
		TabHost host = getTabHost();
		TextView indicator = new TextView(this);
		indicator.setText("Phase 1");
		indicator.setHeight(23);
		indicator.setWidth(50);
		host.addTab(host.newTabSpec("tab1").setIndicator(indicator).setContent(phaseintent));
		host.addTab(host.newTabSpec("tab2").setIndicator("tab 2").setContent(phaseintent));
	}
	
    private class LoadPhasesctsTask extends AsyncTask<Void, Void, Void>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(ProjectPhasesTabActivity.this, "", "Loading phases ...", true);
    	}
		
		@Override
		protected Void doInBackground(Void... params) {

	
    		return null;
		}
    	
		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) dialog.dismiss();
			super.onPostExecute(result);
		}

    }
}
