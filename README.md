# Simple Search View

<img src="https://i.imgur.com/sPDiXWM.gif" width="260" height="426" />

# Installation
**Step 1**. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

        allprojects {
        	repositories {
        		...
        		maven { url 'https://jitpack.io' }
        	}
        }
  
**Step 2**. Add the dependency

        dependencies {
        	implementation 'com.github.vongocanhthi:SimpleSearchView:1.0.0'
        }

# Usage
## XML:

        <com.vnat.library.view.SimpleSearchView
            android:id="@+id/simpleSearchView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:ssv_cursorVisible="true"
            app:ssv_hint="Search"
            app:ssv_text=""
            app:ssv_textColor="@color/black"
            app:ssv_textColorHint="@color/hint"
            app:ssv_textSize="18sp" />
            
## Submit Suggestion List:
**First**, you should submit a list of suggestions in order to be able to query your search.

        simpleSearchView.submitSuggestionList(mSuggestionList);
       
## Event listeners:
### Listen for query events:

        simpleSearchView.setOnQueryTextChangeListener(new OnQueryTextChangeListener() {
            @Override
            public void onQueryTextChange(String queryText) {
                // get suggestions based on "queryText"
            }

            @Override
            public void onQueryTextSubmit(String queryText) {
                // event when pressing search
            }
        });
        
### Listen for suggested click events:
        
        simpleSearchView.setOnSuggestionListener(new OnSuggestionListener() {
            @Override
            public void onSuggestionClick(int position) {

            }

            @Override
            public void onSuggestionLongClick(int position) {

            }

            @Override
            public void onSuggestionRightIconClick(int position) {

            }

            @Override
            public void onSuggestionRightIconLongClick(int position) {

            }
        });
        
### Listen to action events:

        simpleSearchView.setOnActionIconListener(new OnActionIconListener() {
            @Override
            public void onActionIconLeft() {

            }

            @Override
            public void onActionIconRight() {

            }
        });

## Custom icons:

        // change icon action
        simpleSearchView.setLeftSuggestionIcon(R.drawable.ic_search_black);
        simpleSearchView.setRightSuggestionIcon(R.drawable.ic_arrow_up_left_black);

        // change icon suggestion
        simpleSearchView.setLeftActionIcon(R.drawable.ic_search_black);
        simpleSearchView.setRightActionIcon(R.drawable.ic_clear_black);
      
     
# License
    Copyright [2021] [Võ Ngọc Anh Thi]

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
