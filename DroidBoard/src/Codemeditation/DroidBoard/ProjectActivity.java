package Codemeditation.DroidBoard;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class ProjectActivity extends RoboActivity {
	@InjectView(R.id.project_header) TextView project_header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_view);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int project_id = bundle.getInt("PROJECT_ID");
		String project_name = bundle.getString("PROJECT_NAME");
		project_header.setText(String.format("%s",project_name));
		new LoadPhasesctsTask().execute(null);
	}
	
    private class LoadPhasesctsTask extends AsyncTask<Void, Void, Void>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(ProjectActivity.this, "", "Loading project ...", true);
    	}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
    		
    		return null;
		}
    	
		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) dialog.dismiss();
			super.onPostExecute(result);
		}

    }
}
