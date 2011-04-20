package Codemeditation.DroidBoard;

import java.util.List;

import roboguice.activity.RoboTabActivity;
import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.Domain.Phase;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.inject.Inject;

public class ProjectPhasesTabActivity extends RoboTabActivity {
	@Inject IKanbanApi kanbanApi;
	private String projectName;
	private int projectId;
	private List<Phase> phases;
	private Handler handler;
	private Runnable runnable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_phases_tab);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		projectId = bundle.getInt("PROJECT_ID");
		projectName = bundle.getString("PROJECT_NAME");
		TabHost host = getTabHost();
		Intent phaseintent = new Intent(ProjectPhasesTabActivity.this, PhaseActivity.class);
				
		TextView indicator = new TextView(ProjectPhasesTabActivity.this);
		indicator.setText("Project");
		indicator.setHeight(23);
		indicator.setWidth(50);
		
		host.addTab(host.newTabSpec("Project").setIndicator(indicator).setContent(phaseintent));
			
		handler = new Handler();
		
		runnable = new Runnable() {

			@Override
			public void run() {
				TabHost host = getTabHost();
				Intent phaseintent = new Intent(ProjectPhasesTabActivity.this, PhaseActivity.class);
				
				TextView indicator = new TextView(ProjectPhasesTabActivity.this);
				indicator.setText("Phase 1");
				indicator.setHeight(23);
				indicator.setWidth(50);
				host.addTab(host.newTabSpec("tab1").setIndicator(indicator).setContent(phaseintent));
				host.addTab(host.newTabSpec("tab2").setIndicator("tab 2").setContent(phaseintent));
				
			}
			
		};
		
		LoadPhasesctsTask async = new LoadPhasesctsTask();
		async.execute(null);		
	}
	
    private class LoadPhasesctsTask extends AsyncTask<Void, Void, Void>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(ProjectPhasesTabActivity.this, "", "Loading phases ...", true);
    	}
		
		@Override
		protected Void doInBackground(Void... params) {
			phases = kanbanApi.GetPhases(projectId);
    		return null;
		}
    	
		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) dialog.dismiss();
			handler.post(runnable);
			super.onPostExecute(result);
		}

    }
}
