package Codemeditation.DroidBoard;

import java.util.Collection;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.Domain.Project;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.inject.Inject;

public class ProjectlistActivity extends RoboActivity {
	@InjectView(R.id.projectlist_title) TextView projectlist_title_view;
	@Inject IKanbanApi kanbanApi;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projectlistview);
        new LoadProjectsTask().execute(null);
    }
    
    private class LoadProjectsTask extends AsyncTask<Void, Void, Collection<Project>>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(ProjectlistActivity.this, "", "Loading. Please wait ...", true);
    	}
    	
    	@Override
    	protected void onPostExecute(Collection<Project> result) {
    		dialog.dismiss();
    		 projectlist_title_view.setText(String.format("%s active projects", result.size()));
    	}
    	
		@Override
		protected Collection<Project> doInBackground(Void... arg0) {
			return kanbanApi.GetProjects();
		}

    }
}