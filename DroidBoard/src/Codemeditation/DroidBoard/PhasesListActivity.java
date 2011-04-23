package Codemeditation.DroidBoard;

import java.util.List;

import roboguice.activity.RoboActivity;
import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.Domain.Phase;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.TextView;

import com.github.ysamlan.horizontalpager.HorizontalPager;
import com.google.inject.Inject;

public class PhasesListActivity extends RoboActivity {
	@Inject IKanbanApi kanbanApi;
	private String projectName;
	private int projectId;
	private List<Phase> phases;
	private Handler handler;
	private Runnable runnable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phases_list_view);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		projectId = bundle.getInt("PROJECT_ID");
		projectName = bundle.getString("PROJECT_NAME");
		
		 // Create the view switcher
        final HorizontalPager realViewSwitcher = new HorizontalPager(getApplicationContext());
        // Add some views to it
        final int[] backgroundColors =
                { Color.RED, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW };
        

		handler = new Handler();
		
		runnable = new Runnable() {

			@Override
			public void run() {

			       for (int i = 0; i < phases.size(); i++) {
			            TextView textView = new TextView(getApplicationContext());
			            textView.setText(phases.get(i).name);
			            textView.setTextSize(20);
			            textView.setTextColor(Color.WHITE);
			            textView.setGravity(Gravity.TOP);
			            realViewSwitcher.addView(textView);
			        }

			        // set as content view
			        setContentView(realViewSwitcher);
			}
			
		};
		
		LoadPhasesctsTask async = new LoadPhasesctsTask();
		async.execute(null);		
	}
	
    private class LoadPhasesctsTask extends AsyncTask<Void, Void, Void>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(PhasesListActivity.this, "", "Loading phases ...", true);
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
