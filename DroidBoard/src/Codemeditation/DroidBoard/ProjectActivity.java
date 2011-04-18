package Codemeditation.DroidBoard;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProjectActivity extends RoboActivity {
	@InjectView(R.id.project_header) TextView project_header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.project_view);
		String project_id = getIntent().getStringExtra(Intent.EXTRA_TEXT);
		project_header.setText("project_id here");
	}
}
