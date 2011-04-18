package Codemeditation.DroidBoard;

import java.util.List;

import roboguice.activity.RoboListActivity;
import roboguice.inject.InjectView;
import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.Domain.Project;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;

public class ProjectlistActivity extends RoboListActivity {
	@InjectView(R.id.projectlist_title) TextView projectlist_title_view;
	@Inject IKanbanApi kanbanApi;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        new LoadProjectsTask().execute(null);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent i = new Intent(ProjectlistActivity.this, ProjectActivity.class);
    	i.putExtra(Intent.EXTRA_TEXT, kanbanApi.GetProjects().get(position).id);
    	startActivity(i);
    }
    
    private class ProjectAdapter extends ArrayAdapter<Project> {
	
		private Project[] projects;

		public ProjectAdapter(Project[] projects) {
			super(ProjectlistActivity.this, R.layout.project_item, R.id.name, projects);
			this.projects = projects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View item_row = inflater.inflate(R.layout.project_item, parent, false);
			
			TextView name = (TextView)item_row.findViewById(R.id.name);
			name.setText(projects[position].name );
			
			TextView description = (TextView)item_row.findViewById(R.id.description);
			description.setText(projects[position].description );
			return item_row;
		}
    }

    private class LoadProjectsTask extends AsyncTask<Void, Void, List<Project>>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(ProjectlistActivity.this, "", "Loading projects ...", true);
    	}
    	
    	@Override
    	protected void onPostExecute(List<Project> result) {
    		if (dialog != null) dialog.dismiss();
    		setContentView(R.layout.projectlistview);

    		Project[] projects = result.toArray(new Project[0]);
    		ProjectAdapter adapter = new ProjectAdapter(projects);
    		
    		setListAdapter(adapter);
    		projectlist_title_view.setText(String.format("Active Projects: %s", result.size()));
    	}
    	
		@Override
		protected List<Project> doInBackground(Void... arg0) {
			return kanbanApi.GetProjects();
		}

    }
}